package com.bw.movie.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.ClassfyReMenAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.ClassfyPresenter;
import com.bw.movie.view.ClassfyGuanZhuView;
import com.bw.movie.view.ClassfyView;
import com.greendao.gen.UserBeanDao;

import java.util.HashMap;
import java.util.List;

public class ClassifyActivity extends BaseMVPActivity<ClassfyView, ClassfyPresenter> implements ClassfyView{

    private Button classfy_remen;
    private Button classfy_zhengzai;
    private Button classfy_jijiang;
    private RecyclerView classfy_recyclerview;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private HashMap<String, String> map;
    private RadioGroup radiogroup;
    private ImageView classfy_fanhui;
    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected ClassfyPresenter initPresenter() {
        return new ClassfyPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_classify;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        classfy_remen = findViewById(R.id.classfy_remen);
        classfy_zhengzai = findViewById(R.id.classfy_zhengzai);
        classfy_jijiang = findViewById(R.id.classfy_jijiang);
        classfy_recyclerview = findViewById(R.id.classfy_recyclerview);
        classfy_fanhui = findViewById(R.id.classfy_fanhui);
        radiogroup = findViewById(R.id.radiogroup);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        map = new HashMap<>();
        map.put("page","1");
        map.put("count","10");
        presenter.classFyRemen(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
        String name = getIntent().getStringExtra("name");
        if (name.equals("1")){
            radiogroup.check(R.id.classfy_remen);
        }else if (name.equals("2")){
            radiogroup.check(R.id.classfy_zhengzai);
            presenter.classFyZhengZai(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
        }else if (name.equals("3")){
            radiogroup.check(R.id.classfy_jijiang);
            presenter.classFyJiJiang(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
        }
    }

    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        classfy_remen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.classFyRemen(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
            }
        });
        classfy_zhengzai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.classFyZhengZai(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
            }
        });
        classfy_jijiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.classFyJiJiang(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
            }
        });
        classfy_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void successsReMen(List<MovieFragmentBean.ResultBean> result) {
        final ClassfyReMenAdapter classfyReMenAdapter = new ClassfyReMenAdapter(this, result);
        classfy_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        classfy_recyclerview.setAdapter(classfyReMenAdapter);
        classfyReMenAdapter.setClassfyGuanZhuView(new ClassfyGuanZhuView() {
            @Override
            public void quXiao(String id,String i) {
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("movieId",id+"");
                presenter.quXiao(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(), map1);
                classfyReMenAdapter.list.get(Integer.valueOf(i)).setFollowMovie(2);
                classfyReMenAdapter.notifyDataSetChanged();
            }
            @Override
            public void guanZhu(String id , String i) {
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("movieId",id+"");
                presenter.GuanZhu(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(), map1);
                classfyReMenAdapter.list.get(Integer.valueOf(i)).setFollowMovie(1);
                classfyReMenAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void successsZhengZai(List<MovieFragmentBean.ResultBean> result) {
        final ClassfyReMenAdapter classfyReMenAdapter = new ClassfyReMenAdapter(this, result);
        classfy_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        classfy_recyclerview.setAdapter(classfyReMenAdapter);
        classfyReMenAdapter.setClassfyGuanZhuView(new ClassfyGuanZhuView() {
            @Override
            public void quXiao(String id,String i) {
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("movieId",id+"");
                presenter.quXiao(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(), map1);
                classfyReMenAdapter.list.get(Integer.valueOf(i)).setFollowMovie(2);
                classfyReMenAdapter.notifyDataSetChanged();
            }
            @Override
            public void guanZhu(String id , String i) {
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("movieId",id+"");
                presenter.GuanZhu(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(), map1);
                classfyReMenAdapter.list.get(Integer.valueOf(i)).setFollowMovie(1);
                classfyReMenAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void successsJiJiang(List<MovieFragmentBean.ResultBean> result) {
        final ClassfyReMenAdapter classfyReMenAdapter = new ClassfyReMenAdapter(this, result);
        classfy_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        classfy_recyclerview.setAdapter(classfyReMenAdapter);
        classfyReMenAdapter.setClassfyGuanZhuView(new ClassfyGuanZhuView() {
            @Override
            public void quXiao(String id,String i) {
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("movieId",id+"");
                presenter.quXiao(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(), map1);
                classfyReMenAdapter.list.get(Integer.valueOf(i)).setFollowMovie(2);
                classfyReMenAdapter.notifyDataSetChanged();
            }
            @Override
            public void guanZhu(String id , String i) {
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("movieId",id+"");
                presenter.GuanZhu(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(), map1);
                classfyReMenAdapter.list.get(Integer.valueOf(i)).setFollowMovie(1);
                classfyReMenAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void errorReMen(String msg) {
        showToast("热门："+msg+"");
    }

    @Override
    public void errorZhengZai(String msg) {
        showToast("正在："+msg+"");
    }

    @Override
    public void errorJiJiang(String msg) {
        showToast("即将："+msg+"");
    }

    @Override
    public void successquxiao(String message) {
        showToast("取消关注："+message);
    }
    @Override
    public void errorquxiao(String message) {
        showToast("取消关注错误信息："+message);
    }

    @Override
    public void successguanzhu(String message) {
        showToast("关注："+message);

    }

    @Override
    public void errorguanzhu(String message) {
        showToast("关注错误信息："+message);
    }
}
