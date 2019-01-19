package com.bw.movie.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bw.movie.R;

/**
 * 自绘式开关按钮
 * 1.创建一个类,继承View,才是自绘式自定义控件
 * 2.覆写构造方法,进行对象的初始化操作
 * 3.覆写onMeasure,确定控件的宽高大小
 * 4.覆写onDraw方法,绘制出控件的静态效果
 * 5.实现点击滑动按钮的效果,View类具备点击监听的方法
 * 6.覆写手势监听,实现滑块根据手指滑动而移动
 * 7.根据手指的滑动,当滑动中间时,用户松开手,判断滑动图是自动移动到左边,还是到右边
 * 8.注意我留了一个坑,一个滑动与点击事件冲突的坑,当我写完手势监听时,点击事件就不起效果了
 * 提示:Android实际是没有点击事件,全是触摸事件,ctrl点击onTouchEvent方法,再找到performClick()方法既可以看到
 *
 * 9.自定义属性,使自定义控件可以在XML文件中修改自身的属性
 *      a.在res文件夹下,创建Android resource file文件,选择values,起固定名称attrs.xml文件
 *      b.自定义属性名称,name指的是资源ID,可以进行自定义,注意不要写错,没有任何提示
 *          <declare-styleable name="MyToggleBtn">
                    <attr name="slide_button" format="reference"/>
             </declare-styleable>
        提示:format的引用是固定的,reference:引用   color:颜色    boolean:布尔值    dimension:尺寸值
        float:浮点值      integer:整型值     integer:整型值     String:字符串      enum:枚举值
 *      c.文件创建完以后,在我们的Java代码里要进行设置
        d.布局文件根容器中

 作业:写一个回调,在使用自定义控件的类中调用,根据开关的不同做不同的逻辑.弹个吐司
 */
public class MyToggleButton extends View implements View.OnClickListener {
    private Bitmap slide_button;
    private Bitmap switch_background;
    private Paint paint;

    int slideLeft = 0;
    //B.记录当前开关按钮是开还是关,默认关
    boolean toggleState = true;

    int slideLeftMax;
    //D.用户滑动的起始位置
    int firstX;
    //D.用户滑动的终点位置
    int lastX;
    //F.记录用户当前是点击还是滑动
    boolean isDrop;

    //这三个方法是让你做初始化的业务
    //代码中创建控件对象,自动回调此方法
    public MyToggleButton(Context context) {
        this(context, null);
    }

