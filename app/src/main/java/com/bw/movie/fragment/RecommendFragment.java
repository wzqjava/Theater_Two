package com.bw.movie.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailActivity;
import com.bw.movie.adapter.CinemaRecommendAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.LatitudeLongitudeBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.AttentionPresenter;
import com.bw.movie.presenter.RecommendPresenter;
import com.bw.movie.view.AttentionView;
import com.bw.movie.view.RecommendView;
import com.greendao.gen.UserBeanDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * date:2019/1/4
 * author:李壮(大壮)
 * function:推荐影院页面
 */
public class RecommendFragment extends BaseFragment implements RecommendView,AttentionView, Consumer<LatitudeLongitudeBean> {

    private XRecyclerView mCinema_Recommend_RecyclerView;
    private CinemaRecommendAdapter mCinemaRecommendAdapter;
    private RecommendPresenter mRecommendPresenter;
    private Map<String, String> mHeaderParams = new HashMap<>();
    private Map<String, String> mQueryParams = new HashMap<>();
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private Intent mIntent;
    private AttentionPresenter mAttentionPresenter;

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.cinema_recommend_fragment;
    }

    /**
     * 初始化控件资源id
     * @param view
     */
    @Override
    protected void initView(View view) {
        mCinema_Recommend_RecyclerView = view.findViewById(R.id.cinema_recommend_recyclerview);

        //创建适配器
        mCinemaRecommendAdapter = new CinemaRecommendAdapter(getActivity());

        //创建关注与取消关注的Presenter
        mAttentionPresenter = new AttentionPresenter();
        //设置布局管理器
        mCinema_Recommend_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));
        mCinema_Recommend_RecyclerView.setAdapter(mCinemaRecommendAdapter);
        mCinema_Recommend_RecyclerView.setPullRefreshEnabled(true);
        mCinema_Recommend_RecyclerView.setLoadingMoreEnabled(true);
        mCinema_Recommend_RecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                buildLatitudeLongitude();
                mRecommendPresenter.refreshData(mHeaderParams,mQueryParams);
            }

            @Override
            public void onLoadMore() {
                buildLatitudeLongitude();
                mRecommendPresenter.loadData(mHeaderParams,mQueryParams);
            }
        });
    }

    /**
     * 获取经纬度
     */
    private void buildLatitudeLongitude(){
        if(mLatitudeLongitudeBean != null) {
            mQueryParams.put("longitude", String.valueOf(mLatitudeLongitudeBean.getLongitude()));
            mQueryParams.put("latitude", String.valueOf(mLatitudeLongitudeBean.getLatitude()));
        }
    }

    /**
     * 加载数据
     * @param view
     */
    @Override
    protected void initData(View view) {
        /**
         * 初始化P层
         */
        mRecommendPresenter = new RecommendPresenter();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        mHeaderParams.put("userId",mUserBeans.get(0).getUserId());
        mHeaderParams.put("sessionId",mUserBeans.get(0).getSessionId());
        mRecommendPresenter.attch(this);
        mAttentionPresenter.attch(this);
        //从外面过来
        buildLatitudeLongitude();
        mRecommendPresenter.refreshData(mHeaderParams,mQueryParams);
        mIntent = new Intent(getActivity(),CinemaDetailActivity.class);
        mCinemaRecommendAdapter.setCinemaClickListener(new CinemaRecommendAdapter.CinemaClickListener() {
            @Override
            public void cinemaClick(int id) {
                Toast.makeText(getActivity(), "影院id" + id, Toast.LENGTH_SHORT).show();
                mIntent.putExtra("id",String.valueOf(id));
                startActivity(mIntent);
            }
        });

        mCinemaRecommendAdapter.setCinemaAttentionClickListener(new CinemaRecommendAdapter.CinemaAttentionClickListener() {
            @Override
            public void cinemaAttentionClick(int position) {
                RecommendBean.ResultBean iten = mCinemaRecommendAdapter.getIten(position - 1);
                mAttentionPresenter.getAttention(mHeaderParams,position - 1,iten.isFollowCinema(),iten.getId()+"");
            }
        });
    }


    /**
     * 设置监听事件
     * @param view
     */
    @Override
    protected void setListener(View view) {

    }

    @Override
    public void success(boolean isRefresh,List<RecommendBean.ResultBean> resultBeans) {
        if (isRefresh){
            mCinemaRecommendAdapter.setData(resultBeans);
        }else {
            mCinemaRecommendAdapter.addData(resultBeans);
        }

    }


    @Override
    public void successGZ(int position) {
        Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
        mCinemaRecommendAdapter.changeItenAttentionStatus(position);
    }

    @Override
    public void reeorGZ(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onloadComplete() {
        mCinema_Recommend_RecyclerView.refreshComplete();
        mCinema_Recommend_RecyclerView.loadMoreComplete();
    }

    public void initPublishSubject(PublishSubject<LatitudeLongitudeBean> publishSubject) {
        publishSubject.subscribe(this);
    }

    private LatitudeLongitudeBean mLatitudeLongitudeBean = null;
    @Override
    public void accept(LatitudeLongitudeBean latitudeLongitudeBean) throws Exception {
        mLatitudeLongitudeBean = latitudeLongitudeBean;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAttentionPresenter.detach();
        mRecommendPresenter.detach();
    }
}
