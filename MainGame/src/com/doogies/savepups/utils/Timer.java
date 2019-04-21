package com.doogies.savepups.utils;

import com.doogies.savepups.Handler;


public class Timer {

    // STart timer at set game gamestate
    // stop timer at voctory screen or death screen game over

    private Handler handler;

    long currentTime = 0;
    long timeNow = 0;

    public Timer(Handler handler){
        this.handler = handler;
        currentTime = System.nanoTime();


    }

    public void startTimer(){
        //timer.start();
    }
    public void  stop(){
        // timer.stop()
    }
}
