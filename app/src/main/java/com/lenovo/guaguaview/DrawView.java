package com.lenovo.guaguaview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private PorterDuffXfermode xFreClear;
    private PorterDuffXfermode xFerDraw;
    private Path mPath;

    public DrawView(Context context) {
        this(context,null);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(30);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);


        xFerDraw=new PorterDuffXfermode(PorterDuff.Mode.SRC);
        xFreClear=new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        mPaint.setXfermode(xFerDraw);

        mBitmap=Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(mBitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                    if (mPath==null){
                        mPath=new Path();
                    }
                    mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                mCanvas.drawPath(mPath,mPaint);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
