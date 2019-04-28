package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HelpState extends State {

    private static boolean bedroomHints = false;
    private static boolean hallwayHints = false;
    private static boolean loungeHints = false;
    private static boolean bossHints = false;
    private static boolean pathHints = false;
    private static boolean mazeHints = false;

    public HelpState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)){
            State.setState(handler.getGame().gameState);
        }

        if(handler.getRoom().ID == 0) {
            bedroomHints = false;
            hallwayHints = false;
            loungeHints = false;
            bossHints = false;
            pathHints = true;
            mazeHints = false;
        }
        else if(handler.getRoom().ID  == 1){
            bedroomHints = true;
            hallwayHints = false;
            loungeHints = false;
            bossHints = false;
            pathHints = false;
        }
        else if (handler.getRoom().ID == 2){
            bedroomHints = false;
            hallwayHints = true;
            loungeHints = false;
            bossHints = false;
            pathHints = false;
            mazeHints = false;
        }
        else if (handler.getRoom().ID == 3){
            bedroomHints = false;
            hallwayHints = false;
            loungeHints = true;
            bossHints = false;
            pathHints = false;
            mazeHints = false;
        }
        else if (handler.getRoom().ID == 4){
            bedroomHints = false;
            hallwayHints = false;
            loungeHints = false;
            bossHints = true;
            pathHints = false;
            mazeHints = false;
        }
        else if (handler.getRoom().ID == 5) {
            bedroomHints = false;
            hallwayHints = false;
            loungeHints = false;
            bossHints = false;
            pathHints = false;
            mazeHints = true ;
        }
    }

    @Override
    public void render(Graphics g) {
        handler.getRoom().render(g);
        g.drawImage(Assets.tint, 0,0, null);

        Text.drawString(g, "Hints", handler.getWidth()/2, 100, true, Color.WHITE, Assets.fontTitle);

        if(bedroomHints){
            bedroomRender(g);
        }
        else if(hallwayHints){
            hallwayRender(g);
        }
        else if(loungeHints){
            loungeRender(g);
        }
        else if(bossHints){
            bossRender(g);
        }
        else if(pathHints) {
            pathRender(g);
        }
        else if(mazeHints) {
            mazeRender(g);
        }

        Text.drawString(g,"Press H to return to game", 500, 680, true, Color.WHITE, Assets.font28);
    }

    public void bedroomRender(Graphics g){
        Text.drawString(g, "Kill the bed!", handler.getWidth()/2, 300,
                true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "The bed allows you to hide from enemies!",
                handler.getWidth()/2, 350, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "Press G to turn into a bed and again to turn back",
                handler.getWidth()/2, 400, true, Color.WHITE, Assets.fontChenSmaller);
    }

    public void hallwayRender(Graphics g){
        Text.drawString(g, "Kill all the mobs to save the puppy",
                handler.getWidth()/2, 300, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "You cannot leave the room until you save the puppy",
                handler.getWidth()/2, 350, true, Color.WHITE, Assets.fontChenSmaller);
    }

    public void loungeRender(Graphics g){
        Text.drawString(g, "Kill all the mobs to save the puppy",
                handler.getWidth()/2, 300, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "You cannot leave the room until you save the puppy",
                handler.getWidth()/2, 350, true, Color.WHITE, Assets.fontChenSmaller);
    }

    public void bossRender(Graphics g){
        Text.drawString(g, "Kill the vampire",
                handler.getWidth()/2, 300, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "When the vampire turns into a bat,",
                handler.getWidth()/2, 350, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "run around the room and kill the enemies.",
                handler.getWidth()/2, 400, true, Color.WHITE, Assets.fontChenSmaller);
    }

    public void pathRender(Graphics g){
        Text.drawString(g, "This room shows our path finding algorithm.",
                handler.getWidth()/2, 300, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "Red are checked, green are to be checked and blue is the path.",
                handler.getWidth()/2, 350, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "Walk around or go to the next room.",
                handler.getWidth()/2, 400, true, Color.WHITE, Assets.fontChenSmaller);
    }

    public void mazeRender(Graphics g){
        Text.drawString(g, "Press spacebar to summon the witch or to stop it.",
                handler.getWidth()/2, 300, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "Press C to center on the witch or on the player",
                handler.getWidth()/2, 350, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "The witch will find you. Play hide and seek with it.",
                handler.getWidth()/2, 400, true, Color.WHITE, Assets.fontChenSmaller);
    }


    @Override
    public void startMusic() {
    }

    @Override
    public void stopMusic() {
    }
}
