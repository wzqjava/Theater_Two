package com.bw.movie.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.UserIDChaXunBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.model.PersonalDetailsModel;
import com.bw.movie.myview.MyToggleButton;
import com.bw.movie.net.Constans;
import com.bw.movie.presenter.PersonalDetailsPresenter;
import com.bw.movie.view.PersonalDetailsView;
import com.greendao.gen.UserBeanDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PersonalDetailsActivity extends BaseMVPActivity<PersonalDetailsView,PersonalDetailsPresenter> implements PersonalDetailsView {


    String path = Environment.getExternalStorageDirectory()+"/zhx.jpg";
    private ImageView personaldetail_activity_img;
    private TextView personaldetail_activity_name;
    private TextView personaldetail_activity_sex;
    private TextView personaldetail_activity_date;
    private TextView personaldetail_activity_phone;
    private TextView personaldetail_activity_email;
    private ImageView personaldetail_activity_xiugai;
    private ImageView personaldetail_activity_fanhui;
    private RelativeLayout personaldetail_activity_xiangji;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private View shangchuan_xiangce;
    private View shangchuan_xiangji;
    private View shangchuan_tui;
    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected PersonalDetailsPresenter initPresenter() {
        return new  PersonalDetailsPresenter();
    }
    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_personal_details;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, PersonalDetailsActivity.class));
    }

    /**
     * 初始化布局
     *
     * @return
     */
    @Override
    protected void initView() {
        personaldetail_activity_img = (ImageView) findViewById(R.id.personaldetail_activity_img);
        personaldetail_activity_name = (TextView) findViewById(R.id.personaldetail_activity_name);
        personaldetail_activity_sex = (TextView) findViewById(R.id.personaldetail_activity_sex);
        personaldetail_activity_date = (TextView) findViewById(R.id.personaldetail_activity_date);
        personaldetail_activity_phone = (TextView) findViewById(R.id.personaldetail_activity_phone);
        personaldetail_activity_email = (TextView) findViewById(R.id.personaldetail_activity_email);
        personaldetail_activity_fanhui = (ImageView) findViewById(R.id.personaldetail_activity_fanhui);
        personaldetail_activity_xiangji = (RelativeLayout) findViewById(R.id.personaldetail_activity_xiangji);
        personaldetail_activity_xiugai = (ImageView) findViewById(R.id.personaldetail_activity_xiugai);
        shangchuan_xiangce = personaldetail_activity_xiangji.findViewById(R.id.shangchuan_xiangce);
        shangchuan_xiangji = personaldetail_activity_xiangji.findViewById(R.id.shangchuan_xiangji);
        shangchuan_tui = personaldetail_activity_xiangji.findViewById(R.id.shangchuan_tui);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        personaldetail_activity_xiangji.setVisibility(View.INVISIBLE);

        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        presenter.getChaXun(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId());

    }
    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        personaldetail_activity_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        personaldetail_activity_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personaldetail_activity_xiangji.setVisibility(View.VISIBLE);
            }
        });
        shangchuan_tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personaldetail_activity_xiangji.setVisibility(View.INVISIBLE);
            }
        });

        personaldetail_activity_xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改密码
                final View tc = View.inflate(PersonalDetailsActivity.this,R.layout.item_change_pwd, null);
                new AlertDialog.Builder(PersonalDetailsActivity.this)
                        .setView(view)
                        .setTitle("警告:更改密码")
                        .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast("用户点击了取消操作");
                            }
                        })
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText oldPwd = tc.findViewById(R.id.item_old_pwd);
                                EditText newPwd = tc.findViewById(R.id.item_new_pwd);
                                EditText newPwd2 = tc.findViewById(R.id.item_new_pwd2);

                                String old = oldPwd.getText().toString().trim();
                                String xin = newPwd.getText().toString().trim();
                                String xin2 = newPwd2.getText().toString().trim();

                                String getReturn = presenter.totNull(old, xin,xin2);
                                if("yes".equals(getReturn)){
                                    String encryptionOld = presenter.Encryption(old);
                                    String encryptionNew = presenter.Encryption(xin);
                                    String encryptionNew2 = presenter.Encryption(xin2);
                                    Map<String,String> pwds = new HashMap<>();
                                    pwds.put("oldPwd",encryptionOld);
                                    pwds.put("newPwd",encryptionNew);
                                    pwds.put("newPwd2",encryptionNew2);
                                 //   presenter.ChangePwd(pwds);
                                }else{
                                    showToast(getReturn);
                                }
                            }
                        }).show();
            }
        });
        shangchuan_xiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("zhx","您选择了相机");
                showToast("您选择了相机");
                //6.0权限
                if (hasPermissions(Manifest.permission.CAMERA)){
                    Log.e("zhx","申请权限");
                    requestPermissions(Constans.XIANG_JI,Manifest.permission.CAMERA);
                }else{
                    //已经有权限了，去执行打开相机的操作
                    doXiangJi();
                }
            }
        });
        shangchuan_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("zhx","您选择了相册");
                showToast("您选择了相册");

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,101);
            }
        });

    }
    @SuppressLint("MissingPermission")
    @Override
    public void doXiangJi(){
        Log.e("zhx","拍照");
        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent1.addCategory("android.intent.category.DEFAULT");
        //见图片存入内存
        intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
        startActivityForResult(intent1,1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1000 && resultCode==RESULT_OK){
            //裁剪
            Intent mintent = new Intent("com.android.camera.action.CROP");
            mintent.setDataAndType(Uri.fromFile(new File(path)),"image/*");
            //是否支持剪裁
            mintent.putExtra("crop", true);
            mintent.putExtra("acpactX", 1);
            mintent.putExtra("acpactY", 1);
            mintent.putExtra("outputX", 250);
            mintent.putExtra("outputY", 250);
            //返回
            mintent.putExtra("return-data", true);
            startActivityForResult(mintent, 2000);
        }
        if (requestCode == 101 && resultCode == RESULT_OK) {
            //从图库里获取照片   uri选取图片的地址
            Uri uri = data.getData();
            //img.setImageURI(uri);
            //剪裁
            Intent mintent = new Intent("com.android.camera.action.CROP");
            mintent.setDataAndType(uri, "image/*");
            //设置选取图片是否支持剪裁
            mintent.putExtra("crop", true);
            // 裁剪框的比例，1：1
            mintent.putExtra("acpactX", 1);
            mintent.putExtra("acpactY", 1);
            // 裁剪后输出图片的尺寸大小
            mintent.putExtra("outputX", 250);
            mintent.putExtra("outputY", 250);
            //是否将裁剪数据保留在Bitmap中返回
            mintent.putExtra("return-data", true);
            startActivityForResult(mintent, 202);
        }
        if(requestCode==2000 && resultCode==RESULT_OK){
            Bitmap bitmap = data.getParcelableExtra("data");
            //上传头像
            saveBitmapFile(bitmap);
        }
        if (requestCode == 202 && resultCode == RESULT_OK ) {
            //拿到剪裁后的图片,设置到imageview;
            Bitmap bitmap = data.getParcelableExtra("data");
            //上传头像
            saveBitmapFile(bitmap);
        }
    }
    //将bitmap转化为文件
    public void saveBitmapFile(Bitmap bitmap) {

        File file = new File(Environment.getExternalStorageDirectory()+"/zhx.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            shangChuanHeadPic(file);

            bos.flush();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //上传的步骤
    public void shangChuanHeadPic(File file){
        Log.e("zhx","文件"+file.getName());
        HashMap<String, RequestBody> params = new HashMap<>(4);

       // File file = new File(param.filePaths.get(current));

//image//filename
        //有时候需要指定文件的类型   String.format("imgfile\"; filename=\"%s",file.getName()
       /* params.put(String.format("imgfile\"; image=\"%s", file.getName()),
                RequestBody.create(MediaType.parse("image/*"), file));*/
        params.put("image",
                RequestBody.create(MediaType.parse("image/*"), file));
        presenter.getShangChuanHeadPic(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),params);

    }

    @Override
    public void ChaXunSuccess(UserIDChaXunBean.ResultBean result) {
        Log.e("zhx","查询成功："+result.getNickName());
        personaldetail_activity_name.setText(result.getNickName()+"");

        personaldetail_activity_sex.setText(result.getSex() == 1 ? "男" : "女");
        String date = (String)DateFormat.format("yyyy年MMMMdd日", Long.parseLong(result.getLastLoginTime()+""));
        // myViewHolder.myfragment_remind_item_date.setText(date  + "");
        personaldetail_activity_date.setText(date  + "");
        personaldetail_activity_phone.setText(result.getPhone()+"");
        //设置头像圆形
        RequestManager with = Glide.with(this);
        with.load(result.getHeadPic()+"")
                .apply(new RequestOptions().transform(new CircleCrop()))
                .into(personaldetail_activity_img);
    }
    @Override
    public void ChaXunError(String message) {
        Log.e("zhx","查询失败："+message);
    }

    @Override
    public void ShangChuanHeadPicSuccess(String message) {
        showToast("上传成功"+message);
        Log.e("zhx","上传成功   "+message);
        presenter.getChaXun(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId());
        personaldetail_activity_xiangji.setVisibility(View.INVISIBLE);
    }
    @Override
    public void ShangChuanHeadPicError(String message) {
        showToast("上传失败"+message);
    }
}
