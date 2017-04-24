package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.test.stickgame.R;

/**
 * Created by asdf on 2017/4/24.
 */

public class Person extends View {
    private int startx;
    private int starty;
    private int endx;
    private int endy;
    private int[] imgs = {R.drawable.pic_right_1,R.drawable.pic_right_2,R.drawable.pic_right_3,R.drawable.pic_right_4};
    public Person(Context context,int x, int y) {
        super(context);
        startx = x;
        starty = y;
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
    }

    public void move(int x){

    }
}
