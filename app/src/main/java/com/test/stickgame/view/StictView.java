package com.test.stickgame.view;

import android.content.Context;

import com.test.stickgame.state.StateView;
import com.test.stickgame.state.shiti.StateDefault;


public class StictView extends StateView {
    public static final String STATE_DEFAULT = "default";
    public static final int fx = 97;
    public static final int fy = 1048;
    public StictView(Context context) {
        super(context);
        this.addState(STATE_DEFAULT,new StateDefault(this));
        this.setState(STATE_DEFAULT);
    }
    public void play(){
        this.setState(STATE_DEFAULT);
    }
}
