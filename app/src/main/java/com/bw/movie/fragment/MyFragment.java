package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPFragment;
import com.bw.movie.myview.ParallaxListView;
import com.bw.movie.presenter.MyFragmentPresenter;
import com.bw.movie.view.MyFragmentView;

/**
 * date:2019/1/4
 * author:赵豪轩
 * function:
 */
public class MyFragment extends BaseMVPFragment<MyFragmentView,MyFragmentPresenter> {

    private ParallaxListView parallaxlistview;
    private ImageView iv_header;
    public String[] NAMES = new String[]{"宋江", "卢俊义", "吴用",
            "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深",
            "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘",
            "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍",
            " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪",
            "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方",
            "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充",
            "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿",
            "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩",
            "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立",
            "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜",
            "时迁", "段景柱", "易宸锋"};
    @Override
    protected MyFragmentPresenter initPresenter() {
        return new MyFragmentPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.my_fragment;
    }

    /**
     * 初始化View
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        parallaxlistview = view.findViewById(R.id.parallaxlistview);
    }

    /**
     * 初始化数据
     *
     * @param view
     */
    @Override
    protected void initData(View view) {
        //获取外部的布局
        View head = View.inflate(getContext(), R.layout.myfragment_layout_header, null);
       //给listview添加头布局
        parallaxlistview.addHeaderView(head);
        //获取头布局中的图片控件
        iv_header = head.findViewById(R.id.iv_header);

        /**
         * 我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，
         * 这是因为View组件布局要在onResume回调后完成。
         * 所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度
         * 。这是获得一个view的宽度和高度的方法之一。
         * OnGlobalLayoutListener 是ViewTreeObserver的内部类，
         * 当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，
         * 这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。
         * ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。
         *
         * 但是需要注意的是OnGlobalLayoutListener可能会被多次触发，因此在得到了高度之后，要将OnGlobalLayoutListener注销掉。
         */
        iv_header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //把控件传到自定义
                parallaxlistview.setView(iv_header);
                //释放资源
                iv_header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        parallaxlistview.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,NAMES));
    }

    /**
     * 设置监听
     *
     * @param view
     */
    @Override
    protected void setListener(View view) {

    }
}
