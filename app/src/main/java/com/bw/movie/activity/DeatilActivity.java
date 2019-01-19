package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.DetailRecyclerviewPingLunAdapter;
import com.bw.movie.adapter.Detail_Recyclerview_Img;
import com.bw.movie.adapter.Detail_Recyclerview_Movie;
import com.bw.movie.adapter.ShowStillActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.DetailPresenter;
import com.bw.movie.view.DetailPingLunZan;
import com.bw.movie.view.DetailView;
import com.greendao.gen.UserBeanDao;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;

public class DeatilActivity extends BaseMVPActivity<DetailView, DetailPresenter> implements DetailView {

    private TextView detail_name;
    private ImageView detail_img;
    private Button detail_detail;
    private Button detail_yugao;
    private Button detail_juzhao;
    private Button detail_yingping;
    private ImageView detail_fanhui;
    private Button detail_goumai;
    private ImageView detail_guanzhu;
    private RelativeLayout detail_onebutton;
    private RelativeLayout detail_twobutton;

    private ImageView detail_button_img;
    private ImageView detail_button_down;
    private TextView detail_button_detail_one;
    private TextView detail_button_detail_two;
    private TextView detail_button_detail_three;
    private TextView detail_button_detail_four;
    private TextView detail_button_jianjie;
    private TextView detail_button_name_one;
    private TextView detail_button_name_two;
    private TextView detail_button_name_three;
    private TextView detail_button_name_four;

    private ImageView detail_button2_down;
    private ImageView detail_button2_bianji;
    private TextView detail_button2_name;
    private RecyclerView detail_button2_recyclerview;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private Detail_Recyclerview_Movie detail_recyclerview_movie;

    private RelativeLayout detail_three_button;
    private EditText pinglun_shuru;
    private Button pinglun_fasong;
    private ImageView movieinfo_imageview_changd;

    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected DetailPresenter initPresenter() {
        return new DetailPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_deatil;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        detail_name = findViewById(R.id.detail_name);
        detail_img = findViewById(R.id.detail_img);
        detail_detail = findViewById(R.id.detail_detail);
        detail_yugao = findViewById(R.id.detail_yugao);
        detail_juzhao = findViewById(R.id.detail_juzhao);
        detail_yingping = findViewById(R.id.detail_yingping);
        detail_fanhui = findViewById(R.id.detail_fanhui);
        detail_goumai = findViewById(R.id.detail_goumai);
        detail_onebutton = findViewById(R.id.detail_onebutton);
        detail_twobutton = findViewById(R.id.detail_twobutton);
        detail_guanzhu = findViewById(R.id.detail_guanzhu);
        movieinfo_imageview_changd = findViewById(R.id.movieinfo_imageview_changd);

        detail_button_img = detail_onebutton.findViewById(R.id.detail_button_img);
        detail_button_down = detail_onebutton.findViewById(R.id.detail_button_down);
        detail_button_detail_one = detail_onebutton.findViewById(R.id.detail_button_detail_one);
        detail_button_detail_two = detail_onebutton.findViewById(R.id.detail_button_detail_two);
        detail_button_detail_three = detail_onebutton.findViewById(R.id.detail_button_detail_three);
        detail_button_detail_four = detail_onebutton.findViewById(R.id.detail_button_detail_four);
        detail_button_jianjie = detail_onebutton.findViewById(R.id.detail_button_jianjie);
        detail_button_name_one = detail_onebutton.findViewById(R.id.detail_button_name_one);
        detail_button_name_two = detail_onebutton.findViewById(R.id.detail_button_name_two);
        detail_button_name_three = detail_onebutton.findViewById(R.id.detail_button_name_three);
        detail_button_name_four = detail_onebutton.findViewById(R.id.detail_button_name_four);


        detail_button2_down = detail_twobutton.findViewById(R.id.detail_button2_down);
        detail_button2_name = detail_twobutton.findViewById(R.id.detail_button2_name);
        detail_button2_bianji = detail_twobutton.findViewById(R.id.detail_button2_bianji);
        detail_button2_recyclerview = detail_twobutton.findViewById(R.id.detail_button2_recyclerview);

        detail_three_button = findViewById(R.id.detail_three_button);
        pinglun_shuru = detail_three_button.findViewById(R.id.pinglun_shuru);
        pinglun_fasong = detail_three_button.findViewById(R.id.pinglun_fasong);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        detail_onebutton.setVisibility(View.INVISIBLE);
        detail_twobutton.setVisibility(View.INVISIBLE);
        detail_three_button.setVisibility(View.INVISIBLE);
    }


