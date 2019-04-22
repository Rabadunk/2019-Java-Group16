package com.doogies.savepups.utils;

import com.doogies.savepups.Handler;


public class GameTimer {

    // STart timer at set game gamestate
    // stop timer at voctory screen or death screen game over

    private Handler handler;

    long startTime = 0;
    long stopTime = 0;

    long currentTime;
    long timeTaken = 0;

    public GameTimer(Handler handler){
        this.handler = handler;
    }

    public void startTimer(){
        startTime = System.currentTimeMillis();
    }
//    public void stop(){
//        stopTime = System.currentTimeMillis();
//    }
//
//    public void currentTime(){
//        currentTime = System.currentTimeMillis() - startTime;
//    }

    // Getters and setters

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }
}
