package com.bw.movie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * date:2019/1/11
 * author:李壮（大壮）
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
    private int cellWidth;
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

    //A.完成侧拉索引的测量,得到单元格的宽高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件的宽高
        int mHeight = getMeasuredHeight();
        cellWidth = getMeasuredWidth();

        //获取单元格的高度,由自定义控件总高度,除以所有字母所占用的高度
        cellHeight = mHeight * 1.0f / LETTERS.length;//为了精确,避免四舍五入,我们把数转换为小数
    }

    //A.完成侧拉索引
    @Override
    protected void onDraw(Canvas canvas) {

        //遍历了26个字母,进行坐标计算,进行绘制
        for(int i =0 ; i<LETTERS.length ;i++){
            //从数组,根据i取出字母
            String letter = LETTERS[i];

            //计算X坐标
            float x = cellWidth * 0.5f - paint.measureText(letter) * 0.5f;

            //计算Y坐标
            float y = cellHeight * 0.5f + paint.measureText(letter) * 0.5f +i*cellHeight;

            canvas.drawText(letter,x,y,paint);
        }
    }


}
