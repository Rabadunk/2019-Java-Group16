package com.doogies.savepups.utils;

public class Score {

    private int score;
    private String name;

    public Score(String name, int score){
        this.score = score;
        this.name = name;
    }

    // Getters and setters

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
