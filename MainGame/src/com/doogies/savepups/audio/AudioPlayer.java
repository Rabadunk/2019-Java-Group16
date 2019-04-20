package com.doogies.savepups.audio;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {

    Clip clip;

    public void setFile(String soundFileName){

        try {
            File soundFile = new File("res/audio/" + soundFileName + ".wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }
}
