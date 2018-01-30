package zhu.com.sortingdata.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import zhu.com.sortingdata.MainActivity;


/**
 * Created by Administrator on 2017/11/15.
 */

public class MyChangeArcView  extends View {
    //圈上红点的画笔
    private Paint smallCircle;

    //内部背景色圆
    private Paint inUnderCircle;
    //粉色进度条的圆弧
    private Paint outOnArcLine;
    //外层底部灰色的圆画笔
    private Paint outUnderLine;
    //中间文字画笔
    private Paint underText;
    //利率数字画笔
    private Paint numPaint;
    //百分号的画笔
    private Paint fuHaoPaint;
    //底部员的线宽度
    private final int OUTCIRCLEWIDTH = 4;
    //原点半径
    private final int SMALLCIRCLERADIUS = 5;
    //底部圆弧画笔的半径
    private float outCircleRadius = 0;
    private int viewWIdth = 0;
    private int viewHeight = 0;
    //圆弧初始角度
    private final float STARTPOINT = 270;
    //圆弧转动的角度数值
    private float CURRENTPOINT = 0;
    //圆弧转动的结束值
    private float CURRENTFIANLPOINT = 0;
    //一圈的总值
    private int totalAmount = 100;
    //转动的值
    private int canUserAmount = 0;
    private float amountPR = 360;
    private final double PI = 3.14;
    //小红点的转动的X Y 坐标
    private float X = 0, Y = 0;

    private int userNum = 0;

    public MyChangeArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyChangeArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyChangeArcView(Context context) {
        super(context);
    }

    //传入中间的数值
    public void setNum(int canUserAmount) {
        this.canUserAmount = canUserAmount;
        amountPR = 100 / 360f;
        CURRENTFIANLPOINT = canUserAmount / amountPR;
    }


    public void initPaint() {
        //最外底部圆初始化paint
        outUnderLine = new Paint();
        outUnderLine.setColor(Color.parseColor("#D8D9D9"));
        outUnderLine.setStyle(Paint.Style.STROKE);
        outUnderLine.setStrokeWidth(OUTCIRCLEWIDTH);
        outUnderLine.setStrokeCap(Paint.Cap.ROUND);
        outUnderLine.setAntiAlias(true);
        //初始化进度圆弧paint
        outOnArcLine = new Paint();
        outOnArcLine.setColor(Color.parseColor("#FF524E"));
        outOnArcLine.setStyle(Paint.Style.STROKE);
        outOnArcLine.setStrokeWidth(OUTCIRCLEWIDTH);
        outOnArcLine.setStrokeCap(Paint.Cap.ROUND);
        outOnArcLine.setAntiAlias(true);
        //初始化内部圆
        inUnderCircle = new Paint();
        inUnderCircle.setColor(Color.parseColor("#FF524E"));
        inUnderCircle.setStyle(Paint.Style.FILL);
        inUnderCircle.setAntiAlias(true);
        //初始化文字画笔
        underText = new Paint();
        underText.setColor(Color.WHITE);
        underText.setStyle(Paint.Style.FILL);
        underText.setStrokeWidth(1);
        underText.setTextSize(30);
        underText.setAntiAlias(true);

        //数字画笔
        numPaint = new Paint();
        numPaint.setColor(Color.WHITE);
        numPaint.setStyle(Paint.Style.FILL);
        numPaint.setStrokeWidth(1);
        numPaint.setTextSize(85);
        numPaint.setAntiAlias(true);

        //% 画笔
        fuHaoPaint = new Paint();
        fuHaoPaint.setColor(Color.WHITE);
        fuHaoPaint.setStyle(Paint.Style.FILL);
        fuHaoPaint.setStrokeWidth(1);
        fuHaoPaint.setTextSize(50);
        fuHaoPaint.setAntiAlias(true);


        smallCircle = new Paint();
        smallCircle.setColor(Color.parseColor("#FF524E"));
        smallCircle.setStyle(Paint.Style.FILL);
        smallCircle.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWIdth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        canvas.translate(viewWIdth / 2, viewHeight / 2);
        //最外层圆弧的半径
        outCircleRadius = (viewWIdth >= viewHeight ? viewHeight : viewWIdth) / 11.0f * 4.0f;
        RectF rectF = new RectF(-outCircleRadius, -outCircleRadius, outCircleRadius, outCircleRadius);

        //中间背景圆
        canvas.drawCircle((-outCircleRadius + outCircleRadius) / 2f, (-outCircleRadius + outCircleRadius) / 2f, outCircleRadius / 1.11f, inUnderCircle);
        //外圈底部灰色圆
        canvas.drawCircle((-outCircleRadius + outCircleRadius) / 2.0f, (-outCircleRadius + outCircleRadius) / 2.0f, outCircleRadius, outUnderLine);

        //进度圆弧
        canvas.drawArc(rectF, STARTPOINT, CURRENTPOINT, false, outOnArcLine);
        double radius = (double) (STARTPOINT + CURRENTPOINT ) / 180.00 * Math.PI;
        float smallCircleX = 0;
        float smallCirlceY = 0;

        if (CURRENTPOINT >= 0) {
            smallCircleX = (float) (outCircleRadius * Math.cos(radius));
            smallCirlceY = (float) (outCircleRadius * Math.sin(radius));
        }
        if (CURRENTPOINT >= 90) {
            smallCircleX = (float) (outCircleRadius * Math.cos(radius));
            smallCirlceY = (float) (outCircleRadius * Math.sin(radius));
        }
        if (CURRENTPOINT >= 180) {
            smallCircleX = (float) (outCircleRadius * Math.cos(radius));
            smallCirlceY = (float) (outCircleRadius * Math.sin(radius));
        }
        if (CURRENTPOINT >= 270) {
            smallCircleX = (float) (outCircleRadius * Math.cos(radius));
            smallCirlceY = (float) (outCircleRadius * Math.sin(radius));
        }

        //小圆点
        canvas.drawCircle(smallCircleX, smallCirlceY, SMALLCIRCLERADIUS, smallCircle);

        String str = "自定义文字";
        String fuhao = "%";
        float strLength = underText.measureText(str);
        float numLength = numPaint.measureText(String.valueOf(canUserAmount));
        float fuhaoLength = fuHaoPaint.measureText(fuhao);
        canvas.drawText(str, -strLength / 2f, outCircleRadius / 2.0f, underText);
        canvas.drawText(String.valueOf(userNum), -numLength / 1.3f * 1.0f, -outCircleRadius / 6.0f * 0.1f, numPaint);
        canvas.drawText(fuhao, fuhaoLength / 1f * 0.7f, -outCircleRadius / 4.0f * 0.1f, fuHaoPaint);

        if (userNum < canUserAmount) {
            userNum += 1;
            CURRENTPOINT += 3.6;
            postInvalidateDelayed(20);
        } else if (userNum > canUserAmount) {
            userNum = canUserAmount;
            postInvalidateDelayed(20);
        }

    }
}