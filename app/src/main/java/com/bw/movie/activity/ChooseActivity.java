package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.MovieTicketBean;
import com.bw.movie.dialog.ChooseDialog;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.TicketPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.view.TicketView;
import com.bw.movie.view.Marquee;
import com.bw.movie.view.SeatTable;
import com.greendao.gen.UserBeanDao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选座页面
 */
public class ChooseActivity extends BaseMVPActivity<TicketView, TicketPresenter> implements TicketView {

    private TextView mChoose_movie_name;
    private TextView mChoose_cinema_name;
    private TextView mChoose_cinema_address;
    private Marquee mChoose_cinema_marquee;
    private String mCinemaName;
    private String mCinemaAddress;
    private String mMovieName;
    private String mMovieHall;
    private String mMovieBegin;
    private String mMovieEnd;
    private Button mCancel;
    private TextView mMovie_price;
    private String mPrice;
    private SeatTable mChoose_cinema_settable;
    private Button mWechat_pay;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private int mScheduleId;
    private String mUserId;
    private String mSessionId;
    private String mOrderId;
    private AlertDialog alertDialog;
    private int checkedItemId=0;//AlertDialog单选列表中默认被选中的item下标;
    private TextView textView;

    @Override
    protected TicketPresenter initPresenter() {
        return new TicketPresenter();
    }

    /**
     * 初始化视图
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_choose;
    }

    /**
     * 初始化资源控件id
     */
    @Override
    protected void initView() {
        mChoose_movie_name = findViewById(R.id.choose_movie_name);
        mChoose_cinema_name = findViewById(R.id.choose_cinema_name);
        mChoose_cinema_address = findViewById(R.id.choose_cinema_address);
        mChoose_cinema_marquee = findViewById(R.id.choose_cinema_marquee);
        mCancel = findViewById(R.id.wechat_cancel);
        mMovie_price = findViewById(R.id.choose_movie_price);
        mChoose_cinema_settable = findViewById(R.id.choose_cinema_settable);
        mWechat_pay = findViewById(R.id.wechat_pay);
        //初始化影院选座页面对应的影院以及影片信息
        initChooseMessage();

        //初始化电影选座
        initSetTable();

        /**
         * 实现下单业务
         */
        mWechat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //前提下单完成    订单号
                initTicket();

            }
        });
    }

    /**
     * 实现下单业务逻辑
     */
    private void initTicket() {
        if (selectedTableCount <= 0){
            return;
        }
        Map<String,String> headerParams = new HashMap<>();
        Map<String,String> queryParams = new HashMap<>();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        mUserId = mUserBeans.get(0).getUserId();
        mSessionId = mUserBeans.get(0).getSessionId();
        headerParams.put("userId",mUserId);
        headerParams.put("sessionId",mSessionId);

        queryParams.put("scheduleId",mScheduleId+"");
        queryParams.put("amount",selectedTableCount+"");
        queryParams.put("sign",EncryptUtil.MD5(mUserId+mScheduleId+selectedTableCount+"movie"));
        presenter.getMovieTicket(headerParams,queryParams);
    }

    /**
     * 加载数据
     */
    @Override
    protected void initData() {

    }


    /**
     * 设置影院选座页面对应的影院以及影片信息
     */
    private void initChooseMessage() {
        mScheduleId = getIntent().getIntExtra("scheduleId",-1);
        mCinemaName = getIntent().getStringExtra("cinemaName");
        mCinemaAddress = getIntent().getStringExtra("cinemaAddress");
        mMovieName = getIntent().getStringExtra("movieName");
        mMovieHall = getIntent().getStringExtra("movieHall");
        mMovieBegin = getIntent().getStringExtra("movieBegin");
        mMovieEnd = getIntent().getStringExtra("movieEnd");
        mPrice = getIntent().getStringExtra("price");
        mPriceWithCalculate = new BigDecimal(mPrice);
        mChoose_cinema_name.setText(mCinemaName);
        mChoose_cinema_address.setText(mCinemaAddress);
        mChoose_movie_name.setText(mMovieName);
        mChoose_cinema_marquee.setText("   "+mMovieBegin+"--"+mMovieEnd +"        "+ mMovieHall );

    }

    /**
     * 设置选座信息
     */
    private void initSetTable() {
        mChoose_cinema_settable.setMaxSelected(3);//设置最多选中
        mChoose_cinema_settable.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==4&&column==5){
                    return true;
                }
                return false;
            }

            /**
             * 选中座位时
             * @param row
             * @param column
             */
            @Override
            public void checked(int row, int column) {
                changePriceWithSelected();
            }

            /**
             * 取消选中的座位
             * @param row
             * @param column
             */
            @Override
            public void unCheck(int row, int column) {
                changePriceWithUnSelected();
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        mChoose_cinema_settable.setData(8,15);
    }

    private BigDecimal mPriceWithCalculate;
    private int selectedTableCount = 0;
    //假的
    //实际需要保存位置信息并给服务端
    //选中座位时价格联动
    private void changePriceWithSelected() {
        selectedTableCount++;
        String currentPrice = mPriceWithCalculate.multiply(new BigDecimal(String.valueOf(selectedTableCount))).toString();
        mMovie_price.setText("￥："+currentPrice +"");
        //计算机：处理浮点数是不精确的1.2 - 02   = 1   =》    0.9999999999
    }

    //取消选座时价格联动
    private void changePriceWithUnSelected() {
        selectedTableCount--;
        String currentPrice = mPriceWithCalculate.multiply(new BigDecimal(String.valueOf(selectedTableCount))).toString();
        mMovie_price.setText("￥："+currentPrice +"");
    }


    /**
     * 设置监听
     */
    @Override
    protected void setListener() {

        //取消选座
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void success(MovieTicketBean movieTicketBeans) {
        mOrderId = movieTicketBeans.getOrderId();

        ChooseDialog chooseDialog = new ChooseDialog();
        Bundle bundle = new Bundle();
        bundle.putString("price",mPrice);
        bundle.putString("orderId",mOrderId);
        chooseDialog.setArguments(bundle);
        chooseDialog.show(getSupportFragmentManager(),"");
    }

    @Override
    public void error() {

    }
}
