package com.test.stickgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.test.stickgame.utils.InitID;
import com.test.stickgame.utils.InitView;
import com.test.stickgame.view.StickVV;
import com.test.stickgame.view.WallView;

public class MainActivity extends Activity {
    @InitID(R.id.main_linear_state)
    private LinearLayout state;
    @InitID(R.id.main_linear_bottom)
    private LinearLayout bottom;
    @InitID(R.id.main_linear_body)
    private LinearLayout body;
    @InitID(R.id.main)
    private LinearLayout main;

    private StickVV stick;
    private WallView wall;

    @InitID(R.id.state_reset)
    private Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        InitView.load(this);

        initListener();
        iniBody();
        initBottom();



    }
    public void initState(){

    }

    public void initListener(){
        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        stick.setType(StickVV.ZENGZHNAG);
                        break;
                    case MotionEvent.ACTION_UP:
                        stick.setType(StickVV.DONGHUA);
                        break;
                }
                return true;
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stick.init();
                wall.init();
            }
        });

    }

    public void iniBody(){
        stick = new StickVV(this);
        body.addView(stick);
    }

    public void initBottom(){
        wall = new WallView(this);
        bottom.addView(wall);
    }
}
