package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class WallView extends View {
    private Paint paint = null;
    private int fristnum;
    private int secondnum;


    private int height;
    public WallView(Context context) {
        super(context);
        initView();
    }

    /**
     * 输出高多少
     * */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measure(0,0);
        ViewGroup view = (ViewGroup) this.getParent();
        height = view.getMeasuredHeight();
     //   Log.e("tset","height: " +  height );
    }

    public void initView(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

    }
    /**
     * 获取棒子起点
     * */
    public int getIndex(){
        return 100;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Random random = new Random();
        canvas.drawRect(0,0,100,564,paint);
        fristnum = 100 + random.nextInt(500);
        secondnum = fristnum + random.nextInt(300) + 50;
        canvas.drawRect(fristnum,0,secondnum,564,paint);
    }

    /**
     * 获取范围 X,Y
     * */
    public int[] getRange(){
        int[] arr = new int[2];
        arr[0] = fristnum;
        arr[1] = secondnum;
        return arr;
    }
    /**
     * 初始化
     * */
    public void init(){
        invalidate();
    }
}
