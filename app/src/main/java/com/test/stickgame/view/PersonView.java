package com.test.stickgame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import com.test.stickgame.Chace.BitMapCache;
import com.test.stickgame.R;
import com.test.stickgame.subscriber.Mess;
import com.test.stickgame.subscriber.Messager;

import java.util.HashMap;

import static com.test.stickgame.view.StictView.fy;

/**
 * Created by asdf on 2017/4/24.
 */

public class PersonView extends View {
    private HashMap<String,Integer> vals = null;
    private Paint paint = null;
    //终点的坐标
    private int endx;
    //移动的坐标
    private int movex;
    private int movey;
    public static final int STATE_DEFAULE = 110;
    public static final int STATE_MOVE = 220;
    public static  final int STATE_FAIL = 300;
    public static final int STATE_FAIL_DOWN = 400;
    private int type = STATE_DEFAULE;
    private int playId = 0;
    private int[] imgs = {R.drawable.pic_right_1,R.drawable.pic_right_2,R.drawable.pic_right_3,R.drawable.pic_right_4};
    public PersonView(Context context, int x, int y) {
        super(context);
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    switch (type){
                        case STATE_MOVE:
                            if(movex <= endx){
                                movex +=10;
                                setX(movex);
                            }else{
                                setType(STATE_DEFAULE);
                                Messager.getInstance().post("book",new String[]{"true"});
                            }
                            break;
                        case STATE_DEFAULE:
                            break;
                        case STATE_FAIL:
                            if(movex <= endx){
                                movex +=10;
                                setX(movex);
                            }else{
                                setType(STATE_FAIL_DOWN);
                            }
                            break;
                        case STATE_FAIL_DOWN:
                            if(movey <= 100){
                                movey +=10;
                                setY((fy - 84) + movey);
                            }else{
                                setType(STATE_DEFAULE);
                                Messager.getInstance().post("book",new String[]{"false"});
                            }
                            break;
                    }
                    try {
                        postInvalidate();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //初始位置
        setX(x);
        setY(y);
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
        int val  = switchOne();
        canvas.drawBitmap(BitMapCache.create(imgs[val]),0,0,paint);
    }

    public void setVals(HashMap<String,Integer> val){
        this.vals = val;
    }
    public void setType(int type){
        this.type = type;
    }
    /**
     * 成功
     * */
    public void successful(){
        setType(STATE_MOVE);
        endx = vals.get("m") - 38;
    }
    /**
     * 失败
     * */
    public void failuere(int x){
        setType(STATE_FAIL);
        endx = x - 76;

    }
    /**
     * 正中
     * */
    public void zhong(){
        successful();
    }
    @Mess
    public void test(){

    }
    public void raize(){
        ViewGroup.LayoutParams lp = getLayoutParams();
        Bitmap bitmap = BitMapCache.create(R.drawable.pic_right_1);
        lp.width = bitmap.getWidth();
        lp.height = bitmap.getHeight();
        setLayoutParams(lp);
    }
    public void init(){
        setX(0);
        setY(fy - 84);
        movex = 0;
        type = STATE_DEFAULE;
        movey = 0;
    }
}
