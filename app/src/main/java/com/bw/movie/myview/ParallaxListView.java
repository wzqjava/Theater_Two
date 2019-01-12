package com.bw.movie.myview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

/**
 * date:2019/1/12
 * author:赵豪轩(xuan)
 * function:
 *     recyclerview实现视差特效
 *     继承式自定义控件
 *
 */
public class ParallaxListView extends ListView {
    ImageView iv_hrader;
    private int yuanshi;
    private int kongjianyuanshi;

    /**
     * 初始化：创建三个构造方法
     * @param context
     */
    public ParallaxListView(@NonNull Context context) {
        this(context,null);
    }
    public ParallaxListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public ParallaxListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void setView(ImageView iv_header){
        //1.获取控件
        this.iv_hrader = iv_header;
        //2.获取控件中的图片的原始高度
        yuanshi = iv_header.getDrawable().getIntrinsicHeight();

        //3.为了方便我们第三步回弹的操作，获取一下图片控件的原始高度。
        kongjianyuanshi = iv_header.getHeight();
    }

    /**
     * 滑动到listview的两边才会被调用
     * @param deltaX
     * @param deltaY   竖直方向滑动的瞬时变化量,顶部下拉为- ;顶部上拉为+
     * @param scrollX
     * @param scrollY
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX
     * @param maxOverScrollY
     * @param isTouchEvent  是否是用户触摸拉动,true表示用户手指拉动,false是惯性
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        if(deltaY<0 && isTouchEvent){
            //1.获取新的高度
            //由于下拉为负值，所以需要把deltawY设置为绝对值
            int new_heigth = iv_hrader.getHeight() + Math.abs(deltaY);

            //2.判断当图片小于原始高度，图片不能大于原始的高度
            if (new_heigth < yuanshi){
                //把新的高度值负值给控件,改变控件的高度
                iv_hrader.getLayoutParams().height=new_heigth;
                iv_hrader.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
    /**
     * 3.设置触摸事件的监听
     *    当松开手指时：把控件恢复成原始的高度
     *    获取图片现在的高度，然后使用属性动画，
     *    获取动画执行过程中的分度值
     *    获取中间的值,并赋给控件新高度,可以使控件平稳回弹的效果
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            //当我们的手指松开之后，回调
            case MotionEvent.ACTION_UP:
                //首先获取我们当前控件的宽和高
                if (iv_hrader != null){
                    int dangqian = iv_hrader.getHeight();
                    //定义一个属性动画
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(dangqian, kongjianyuanshi);
                    //设置动画更新的监听
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            //获取动画执行过程中的分度值
                            //获取中间的值,并赋给控件新高度,可以使控件平稳回弹的效果
                            Integer animatedValue = (Integer) valueAnimator.getAnimatedValue();
                            //让新的高度值生效
                            iv_hrader.getLayoutParams().height = animatedValue;
                            iv_hrader.requestLayout();
                        }
                    });
                    //动画的回弹效果,值越大,回弹越厉害
                    valueAnimator.setInterpolator(new OvershootInterpolator(2));
                    //设置动画的执行时间,单位值毫秒
                    valueAnimator.setDuration(500);
                    //动画执行
                    valueAnimator.start();
                }

                break;
        }
        return super.onTouchEvent(ev);
    }
}
