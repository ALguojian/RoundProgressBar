package com.guojian.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Hello on 2017/9/29.
 * 自定义圆形进度条
 */

public class RoundProgressBar extends View {


    private Paint paint;//画笔对象
    private int width;//view宽度
    private int height;//view高度
    private int defaultWidthAndHeight = 0;//默认宽高
    private int padding = 0;//默认padding值
    private int roundColor;//圆环颜色
    private int roungProgressColor;//进度条颜色
    private int textColor;//文字颜色
    private int numColor;//数字颜色
    private float testSize;//字体大小
    private float numSize;//数字大小
    private float roundWidth;//圆环宽度
    private int maxLong;//最大值
    private int progress;//进度值
    private boolean textShow;//是否显示文字
    private int style;//进度条样式
    public static final int STROKE = 0;//空心样式
    public static final int FILL = 1;//实心样式
    private OnProgressListener onProgressListener;//进度接口
    private int center;//圆环中心
    private int radius;//圆环半径
    private float margeSize;//距离大小
    private String textName;//距离大小


    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, @Nullable AttributeSet attrs) {
        //初始化默认宽高值
        defaultWidthAndHeight = dpTopx(100);

        //初始化属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        roundColor = typedArray.getColor(R.styleable.RoundProgressBar_round_color, Color.BLACK);

        roungProgressColor = typedArray.getColor(R.styleable.RoundProgressBar_round_Progress_color, Color.BLUE);

        textColor = typedArray.getColor(R.styleable.RoundProgressBar_text_name_color, Color.YELLOW);

        numColor = typedArray.getColor(R.styleable.RoundProgressBar_num_color, Color.YELLOW);

        testSize = typedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);

        margeSize = typedArray.getDimension(R.styleable.RoundProgressBar_margeSize, 30);

        numSize = typedArray.getDimension(R.styleable.RoundProgressBar_numSize, 15);

        textName = typedArray.getString(R.styleable.RoundProgressBar_textName);

        roundWidth = typedArray.getDimension(R.styleable.RoundProgressBar_round_width, 5);

        maxLong = typedArray.getInteger(R.styleable.RoundProgressBar_maxLong, 100);

        textShow = typedArray.getBoolean(R.styleable.RoundProgressBar_showText, true);

        style = typedArray.getInt(R.styleable.RoundProgressBar_style, 0);

        typedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        center = getWidth() / 2;
        radius = (int) (center - roundWidth / 2);

        drawCircle(canvas); //绘制外层圆环
        drawText(canvas);//绘制文本内容
        drawProgressBar(canvas);

    }

    /**
     * 绘制圆环进度条
     *
     * @param canvas
     */
    private void drawProgressBar(Canvas canvas) {
        //初始化画笔
        paint = new Paint();
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roungProgressColor);

        //空心圆环
        RectF stroke_one = new RectF(center - radius, center - radius, center + radius, center + radius);

        RectF stroke_two = new RectF(center - radius + roundWidth + padding,
                center - radius + roundWidth + padding,
                center + radius - roundWidth - padding,
                center + radius - roundWidth - padding);

        paint.setStrokeCap(Paint.Cap.ROUND);

        switch (style) {

            case STROKE:

                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(stroke_one, -90, 360 * progress / 100, false, paint);
                break;

            case FILL:

                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0)
                    canvas.drawArc(stroke_two, -90, 360 * progress / 100, true, paint);
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高的mode和size
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量宽度
        if (widthMode == MeasureSpec.AT_MOST)
            width = defaultWidthAndHeight;
        else
            width = widthSize;

        //测量高度
        if (heightMode == MeasureSpec.AT_MOST)
            height = defaultWidthAndHeight;
        else
            height = heightSize;

        //设置测量的宽高值
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //确定view的宽高
        width = w;
        height = h;
        //初始化padding，默认值
        padding = dpTopx(5);
    }

    /**
     * 获取最大进度值
     *
     * @return
     */
    public synchronized int getMaxLong() {

        return maxLong;
    }

    /**
     * 设置最大进度值
     *
     * @param maxLong
     */
    public synchronized void setMaxLong(int maxLong) {
        if (maxLong < 0) {
            throw new IllegalArgumentException("最大进度是0");
        }
        this.maxLong = maxLong;
    }


    /**
     * 获得当前进度值
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度值
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {

        if (progress < 0)
            throw new IllegalArgumentException("进度值是0");

        if (progress > maxLong)
            progress = maxLong;

        if (progress <= maxLong) {
            this.progress = progress;
            postInvalidate();
        }

        if (progress == maxLong)
            if (onProgressListener != null)
                onProgressListener.progressListener();

    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoungProgressColor() {
        return roungProgressColor;
    }

    public void setRoungProgressColor(int roungProgressColor) {
        this.roungProgressColor = roungProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getnumColor() {
        return numColor;
    }

    public void setnumColor(int textColor) {
        this.numColor = textColor;
    }

    public float getTestSize() {
        return testSize;
    }

    public void setTestSize(float testSize) {
        this.testSize = testSize;
    }

    public float getnumSize() {
        return numSize;
    }

    public void setnumSize(float testSize) {
        this.numSize = testSize;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String testSize) {
        this.textName = testSize;
    }

    public float getMargeSize() {
        return margeSize;
    }

    public void setMargeSize(float testSize) {
        this.margeSize = testSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }


    /**
     * 绘制文本内容
     */
    private void drawText(Canvas canvas) {


        //初始化画笔
        paint = new Paint();
        paint.setStrokeWidth(0);//设置stroke宽度
        paint.setColor(textColor);//设置绘制字体的颜色
        paint.setTextSize(testSize);
        paint.setTypeface(Typeface.DEFAULT);//设置文字的style

        float textWidth = paint.measureText(textName);
        //绘制文本 会根据设置的是否显示文本的属性&是否是Stroke的样式进行判断
        if (textShow && style == STROKE)
            canvas.drawText(textName, center - textWidth / 2, center + margeSize + paint.getTextSize() / 2, paint);


//        //初始化画笔
//        paint = new Paint();
//        paint.setStrokeWidth(0);//设置stroke宽度
//        paint.setColor(numColor);//设置绘制字体的颜色
//        paint.setTextSize(numSize);
//        paint.setTypeface(Typeface.DEFAULT);//设置文字的style

        int percent = (int) (((float) progress / (float) maxLong) * 100);//得到进度值

        float textWidth2 = paint.measureText(percent + "/" + maxLong);


        //绘制文本 会根据设置的是否显示文本的属性&是否是Stroke的样式进行判断
        if (textShow && percent != 0 && style == STROKE)
            canvas.drawText(percent + "/" + maxLong, center - textWidth2 / 2, center - margeSize, paint);

    }

    /**
     * 绘制外层圆环
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        //初始化画笔
        paint = new Paint();
        paint.setColor(roundColor);//设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//设置画笔样式
        paint.setStrokeWidth(roundWidth);//设置STROKE的宽度
        paint.setAntiAlias(true);//设置抗锯齿
        canvas.drawCircle(center, center, radius, paint);//绘制圆形
    }


    /**
     * dp转化px
     */
    public int dpTopx(int dp) {

        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }


    /**
     * 进度调度接口
     */
    public interface OnProgressListener {
        void progressListener();
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }
}
