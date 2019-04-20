package com.doogies.savepups.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioFile implements LineListener{

    private File soundFile;
    private AudioInputStream ais;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;
    private FloatControl gainControl;
    private boolean playing;

    public AudioFile(String fileName){
        soundFile = new File(fileName);
        try {
            ais = AudioSystem.getAudioInputStream(soundFile);
            format = ais.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void play(){
        play(-20);
    }

    public void play(float volume){
        gainControl.setValue(volume);
        clip.start();
        playing = true;
    }

    @Override
    public void update(LineEvent event) {
        if(event.getType() == LineEvent.Type.START){
            playing = true;
        }
        else if(event.getType() == LineEvent.Type.STOP){
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
            playing = false;
        }
    }

    // Getters and setters

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
