package com.doogies.savepups.audio;

import com.doogies.savepups.Handler;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

public class Music implements Runnable{

    private ArrayList<String> musicFiles;
    private int index;

    public Music(String... files){
        musicFiles = new ArrayList<>();
        for(String file : files){
            musicFiles.add("res/audio/" + file + ".wav");
        }
    }


    private void playSound(String fileName){
        try {
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        playSound(musicFiles.get(index));
    }
}
