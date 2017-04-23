package com.test.stickgame.state.shiti;

import android.graphics.Canvas;

import com.test.stickgame.state.Statable;
import com.test.stickgame.state.StateView;

import static com.test.stickgame.view.StictView.fx;
import static com.test.stickgame.view.StictView.fy;


public class StateDefault extends Statable {
    private StateView view;
    public StateDefault(final StateView view){
        this.view = view;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    view.postInvalidate();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(fx,fy,fx,fy,paint);
    }
}
