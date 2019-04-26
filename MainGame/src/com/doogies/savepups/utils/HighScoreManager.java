package com.doogies.savepups.utils;

import com.doogies.savepups.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.doogies.savepups.utils.Utils.loadFileAsString;
import static com.doogies.savepups.utils.Utils.parseInt;


public class HighScoreManager {

    private Handler handler;
    private String[] stringScores;
    private ArrayList<Score> arrayListScores;
    private ArrayList<Score> highScores;
    private int i;
    private  boolean scoreAdded = false;

    // Highest and lowest scores
    private int lowestScore, highestScore;


    private Comparator<Score> scoreComparator = (Score score1, Score score2) -> {
            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 > sc2){
                return -1;                   // -1 means first score is bigger then second score
            }
            else if (sc1 < sc2){
                return 1;                   // 1 means that score is lower
            }
            else{
                return 0;                     // 0 means score is equal
            }
    };

    private static final String savedFile = "res/saves/scores.txt";

    public HighScoreManager(Handler handler){
        this.handler = handler;

        arrayListScores = new ArrayList<>();
        highScores = new ArrayList<>();

        loadScoreFileToStringList();
        convertStringScoresToInts();

        sortScores();
        lowestScore = arrayListScores.get(10).getScore();
        highestScore = arrayListScores.get(0).getScore();
        discardExtraScores();

//        printScoresOnce();
    }

    public void loadScoreFileToStringList() {
        stringScores = loadFileAsString(savedFile).split(("\\s+"));
    }

    public void convertStringScoresToInts() {
        for(i = 0; i < stringScores.length; i = i +2) {
            int thisScore = parseInt(stringScores[i+1]);
            String name = stringScores[i];
            arrayListScores.add(new Score(name, thisScore));
        }
    }

    public void sortScores(){
        Collections.sort(arrayListScores, scoreComparator);
    }

    public void discardExtraScores(){
        if(arrayListScores.size() > 10) {
            for (i = 0; i < 10; i++) {
                //System.out.println(arrayListScores.get(i).getName() + " : " + arrayListScores.get(i).getScore());
                highScores.add(arrayListScores.get(i));
            }
        }
        Collections.sort(highScores, scoreComparator);
    }

    public void tick(){
        //Not being ticked right now
    }


    public void printStringScoresOnce(){
        for(Score s: arrayListScores){
            System.out.println(s.getName() + " " + s.getScore());
        }
    }

    public void printScoresOnce(){
        for(Score s: highScores){
            System.out.println(s.getName() + " " + s.getScore());
        }
        System.out.println("Highest: " + highestScore + " Lowest: " + lowestScore);
    }


    public void addScore(String name, int score){
        arrayListScores.add(new Score(name, score));
        scoreAdded = true;
        //System.out.println("Score added");
        sortScores();
        lowestScore = arrayListScores.get(10).getScore();
        highestScore = arrayListScores.get(0).getScore();
        discardExtraScores();
    }
    // Getters and setters


    public ArrayList<Score> getArrayListScores() {
        return arrayListScores;
    }

    public void setArrayListScores(ArrayList<Score> arrayListScores) {
        this.arrayListScores = arrayListScores;
    }

    public ArrayList<Score> getHighScores() {
        return highScores;
    }

    public void setHighScores(ArrayList<Score> highScores) {
        this.highScores = highScores;
    }

    // Getters and setters


    public int getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(int lowestScore) {
        this.lowestScore = lowestScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highScore) {
        this.highestScore = highScore;
    }
}
// Some of the code is
// From https://stackoverflow.com/questions/22339123/adding-highscores-to-java-game-from-console-to-jpanel-saving-highscore-in-en
