package com.lenovo.guaguaview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GuaView extends View {
    private Paint mpiant;
    private Path mpath;
    private Bitmap mDownImage;
    private Bitmap mUpImage;
    private Canvas mCavans;

    public GuaView(Context context) {
        this(context,null);
    }

    public GuaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mpiant=new Paint();
        mpiant.setAlpha(0);
        mpiant.setAntiAlias(false);
        mpiant.setStyle(Paint.Style.STROKE);
        mpiant.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mpiant.setStrokeJoin(Paint.Join.ROUND);
        mpiant.setStrokeWidth(50);
        mpiant.setStrokeCap(Paint.Cap.ROUND);

        mpath=new Path();
        //获取图片资源
        mDownImage=BitmapFactory.decodeResource(getResources(),R.mipmap.test);
        //根据原图获取一个原图大小的遮罩层
        mUpImage=Bitmap.createBitmap(mDownImage.getWidth(),mDownImage.getHeight(),Bitmap.Config.ARGB_8888);

        mCavans=new Canvas(mUpImage);
        mCavans.drawColor(Color.GRAY);
        Log.i("TAG","down--------tesst--comit--------");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                mpath.reset();
                mpath.moveTo(event.getX(),event.getY());
                Log.i("TAG","down------------------");
                break;
            case MotionEvent.ACTION_MOVE:
                mpath.lineTo(event.getX(),event.getY());
                Log.i("TAG","move----------------");
                break;
        }
        mCavans.drawPath(mpath,mpiant);
        Log.i("TAG","move---------testcommit-------");
        invalidate();
        return true;



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mDownImage,0,0,null);
        canvas.drawBitmap(mUpImage,0,0,null);
        Log.i("TAG","我最初是在local分支上");
    }
}
