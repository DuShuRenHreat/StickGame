package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.test.stickgame.Chace.BitMapCache;
import com.test.stickgame.R;

/**
 * Created by asdf on 2017/4/24.
 */

public class PersonView extends View {
    private Paint paint = null;
    private int startx;
    private int starty;
    private int endx;
    private int endy;
    private int playId = 0;
    private int[] imgs = {R.drawable.pic_right_1,R.drawable.pic_right_2,R.drawable.pic_right_3,R.drawable.pic_right_4};
    public PersonView(Context context, int x, int y) {
        super(context);
        startx = x;
        starty = y;
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    try {
                        postInvalidate();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPerson(canvas);
    }
    public int switchOne(){
       return ++playId == 4 ? playId = 0 : playId;
    }
    public void drawPerson(Canvas canvas){
        canvas.drawBitmap(BitMapCache.create(imgs[switchOne()]),startx,starty,paint);
    }
    public void move(int x){

    }
}
