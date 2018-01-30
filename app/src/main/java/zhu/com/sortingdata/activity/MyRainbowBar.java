package zhu.com.sortingdata.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import zhu.com.sortingdata.MainActivity;


/**
 * Created by Administrator on 2017/11/22.
 */

public class MyRainbowBar extends View {

    //progress bar color
    int barColor = Color.parseColor("#1E88E5");
    //every bar segment width
    int hSpace = 80 ;
    //every bar segment height
    int vSpace = 4 ;
    //space among bars
    int space = 10 ;

    Paint mPaint;
    float startX = 0;
    float delta = 10f;
    int index = 0;
    public MyRainbowBar(Context context) {
        super(context);
    }

    public MyRainbowBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRainbowBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);

        //get screen width
        float sw = this.getMeasuredWidth();
        if (startX >= sw + (hSpace + space) - (sw % (hSpace + space))) {
            startX = 0;
        } else {
            startX += delta;
        }
        float start = startX;
        // draw latter parse
        while (start < sw) {
            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            start += (hSpace + space);
        }
        start = startX - space - hSpace;
        // draw front parse
        while (start >= -hSpace) {
            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            start -= (hSpace + space);
        }
        if (index >= 700000) {
            index = 0;
        }
//        invalidate();
        postInvalidateDelayed(100);
    }
}
