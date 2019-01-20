package com.bw.movie.activity;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bw.movie.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.RandomAccessFile;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpHead;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class DownLoadActivity extends AppCompatActivity {

    protected static final String TAG = "OtherActivity";
    //下载线程的数量
    private final static int threadsize = 3;
    protected static final int SET_MAX = 0;
    public static final int UPDATE_VIEW = 1;
    private ProgressBar pb;
    private Button bt_download;
    private Button bt_pause;
    private TextView tv_info;
    //显示进度和更新进度
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SET_MAX://设置进度条的最大值
                    int filelength = msg.arg1;
                    pb.setMax(filelength);
                    break;
                case UPDATE_VIEW://更新进度条 和 下载的比率
                    int len = msg.arg1;//新下载的长度
                    pb.setProgress(pb.getProgress() + len);//设置进度条的刻度

                    int max = pb.getMax();//获取进度的最大值
                    int progress = pb.getProgress();//获取已经下载的数据量
                    // 下载：30  总：100
                    int result = (progress * 100) / max;

                    tv_info.setText("下载:" + result + "%");

                    break;

                default:
                    break;
            }
        }

        ;
    };
    String uri = "http://172.17.8.100/media/movie.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        //找到控件
        pb = (ProgressBar) findViewById(R.id.pb);
        tv_info = (TextView) findViewById(R.id.tv_info);
        bt_download = (Button) findViewById(R.id.bt_download);
        bt_pause = (Button) findViewById(R.id.bt_pause);
        //数据的回显
        //确定下载的文件
        String name = getFileName(uri);
        File file = new File(Environment.getExternalStorageDirectory(), name);
        if (file.exists()) {//文件存在回显
            //获取文件的大小
            int filelength = (int) file.length();
            pb.setMax(filelength);
            try {
                //统计原来所有的下载量
                int count = 0;
                //读取下载记录文件
                for (int threadid = 0; threadid < threadsize; threadid++) {
                    //获取原来指定线程的下载记录
                    int existDownloadLength = readDownloadInfo(threadid);
                    count = count + existDownloadLength;
                }
                //设置进度条的刻度
                pb.setProgress(count);

                //计算比率
                int result = (count * 100) / filelength;
                tv_info.setText("下载:" + result + "%");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bt_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download(v);
            }
        });
        bt_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause(v);
            }
        });
    }

    //暂停
    private boolean flag = false;//是否在下载

    public void pause(View v) {
        flag = false;
        bt_download.setEnabled(true);
        bt_pause.setEnabled(false);
    }

    //下载
    public void download(View v) {
        flag = true; //是在下载
        bt_download.setEnabled(false);//一点击变成不可点击
        bt_pause.setEnabled(true);//一点击变成可点击
        new Thread() {//子线程
            public void run() {
                try {
                    //获取服务器上文件的大小
                    HttpClient client = new DefaultHttpClient();
                    HttpHead request = new HttpHead(uri);
                    HttpResponse response = client.execute(request);
                    //response 只有响应头 没有响应体
                    if (response.getStatusLine().getStatusCode() == 200) {
                        Header[] headers = response.getHeaders("Content-Length");
                        String value = headers[0].getValue();
                        //文件大小
                        int filelength = Integer.parseInt(value);
                        Log.i(TAG, "filelength:" + filelength);

                        //设置进度条的最大值
                        Message msg_setmax = Message.obtain(mHandler, SET_MAX, filelength, 0);
                        msg_setmax.sendToTarget();


                        //处理下载记录文件
                        for (int threadid = 0; threadid < threadsize; threadid++) {
                            //对应的下载记录文件
                            File file = new File(Environment.getExternalStorageDirectory(), threadid + ".txt");
                            //判断文件是否存在
                            if (!file.exists()) {
                                //创建文件
                                file.createNewFile();
                            }
                        }

                        //在sdcard创建和服务器大小一样的文件
                        String name = getFileName(uri);
                        File file = new File(Environment.getExternalStorageDirectory(), name);
                        //随机访问文件
                        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                        //设置文件的大小
                        raf.setLength(filelength);
                        //关闭
                        raf.close();

                        //计算每条线程的下载量
                        int block = (filelength % threadsize == 0) ? (filelength / threadsize) : (filelength / threadsize + 1);
                        //开启三条线程执行下载
                        for (int threadid = 0; threadid < threadsize; threadid++) {
                            new DownloadThread(threadid, uri, file, block).start();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }


    //线程下载类
    private class DownloadThread extends Thread {
        private int threadid;//线程的id
        private String uri;//下载的地址
        private File file;//下载文件
        private int block;//下载的块
        private int start;
        private int end;

        public DownloadThread(int threadid, String uri, File file, int block) {
            super();
            this.threadid = threadid;
            this.uri = uri;
            this.file = file;
            this.block = block;
            //计算下载的开始位置和结束位置
            start = threadid * block;
            end = (threadid + 1) * block - 1;

            try {
                //读取该条线程原来的下载记录
                int existDownloadLength = readDownloadInfo(threadid);

                //修改下载的开始位置  从新下载
                start = start + existDownloadLength;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //下载  状态码：200是普通的下载   206是分段下载    Range:范围
        @Override
        public void run() {
            super.run();
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //跳转到起始位置
                raf.seek(start);

                //分段下载
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(uri);
                request.addHeader("Range", "bytes:" + start + "-" + end);//添加请求头
                HttpResponse response = client.execute(request);
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    //把流写入到文件
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        //如果暂停下载 点击暂停 false 就直接return 点击下载true接着下载
                        if (!flag) {
                            return;//标准线程结束
                        }
                        //写数据
                        raf.write(buffer, 0, len);

                        //读取原来下载的数据量 这里读取是为了更新下载记录
                        int existDownloadLength = readDownloadInfo(threadid);//原来下载的数据量

                        //计算最新的下载
                        int newDownloadLength = existDownloadLength + len;

                        //更新下载记录 从新记录最新下载位置
                        updateDownloadInfo(threadid, newDownloadLength);
                        //更新进度条的显示  下载的百分比
                        Message update_msg = Message.obtain(mHandler, UPDATE_VIEW, len, 0);
                        update_msg.sendToTarget();
                        //模拟 看到进度条动的效果
                        SystemClock.sleep(50);
                    }
                    inputStream.close();
                    raf.close();
                    Log.i(TAG, "第" + threadid + "条线程下载完成");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * 读取指定线程的下载数据量
     *
     * @param threadid 线程的id
     * @return
     * @throws Exception
     */
    public int readDownloadInfo(int threadid) throws Exception {
        //下载记录文件
        File file = new File(Environment.getExternalStorageDirectory(), threadid + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        //读取一行数据
        String content = br.readLine();

        int downlength = 0;
        //如果该文件第一次创建去执行读取操作 文件里面的内容是 null
        if (!TextUtils.isEmpty(content)) {
            downlength = Integer.parseInt(content);
        }
        //关闭流
        br.close();
        return downlength;
    }


    /**
     * 更新下载记录
     *
     * @param threadid
     * @param newDownloadLength
     */
    public void updateDownloadInfo(int threadid, int newDownloadLength) throws Exception {
        //下载记录文件
        File file = new File(Environment.getExternalStorageDirectory(), threadid + ".txt");
        FileWriter fw = new FileWriter(file);
        fw.write(newDownloadLength + "");
        fw.close();
    }

    /**
     * 获取文件的名称
     *
     * @param uri
     * @return
     */
    private String getFileName(String uri) {
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

}
