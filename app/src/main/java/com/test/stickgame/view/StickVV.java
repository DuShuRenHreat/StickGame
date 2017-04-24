package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import static com.test.stickgame.view.StictView.fx;
import static com.test.stickgame.view.StictView.fy;


public class StickVV extends View {
    private Paint paint;
    private int speed ;
    public static final int ZENGZHNAG = 110;
    public static final int DEFAULT = 220;
    public static final int DONGHUA = 330;
    private int type = DEFAULT;
    private int count = 0;
    public StickVV(Context context) {
        super(context);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    postInvalidate();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void initView(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       switch (type) {
            case ZENGZHNAG:
                if (speed <= (fy - 100))
                    speed += 20;
                canvas.drawLine(fx,fy,fx,fy - speed,paint);
                break;
            case DEFAULT:
                canvas.rotate(count,fx,fy);
                canvas.drawLine(fx,fy,fx,fy - speed,paint);
                break;
            case DONGHUA:
                count+=10;
                if(count <= 80){
                    canvas.rotate(count,fx,fy);
                    canvas.drawLine(fx,fy,fx,fy - speed,paint);
                }else{
                    type = DEFAULT;
                }
                break;
        }

    }
    public void setType(int type){
        this.type = type;
    }
    /**
     * 初始化
     * */
    public void init(){
        type = DEFAULT;
        speed = 0;
        count = 0;
    }

}
