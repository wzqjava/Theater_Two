package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.DetailRecyclerviewPingLunAdapter;
import com.bw.movie.adapter.Detail_Recyclerview_Img;
import com.bw.movie.adapter.Detail_Recyclerview_Movie;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.DetailPresenter;
import com.bw.movie.view.DetailView;
import com.greendao.gen.UserBeanDao;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

public class DeatilActivity extends BaseMVPActivity<DetailView, DetailPresenter> implements DetailView{

    private TextView detail_name;
    private ImageView detail_img;
    private Button detail_detail;
    private Button detail_yugao;
    private Button detail_juzhao;
    private Button detail_yingping;
    private ImageView detail_fanhui;
    private Button detail_goumai;

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
    private TextView detail_button2_name ;
    private RecyclerView detail_button2_recyclerview;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private Detail_Recyclerview_Movie detail_recyclerview_movie;

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

        detail_button_img = detail_onebutton.findViewById(R.id.detail_button_img);
        detail_button_down = detail_onebutton.findViewById(R.id.detail_button_down);
        detail_button_detail_one = detail_onebutton.findViewById(R.id.detail_button_detail_one);
        detail_button_detail_two  =  detail_onebutton.findViewById(R.id.detail_button_detail_two);
        detail_button_detail_three =  detail_onebutton.findViewById(R.id.detail_button_detail_three);
        detail_button_detail_four =  detail_onebutton.findViewById(R.id.detail_button_detail_four);
        detail_button_jianjie =   detail_onebutton.findViewById(R.id.detail_button_jianjie);
        detail_button_name_one  =  detail_onebutton.findViewById(R.id.detail_button_name_one);
        detail_button_name_two  =  detail_onebutton.findViewById(R.id.detail_button_name_two);
        detail_button_name_three  =  detail_onebutton.findViewById(R.id.detail_button_name_three);
        detail_button_name_four  =  detail_onebutton.findViewById(R.id.detail_button_name_four);


        detail_button2_down =  detail_twobutton.findViewById(R.id.detail_button2_down);
        detail_button2_name =  detail_twobutton.findViewById(R.id.detail_button2_name);
        detail_button2_bianji = detail_twobutton.findViewById(R.id.detail_button2_bianji);
        detail_button2_recyclerview =  detail_twobutton.findViewById(R.id.detail_button2_recyclerview);
    }
    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        detail_onebutton.setVisibility(View.INVISIBLE);
        detail_twobutton.setVisibility(View.INVISIBLE);
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
            }
        });
        detail_button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.INVISIBLE);
                detail_goumai.setVisibility(View.VISIBLE);
            }
        });
        detail_button2_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.INVISIBLE);
                detail_goumai.setVisibility(View.VISIBLE);
                
            }
        });
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, DeatilActivity.class));
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void even(final MovieFragmentBean.ResultBean resultBean) {
        Log.e("zhx", resultBean.getName() + "");
        detail_name.setText(resultBean.getName() + "");
        Glide.with(this).load(resultBean.getImageUrl() + "").into(detail_img);
        //影片的id
        final int id = resultBean.getId();



            mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
            mUserBeans = mUserBeanDao.loadAll();
            HashMap<String, String> map = new HashMap<>();
            map.put("movieId",String.valueOf(id));
            Log.e("zzz",mUserBeans.get(0).getUserId()+"   "+mUserBeans.get(0).getSessionId()+"");
            //详情展示
            presenter.getdetailData(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);

            HashMap<String, String> map1 = new HashMap<>();
            map1.put("movieId",String.valueOf(id));
            map1.put("page","1");
            map1.put("count","10");

            presenter.getPingLunData(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map1);

          // Log.e("zzz","");
        detail_goumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeatilActivity.this, SelectTheatersActivity.class);
                intent.putExtra("name",resultBean.getName()+"");
                intent.putExtra("id",id+"");
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        detail_button_jianjie.setText(resultBean.getSummary());
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

                List<Detail_Detail_Bean.ResultBean.ShortFilmListBean> shortFilmList = resultBean.getShortFilmList();
                //播放视频
                detail_button2_recyclerview.setLayoutManager(new LinearLayoutManager(DeatilActivity.this,LinearLayoutManager.VERTICAL,false));

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

                List<String> posterList = resultBean.getPosterList();
                detail_button2_recyclerview.setHasFixedSize(true);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                detail_button2_recyclerview.setLayoutManager(layoutManager);
                Detail_Recyclerview_Img detail_recyclerview_img = new Detail_Recyclerview_Img(DeatilActivity.this,posterList);

                detail_button2_recyclerview.setAdapter(detail_recyclerview_img);
            }
        });


    }

    @Override
    public void error(String msg) {
        showToast("影片详情的"+msg+"");
    }

    @Override
    public void suucessPingLun(final List<DetailPingLunBean.ResultBean> result) {

        Log.e("xxxxxx",result.get(0).getCommentContent()+"");

         detail_yingping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_button2_name.setText("影评");
                detail_onebutton.setVisibility(View.INVISIBLE);
                detail_twobutton.setVisibility(View.VISIBLE);
                detail_goumai.setVisibility(View.INVISIBLE);
                //com_icon_publish_default
                detail_button2_bianji.setVisibility(View.VISIBLE);

                DetailRecyclerviewPingLunAdapter adapter = new DetailRecyclerviewPingLunAdapter(DeatilActivity.this, result);
                detail_button2_recyclerview.setLayoutManager(new LinearLayoutManager(DeatilActivity.this,LinearLayoutManager.VERTICAL,false));
                detail_button2_recyclerview.setAdapter(adapter);

            }
        });
    }
    @Override
    public void errorPingLun(String msg) {
        showToast(msg+"");
        Log.e("zhx","影片评论的："+"影片评论的");
    }
}
