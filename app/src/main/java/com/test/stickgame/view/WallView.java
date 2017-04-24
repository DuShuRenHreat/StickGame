package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Random;

public class WallView extends View {
    private Paint paint = null;
    private int fristnum;
    private int secondnum;
    Random random = new Random();

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
        reOrder();

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
        canvas.drawRect(0,0,100,564,paint);
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
    public WallView init(){
        invalidate();
        return this;
    }
    /**
     * 获取第二个个柱子中间的坐标 X , X1,X2
     * */
    public HashMap<String,Integer> getMidpoint(){
        HashMap<String,Integer> result = new HashMap<>();
        result.put("f",fristnum);
        result.put("m" ,fristnum + ((secondnum - fristnum) / 2) );
        result.put("s",secondnum);
        return result;
    }
    /*
    *重新安排顺序
    **/
    public WallView reOrder(){
        fristnum = 400 + random.nextInt(200);
        secondnum = fristnum + random.nextInt(200) + 100;
        return this;
    }

}
