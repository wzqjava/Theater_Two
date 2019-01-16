package com.bw.movie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * date:2017/8/14
 * author:易宸锋(dell)
 * function:快速索引栏实现思路
 * 1.继承View,覆写构造方法,初始化画笔
 * 2.在OnDrawer方法里绘制字符
 * 3.在onMeasure方法里测量高度
 * 4.在OntouchEvent事件知道用户具体按住了哪个字母
 * 5.定义抽象方法,实现监听回调
 */
public class QuickIndexBar extends View {
    //A.要绘制的内容
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };

    private Paint paint;
    private float y;
    private int currentIndex;

    //A.单元格宽度
    private int cellWidth;
    //A.单元格高度
    private float cellHeight;

    //A.构造方法
    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        intiPaint();
    }

    private void intiPaint() {
        //创建一个抗锯齿的画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画笔文本加粗
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //颜色
        paint.setColor(Color.WHITE);
    }

    //A.完成侧边索引的测量,得到单元格的宽高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件的宽高
        int mHeight = getMeasuredHeight();
        cellWidth = getMeasuredWidth();

        //获取单元格的高度,由自定义控件总高度,除以所有字母所占用的高度
        cellHeight = mHeight * 1.0f / LETTERS.length;//为了精确,避免四舍五入,我们把数转换为小数
    }

    //A.完成侧边索引
    @Override
    protected void onDraw(Canvas canvas) {

        //遍历了26个字母,进行坐标计算,进行绘制
        for (int i = 0; i < LETTERS.length; i++) {
            //从数组,根据i取出字母
            String letter = LETTERS[i];

            //计算X坐标
            float x = cellWidth * 0.5f - paint.measureText(letter) * 0.5f;

            //计算Y坐标
            float y = cellHeight * 0.5f + paint.measureText(letter) * 0.5f + i * cellHeight;
            // 绘制文本
            canvas.drawText(letter, x, y, paint);
        }
    }

    //B.记录用户上一次按下的位置,以便进行判断这一次所按住的位置是否还是上一次的位置,如果是的话,不做任何处理
    private int lastIndex = -1;

    //B.重写触摸事件,返回值为True ,方起效果
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //计算用户按到哪个字母的范围,主要是Y周
            case MotionEvent.ACTION_DOWN://按下时,回调
            case MotionEvent.ACTION_MOVE://移动时,回调
                //获取被点击到的字母索引
                y = event.getY();
//                System.out.println("y值"+y);
                currentIndex = (int) (y / cellHeight);
//                System.out.println("按住了第几个单元格"+currentIndex);
                //为了防止一个字母按住,不停的重复调用,讲进行判断,判断是否还是按着上一个字母,是的话,就不做任何处理,提高程序的性能
                if (currentIndex != lastIndex) {
                    //为了防止角标越界
                    if (currentIndex >= 0 && currentIndex < LETTERS.length) {
                        String letter = LETTERS[currentIndex];
                        //C.设置回调的监听
                        if (mOnLetterUpdateListener != null) {
                            mOnLetterUpdateListener.onLetterUpdate(letter);
                        }
                        lastIndex = currentIndex;
                    }
                }
                break;
            case MotionEvent.ACTION_UP://松开时,回调
                break;
        }
        return true;
    }

    //C.定义接口
    public interface OnLetterUpdateListener {
        void onLetterUpdate(String letter);
    }

    //C.定义接口对象
    private OnLetterUpdateListener mOnLetterUpdateListener;

    //C.暴露方法,让外界传过来一个实现接口的类对象
    public void setOnLetterUpdateListener(OnLetterUpdateListener onLetterUpdateListener) {
        mOnLetterUpdateListener = onLetterUpdateListener;
    }


}
