package com.test.stickgame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.test.stickgame.subscriber.Mess;
import com.test.stickgame.subscriber.Messager;
import com.test.stickgame.utils.InitID;
import com.test.stickgame.utils.InitView;
import com.test.stickgame.view.PersonView;
import com.test.stickgame.view.StickVV;
import com.test.stickgame.view.WallView;

import static com.test.stickgame.view.StictView.fy;

public class MainActivity extends Activity {
    @InitID(R.id.main_linear_state)
    private LinearLayout state;
    @InitID(R.id.main_linear_bottom)
    private LinearLayout bottom;
    @InitID(R.id.main_linear_body)
    private LinearLayout body;
    @InitID(R.id.main)
    private LinearLayout main;
    @InitID(R.id.main_frame_body_person)
    private FrameLayout personlayout;

    private StickVV stick;
    private WallView wall;
    private PersonView person;

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
                init();
            }
        });

    }
    public void doAnimation1(){
        AnimatorSet set = new AnimatorSet() ;
        ObjectAnimator anim = ObjectAnimator .ofFloat(main, "rotationX", 0f, 360f);
        anim.setDuration(2000);
        ObjectAnimator anim2 = ObjectAnimator .ofFloat(main, "rotationX", 360f, 0f);
        anim2.setDuration(2000);
        set.play(anim).before(anim2);
        set.start();

    }
    public void doAnimation2(){
        AnimatorSet set = new AnimatorSet() ;
        ObjectAnimator anim3 = ObjectAnimator .ofFloat(main, "rotationY", 0f, 360);
        anim3.setDuration(2000);
        ObjectAnimator anim4 = ObjectAnimator .ofFloat(main, "rotationY", 360f, 0f);
        anim4.setDuration(2000);
        set.play(anim3).before(anim4) ;
        set.start();
    }
    /**
     * 初始化游戏
     * */
    public void init(){
        stick.init();
        wall.init().reOrder();
        stick.drawMidPoint(wall.getMidpoint());
        person.init();
        isExecute = false;
        stick.drawMidPoint(wall.getMidpoint());
        person.setVals(wall.getMidpoint());
    }
    /**
     * 动画执行完的时候
     * */
    @Mess
    public void test(String[] strs){
        Log.e("tset","size: " + strs[0]);
        if("true".equals(strs[0])){
            Log.e("li","true");
            doAnimation1();
            init();
            doAnimation2();
        }else if("false".equals(strs[0])){
            Log.e("li","false");
        }else{
            Log.e("li","other");
        }
    }
    /**
     * 结果
     * */
    @Mess
    public void test(String result){
        if("false".equals(result)){
            //失败
            person.failuere(stick.getLineLength());
        }else if("true".equals(result)){
            //成功
            person.successful();
        }else if("zhong".equals(result)){
            //中红点
            person.zhong();
        }
    }

    //实例化中部
    public void iniBody(){
        person = new PersonView(this,0,fy-84);
        stick = new StickVV(this);
        personlayout.addView(stick);
        personlayout.addView(person);
        person.raize();

    }
    //实例化底部
    public void initBottom(){
        wall = new WallView(this);
        bottom.addView(wall);
        //第二跳柱子在中部的中间点
        stick.drawMidPoint(wall.getMidpoint());
        person.setVals(wall.getMidpoint());
    }
}
