package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.test.stickgame.MainActivity;
import com.test.stickgame.subscriber.Messager;

import java.util.HashMap;

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
    //中间点的X坐标
    private int mx = 0;
    private int ffx;
    private int ssx;
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
                doDefault(canvas);;
                break;
            case DONGHUA:
                count+=10;
                if(count <= 80){
                    doDefault(canvas);
                }else{
                    MainActivity.isExecute = true;
                    setType(DEFAULT);
                    jude();
                }
                break;
        }
        drawMyPoint(canvas);
    }
    /**
     * 处理结果返回到MainActivity
     * */
    public void jude(){
        int x =   fx + speed;
        if(ffx <=  x && x <= ssx){
            if(mx - 5 <= x && x <= mx + 5 ){
                Messager.getInstance().post("book","zhong");
            }else{
                Messager.getInstance().post("book","true");
            }
        }else{
            Messager.getInstance().post("book","false");
        }
    }
    /**
     *处理了移动后红点会消失
     * */
    public void doDefault(Canvas canvas){
        canvas.save();
        canvas.rotate(count,fx,fy);
        canvas.drawLine(fx,fy,fx,fy - speed,paint);
        canvas.restore();
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
    public void drawMyPoint(Canvas canvas){
        Paint paint =  new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.drawCircle(mx,fy,10,paint);
    }
    public int getLineLength(){
        return fx + speed;
    }
    /**
     * 画中心的点
     * */
    public void drawMidPoint(HashMap<String,Integer> result){
        mx = result.get("m");
        ffx = result.get("f");
        ssx = result.get("s");
        invalidate();
    }
}
