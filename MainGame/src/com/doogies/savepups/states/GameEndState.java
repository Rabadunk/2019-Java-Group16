package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;
import com.doogies.savepups.inventory.Inventory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameEndState extends State {

    public AudioPlayer victoryMusic, defeatMusic;

    private boolean scoreSet = false;

    public GameEndState(Handler handler){
        super(handler);

        victoryMusic = new AudioPlayer();
        victoryMusic.setFileMusic("Victory");

        defeatMusic = new AudioPlayer();
        defeatMusic.setFileMusic("NoHope");
    }

    @Override
    public void tick() {
        if(handler.getPlayer().getScore() > handler.getHighScoreManager().getLowestScore() && !scoreSet){
            handler.getHighScoreManager().addScore("New_player", handler.getPlayer().getScore());
            scoreSet = true;
            handler.getHighScoreManager().printScoresOnce();
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            // Reset game.
            handler.newPlayer();
            handler.getGame().gameState = new GameState(handler);
            State.setState(handler.getGame().menuState);
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, handler.getWidth(), handler.getHeight());

        if(handler.getPlayer().isGameWon){
            if(handler.getPlayer().getScore() > handler.getHighScoreManager().getLowestScore()) {
                Text.drawString(g, "New high score!", 500, 200, true, Color.WHITE, Assets.fontTitle);
            }
            else{
                Text.drawString(g, "Congratulations!", 500, 200, true, Color.WHITE, Assets.fontTitle);
            }
        }
        else {
            Text.drawString(g, "GAME OVER", 500, 200, true, Color.WHITE, Assets.fontTitle);
        }



        Text.drawString(g, "Score: " + handler.getPlayer().getScore(),500, 600, true, Color.cyan,Assets.font28);

        // Correct format
        if(handler.getPlayer().getTimeTakenSeconds() < 10){
            Text.drawString(g, "Time: " + handler.getPlayer().getTimeTakenMinutes() + ":0" + handler.getPlayer().getTimeTakenSeconds(),
                    500, 570, true, Color.cyan, Assets.font28);
        }
        else {
            Text.drawString(g, "Time: " + handler.getPlayer().getTimeTakenMinutes() + ":" + handler.getPlayer().getTimeTakenSeconds(),
                    500, 570, true, Color.cyan, Assets.font28);
        }

        Text.drawString(g,"Press ENTER to exit", 500, 650, true, Color.WHITE, Assets.font28);

    }

    @Override
    public void startMusic() {
        if(handler.getPlayer().isIsGameWon()){
            victoryMusic.play();
        }
        else{
            defeatMusic.play();
        }
    }

    @Override
    public void stopMusic() {
        victoryMusic.stop();
        defeatMusic.stop();
    }
}
