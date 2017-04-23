package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import static com.test.stickgame.view.StictView.fx;
import static com.test.stickgame.view.StictView.fy;

/**
 * Created by asdf on 2017/4/23.
 */

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
    /*    new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    postInvalidate();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
    }
    public void initView(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    public void raize(){
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = fy;
        lp.width = 200;
        setLayoutParams(lp);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     /*   switch (type) {
            case ZENGZHNAG:
                if (speed <= (fy - 100))
                    speed += 20;
                canvas.drawLine(fx,fy,fx,fy - speed,paint);
                break;
            case DEFAULT:
                canvas.drawLine(fx,fy,fx,fy - speed,paint);
                break;
            case DONGHUA:
                count+=10;
                if(count <= 90)
                    canvas.rotate(count);
                else{
                    type = DEFAULT;
                }
                break;
        }*/
        canvas.drawLine(fx,fy,fx,500,paint);
        canvas.rotate(46,fx,fy);
        int sx = fx;
        int sy = 500;

        canvas.drawLine(fx,fy,fx,500,paint);

    }
    public void setType(int type){
        this.type = type;
    }

}
