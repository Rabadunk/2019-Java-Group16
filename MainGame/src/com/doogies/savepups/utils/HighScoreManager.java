package com.doogies.savepups.utils;

import com.doogies.savepups.Handler;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HighScoreManager {

    private Handler handler;
    private ArrayList<Score> scores;
    private Comparator<Score> scoreComparator = (Score score1, Score score2) -> {
            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 > sc2){
                return -1;                   // -1 means first score is bigger then second score
            }
            else if (sc1 < sc2){
                return +1;                   // +1 means that score is lower
            }
            else{
                return 0;                     // 0 means score is equal
            }
    };

    private static final String savedFile = "res/saves/scores.dat";
    ObjectOutputStream output = null;
    ObjectInputStream input = null;

    public HighScoreManager(Handler handler){
        this.handler = handler;

        loadScoreFile();
    }

    public void loadScoreFile() {
        try {
            input = new ObjectInputStream(new FileInputStream(savedFile));
            scores = (ArrayList<Score>) input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(" FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(" CNF Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }

    public void sortScores(){
        Collections.sort(scores, scoreComparator);
    }

    public int compare(){
        return 0;
    }

    public void tick(){
//        for(Score s : scores)
//            System.out.println(s.getScore() + ":" + s.getName());
    }

}
// This code is heavily based on
// From https://stackoverflow.com/questions/22339123/adding-highscores-to-java-game-from-console-to-jpanel-saving-highscore-in-en