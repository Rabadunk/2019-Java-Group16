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
            gainControl.setValue(-10);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setFileMusic(String soundFileName){
        setFile(soundFileName);
        // Might need to remove for sound effects
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }
}