    /**
     * 设置监听
     */
    @Override
    protected void setListener() {

        detail_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detail_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_onebutton.setVisibility(View.VISIBLE);
                detail_twobutton.setVisibility(View.INVISIBLE);
                detail_goumai.setVisibility(View.INVISIBLE);
                detail_three_button.setVisibility(View.INVISIBLE);
            }
        });
        detail_button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.INVISIBLE);
                detail_goumai.setVisibility(View.VISIBLE);
                detail_three_button.setVisibility(View.INVISIBLE);
            }
        });
        detail_button2_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JZVideoPlayer.releaseAllVideos();
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.INVISIBLE);
                detail_goumai.setVisibility(View.VISIBLE);
                detail_three_button.setVisibility(View.INVISIBLE);
            }
        });
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, DeatilActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void even(final MovieFragmentBean.ResultBean resultBean) {

        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        Log.e("zhx", resultBean.getName() + "");
        detail_name.setText(resultBean.getName() + "");
        Glide.with(this).load(resultBean.getImageUrl() + "").into(detail_img);
        Glide.with(this).load(resultBean.getImageUrl() + "").into(movieinfo_imageview_changd);


        if (resultBean.getFollowMovie() == 1) {
            detail_guanzhu.setImageResource(R.mipmap.com_icon_collection_selected);
        } else {
            detail_guanzhu.setImageResource(R.mipmap.com_icon_collection_default);
        }
        detail_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关注
                if (resultBean.getFollowMovie() == 1) {
                    //取消关注
                    HashMap<String, String> qu = new HashMap<>();
                    qu.put("movieId", "" + resultBean.getId());
                    presenter.getQuXiao(mUserBeans.get(0).getUserId(), mUserBeans.get(0).getSessionId(), qu);

                    resultBean.setFollowMovie(2);
                    // DeatilActivity.this.notify();
                } else {
                    //关注
                    HashMap<String, String> gu = new HashMap<>();
                    gu.put("movieId", "" + resultBean.getId());
                    presenter.getGuanZhu(mUserBeans.get(0).getUserId(), mUserBeans.get(0).getSessionId(), gu);
                    resultBean.setFollowMovie(1);
                    // DeatilActivity.this.notify();
                }
            }
        });
        //影片的id
        final int id = resultBean.getId();


        HashMap<String, String> map = new HashMap<>();
        map.put("movieId", String.valueOf(id));
        Log.e("zzz", mUserBeans.get(0).getUserId() + "   " + mUserBeans.get(0).getSessionId() + "");
        //详情展示
        presenter.getdetailData(mUserBeans.get(0).getUserId(), mUserBeans.get(0).getSessionId(), map);

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("movieId", String.valueOf(id));
        map1.put("page", "1");
        map1.put("count", "10");

        presenter.getPingLunData(mUserBeans.get(0).getUserId(), mUserBeans.get(0).getSessionId(), map1);

        // Log.e("zzz","");
        detail_goumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeatilActivity.this, SelectTheatersActivity.class);
                intent.putExtra("name", resultBean.getName() + "");
                intent.putExtra("id", id + "");
                startActivity(intent);
            }
        });
        //对影片的评论
        detail_button2_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_three_button.setVisibility(View.VISIBLE);
                pinglun_fasong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String trim = pinglun_shuru.getText().toString().trim();
                        Log.e("zhx", "评论影片：" + trim);
                        boolean aNull = presenter.isNull(trim);
                        if (aNull) {
                            HashMap<String, String> yp = new HashMap<>();
                            yp.put("movieId", resultBean.getId() + "");
                            yp.put("commentContent", "" + trim);
                            presenter.getPingLunYingPian(mUserBeans.get(0).getUserId(), mUserBeans.get(0).getSessionId(), yp);
                            Log.e("zhx", "评论影片的评论：" + trim);
                            detail_three_button.setVisibility(View.INVISIBLE);
                            pinglun_shuru.setText("");
                        } else {
                            showToast("输入信息不能为空");
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void suucess(final Detail_Detail_Bean.ResultBean resultBean) {

        Glide.with(this).load(resultBean.getImageUrl()).into(detail_button_img);

        EventBus.getDefault().postSticky(resultBean);
        detail_button_detail_one.setText(resultBean.getMovieTypes());
        detail_button_detail_two.setText(resultBean.getDirector());
        detail_button_detail_three.setText(resultBean.getDuration());
        detail_button_detail_four.setText(resultBean.getPlaceOrigin());
        String summary = resultBean.getSummary();
        summary = summary.substring(0, 100);
        detail_button_jianjie.setText(summary + "...");
        String starring = resultBean.getStarring();
        String[] split = starring.split(",");

        detail_button_name_one.setText(split[0]);
        detail_button_name_two.setText(split[1]);
        detail_button_name_three.setText(split[2]);
        detail_button_name_four.setText(split[3]);

        detail_yugao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                detail_button2_bianji.setVisibility(View.INVISIBLE);
                detail_button2_name.setText("预告");
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.VISIBLE);
                detail_goumai.setVisibility(View.INVISIBLE);
                detail_three_button.setVisibility(View.INVISIBLE);

                List<Detail_Detail_Bean.ResultBean.ShortFilmListBean> shortFilmList = resultBean.getShortFilmList();
                //播放视频
                detail_button2_recyclerview.setLayoutManager(new LinearLayoutManager(DeatilActivity.this, LinearLayoutManager.VERTICAL, false));

                detail_recyclerview_movie = new Detail_Recyclerview_Movie(DeatilActivity.this, shortFilmList);

                detail_button2_recyclerview.setAdapter(detail_recyclerview_movie);

            }
        });
        detail_juzhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_button2_bianji.setVisibility(View.INVISIBLE);
                detail_button2_name.setText("剧照");
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.VISIBLE);
                detail_goumai.setVisibility(View.INVISIBLE);
                detail_three_button.setVisibility(View.INVISIBLE);

                List<String> posterList = resultBean.getPosterList();
                detail_button2_recyclerview.setHasFixedSize(true);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                detail_button2_recyclerview.setLayoutManager(layoutManager);
                Detail_Recyclerview_Img detail_recyclerview_img = new Detail_Recyclerview_Img(DeatilActivity.this, posterList);

                detail_button2_recyclerview.setAdapter(detail_recyclerview_img);
                detail_recyclerview_img.setItemclick(new Detail_Recyclerview_Img.Itemclick() {
                    @Override
                    public void GetImages(String imageUrl) {
                        Intent intent = new Intent(getApplicationContext(),ShowStillActivity.class);
                        intent.putExtra("ImageUrl", imageUrl);
                        startActivity(intent);
                    }
                });
            }
        });


    }

    @Override
    public void error(String msg) {
        showToast("影片详情的" + msg + "");
    }

    @Override
    public void suucessPingLun(final List<DetailPingLunBean.ResultBean> result) {

        Log.e("xxxxxx", result.get(0).getCommentContent() + "");

        detail_yingping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_button2_name.setText("影评");
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.VISIBLE);
                detail_three_button.setVisibility(View.INVISIBLE);
                detail_goumai.setVisibility(View.INVISIBLE);
                //com_icon_publish_default
                detail_button2_bianji.setVisibility(View.VISIBLE);

                final DetailRecyclerviewPingLunAdapter adapter = new DetailRecyclerviewPingLunAdapter(DeatilActivity.this, result);
                detail_button2_recyclerview.setLayoutManager(new LinearLayoutManager(DeatilActivity.this, LinearLayoutManager.VERTICAL, false));
                detail_button2_recyclerview.setAdapter(adapter);
                //点赞
                adapter.dianzan(new DetailPingLunZan() {
                    @Override
                    public void zan(String s) {
                        //进行点赞操作
                        HashMap<String, String> zan = new HashMap<>();
                        zan.put("commentId", s + "");
                        presenter.getDianZan(mUserBeans.get(0).getUserId(), mUserBeans.get(0).getSessionId(), zan);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void pinglun(final String s) {
                        //开始评论别人的评论
                        detail_three_button.setVisibility(View.VISIBLE);
                        pinglun_fasong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String trim = pinglun_shuru.getText().toString().trim();
                                boolean aNull = presenter.isNull(trim);
                                if (aNull) {
                                    HashMap<String, String> yppl = new HashMap<>();
                                    yppl.put("commentId", s + "");
                                    yppl.put("replyContent", "" + trim);
                                    presenter.getPingLunPingLun(mUserBeans.get(0).getUserId(), mUserBeans.get(0).getSessionId(), yppl);
                                    Log.e("zhx", "评论别人的评论：" + trim);
                                    detail_three_button.setVisibility(View.INVISIBLE);
                                    pinglun_shuru.setText("");
                                } else {
                                    showToast("输入信息不能为空");
                                }
                            }
                        });
                    }
                });

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void errorPingLun(String msg) {
        showToast(msg + "");
        Log.e("zhx", "影片评论的：" + "影片评论的");
    }

    @Override
    public void suucessQuXiao(String msg) {
        showToast("" + msg);
        detail_guanzhu.setImageResource(R.mipmap.com_icon_collection_default);
    }

    @Override
    public void suucessGuanZhu(String msg) {
        showToast("" + msg);
        detail_guanzhu.setImageResource(R.mipmap.com_icon_collection_selected);
    }

    @Override
    public void errorQuXiao(String msg) {
        showToast("取消关注失败" + msg);
    }

    @Override
    public void errorGuanZhu(String msg) {
        showToast("关注失败" + msg);
    }

    @Override
    public void successdianzan(String message) {
        showToast("点赞成功     " + message);
    }

    @Override
    public void errordianzan(String s) {
        showToast("点赞失败     " + s);
    }

    @Override
    public void successpinglunpinglun(String message) {
        showToast("评论别人的评论成功：" + message);
    }

    @Override
    public void errorpinglunpinglun(String message) {
        showToast("评论别人的评论失败：" + message);
    }

    @Override
    public void successpinglunyingpian(String message) {
        showToast("评论影片成功：" + message);
    }

    @Override
    public void errorpinglunyingpian(String message) {
        showToast("评论影片失败：" + message);
    }

}
