package com.doogies.savepups.audio;

import com.doogies.savepups.Handler;
import com.doogies.savepups.states.MenuState;
import com.doogies.savepups.states.State;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

public class Music implements Runnable{

    protected Handler handler;
    private ArrayList<AudioFile> musicFiles;
    private int index;
    private boolean running;

    public Music(Handler handler, String... files){
        this.handler = handler;
        musicFiles = new ArrayList<>();
        for(String file : files){
            musicFiles.add(new AudioFile("res/audio/" + file + ".wav"));
        }
    }


    @Override
    public void run() {

        running = true;

        AudioFile song = musicFiles.get(index);
        song.play();

        while(running) {
            if(!song.isPlaying()) {
                index++;
                if(index >= musicFiles.size()) {
                    index = 0;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
