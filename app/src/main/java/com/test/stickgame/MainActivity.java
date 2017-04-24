package com.test.stickgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.test.stickgame.subscriber.Mess;
import com.test.stickgame.subscriber.Messager;
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

    @InitID(R.id.main_btn_reset)
    private Button btn_reset;

    public static  boolean isExecute = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        InitView.load(this);
        Messager.getInstance().register(this);
        initListener();
        iniBody();
        initBottom();


    }
    public void initState(){

    }

    public void initListener(){
        //触摸屏幕时做的动作
        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isExecute) return false;
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        stick.setType(StickVV.ZENGZHNAG);
                        break;
                    case MotionEvent.ACTION_UP:
                        stick.setType(StickVV.DONGHUA);
                        //手指离开后点击没有效果
                        isExecute = true;
                        break;
                }
                return true;
            }
        });
        //重置按钮
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stick.init();
                wall.init().reOrder();
                stick.drawMidPoint(wall.getMidpoint());
                isExecute = false;
            }
        });

    }
    /**
     * 结果
     * */
    @Mess
    public void test(String result){
        Log.e("test","result: " + result);
    }

    //实例化中部
    public void iniBody(){
        stick = new StickVV(this);
        body.addView(stick);
    }
    //实例化底部
    public void initBottom(){
        wall = new WallView(this);
        bottom.addView(wall);
        //第二跳柱子在中部的中间点
        stick.drawMidPoint(wall.getMidpoint());
    }
}