    //XML布局中使用此自定义控件,自动回调此方法
    public MyToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //XML布局中使用该控件,且带有样式时,自动回调
    public MyToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //G.引用布局XML文件里的定义的属性,AttributeSet attrs就代表了资源对象,注意要把A加载两张背景图的方法注释掉
        //G.怎么引用attrs的文件,我们通过看源码,掌握其使用格式
        //参数1:就是传入AttributeSet attrs即可
        //参数2:把要解析的两个自定义属性的id封装在哪个节点下,对应我们定义的declare-styleable name
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyToggleBtn);
        BitmapDrawable bd1 = (BitmapDrawable) array.getDrawable(R.styleable.MyToggleBtn_slide_button);
        BitmapDrawable bd2 = (BitmapDrawable) array.getDrawable(R.styleable.MyToggleBtn_switch_background);
        //G.加载两张背景图
        slide_button = bd1.getBitmap();
        switch_background = bd2.getBitmap();

        intiView();
    }

    //做控件的初始化
    private void intiView() {
        //A.创建画笔的对象,方便绘制时调用
        paint = new Paint();
        //A.加载两张背景图,转换为Bitmap对象(自定义开关,实际就是这两张图片叠加的效果)
/*        slide_button = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        switch_background = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);*/

        //C.竖线图片在最右方,要得到其左上角的X坐标(绘制图片从左上角开始)=背景图片宽度-竖线图片宽度
        slideLeftMax = switch_background.getWidth() - slide_button.getWidth();

        //B.给自己设置点击侦听
        setOnClickListener(this);
    }

    //A.指定控件的宽高大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //A.指定组件宽高是图片的宽和高
        setMeasuredDimension(switch_background.getWidth(), switch_background.getHeight());
    }

    //A.开始绘制两张图片,实现开关按钮的静态效果
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制两张图片
        canvas.drawBitmap(switch_background, 0, 0, paint);
        //这里X周写死为0,则开关按钮不具备滑动效果
        //canvas.drawBitmap(slide_button,0, 0, paint);
        //B.这里的三道竖图片,能实现滑动的效果是因为X轴随着用户的交互动态的发生改变
        canvas.drawBitmap(slide_button, slideLeft, 0, paint);
    }

    //B.开关按钮的点击事件
    @Override
    public void onClick(View v) {
        //F.先判断用户到底是想点击还是想滑动
        if (!isDrop) {
            //B.如果开关按钮是关,那么道竖图片应该在左边,那么他的X轴在最左边0
            if (toggleState) {
                slideLeft = 0;
            }
            //B.如果开关按钮是开,那边竖道图片应该在右边,绘制图片从左上角开始,因此坐标我们要计算得到
            else {
                slideLeft = slideLeftMax;
            }
            //B.进行状态的值的取反
            toggleState = !toggleState;
        }
        //B.重新调用OnDrawer方法,该方法只能在主线程运行
        invalidate();
    }

    //D.手势监听,返回为true,才能起效果
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //F.解决滑动与点击事件冲突问题
        super.onTouchEvent(event);

        //D.手指画了几个像素,那么图片就绘制到那个地方,要得到手指滑动的距离.
        switch (event.getAction()) {
            //D.手指按下,起点终点都是一个位置
            case MotionEvent.ACTION_DOWN:
                firstX = lastX = (int) event.getX();

                //F.在按下时,状态要进行还原
                isDrop = false;
                break;
            //D.手指移动
            case MotionEvent.ACTION_MOVE:
                //D.得到移动最新的X轴坐标
                int newX = (int) event.getX();

                //D.计算出本次move事件和上一次move事件之间位移的像素
                int offsetX = newX - lastX;//新坐标减去上一次的终点
                //D.把移动距离+原来的距离,就是新的滑动图X轴坐标
                slideLeft = slideLeft + offsetX;

                //D.最新的X轴坐标成了新的终点
                lastX = newX;

                //F.判断当前用户是点击按钮还是在滑动按钮,如果滑动距离小于6，就算是点击
                if (Math.abs(newX - firstX) <= 6) {//这里取绝对值,因为坐标有可能会产生负数
                    isDrop = false;
                } else {
                    //F.如果滑动距离大于6，就算是用户在滑动
                    isDrop = true;
                }
                break;
            //D.手指松开,我们才让滑动图去自动移动
            case MotionEvent.ACTION_UP:

                //F.判断用户是想滑动还是想位移
                if (isDrop) {
                    //E.获取到手指抬起时的X轴坐标
                    int upX = (int) event.getX();
                    //E.检测手指抬起时，滑块的位移距离是否大于slideLeftMax的一半
                    //E.大于一半,往右滑动，
                    if (upX - firstX > slideLeftMax / 2) {
                        //改变开关按钮的状态
                        toggleState = true;
                    }
                    //E.小于一半,往左滑动
                    else if (firstX - upX > slideLeftMax / 2) {
                        toggleState = false;
                    }
                    //E.根据开关状态,改变 slideLeft的值,从而使绘制的X轴坐标发送变化
                    flushViewState();
                }
                break;

        }
        //D.对控件重新进行绘制
        flushViewBound();
        return true;
    }

    //D.对控件重新进行绘制
    private void flushViewBound() {
        //D.为避免滑块滑出,要对滑动坐标进行限制,最左边不能超过0,最右边也不能超
        if (slideLeft < 0) {
            slideLeft = 0;
        } else if (slideLeft > slideLeftMax) {
            slideLeft = slideLeftMax;
        }
        //D.重新调用OnDrawer方法进行绘制
        invalidate();
    }

    //E.根据开关状态,改变 slideLeft的值,从而使绘制的X轴坐标发送变化
    private void flushViewState() {
        if (toggleState) {
            slideLeft = slideLeftMax;
        } else {
            slideLeft = 0;
        }
    }
}
