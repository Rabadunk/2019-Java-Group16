package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;
import com.doogies.savepups.inventory.Inventory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends State {

    public AudioPlayer gameMusic;


    public GameOverState(Handler handler){
        super(handler);
        gameMusic = new AudioPlayer();
        gameMusic.setFileMusic("NoHope");
    }


    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
            // Reset game.
            handler.getGame().gameState = new GameState(handler);
            handler.getPlayer().setInventory(new Inventory(handler));
            State.setState(handler.getGame().menuState);
            handler.getPlayer().setPlayerActive(false);
            handler.getPlayer().setTimerSet(false);
            handler.getPlayer().setScore(0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, handler.getWidth(), handler.getHeight());
        Text.drawString(g,"GAME OVER", 500, 200, true, Color.WHITE, Assets.fontTitle);

        Text.drawString(g, "Score: " + handler.getPlayer().getScore(),500, 600, true, Color.cyan,Assets.font28);

        if(handler.getPlayer().getTimeTakenSeconds() < 10){
            Text.drawString(g, "Time: " + handler.getPlayer().getTimeTakenMinutes() + ":0" + handler.getPlayer().getTimeTakenSeconds(),
                    500, 570, true, Color.cyan, Assets.font28);
        }
        else {
            Text.drawString(g, "Time: " + handler.getPlayer().getTimeTakenMinutes() + ":" + handler.getPlayer().getTimeTakenSeconds(),
                    500, 570, true, Color.cyan, Assets.font28);
        }

        Text.drawString(g,"Press p to exit", 500, 650, true, Color.WHITE, Assets.font28);


    }

    @Override
    public void startMusic() { gameMusic.play(); }

    @Override
    public void stopMusic() {
        gameMusic.stop();
    }
}
