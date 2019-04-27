package com.doogies.savepups.hud;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;
import com.doogies.savepups.states.State;
import com.doogies.savepups.utils.GameTimer;

import java.awt.*;

public class GameHud {

    private Handler handler;

    public GameHud(Handler handler){
        this.handler = handler;
    }

    public void tick(){
        //System.out.println("GameHud");

    }

    public void render(Graphics g){
        // Health and beds which later will be dooogies
        Text.drawString(g, "Health:  " + handler.getPlayer().getHealth(), 15,30, false, Color.WHITE, Assets.fontHud);
        g.drawImage(Assets.coinGold[4], handler.getWidth() - 120 ,13, null);
        Text.drawString(g, ": "+handler.getPlayer().getInventory().getItem("CoinGold"), handler.getWidth() - 90,30, false, Color.WHITE, Assets.fontHud);

        // Score and time
        if(handler.getPlayer().getTimeTakenSeconds() < 10){
            Text.drawString(g, "Time:  " + handler.getPlayer().getTimeTakenMinutes() + " : 0" + handler.getPlayer().getTimeTakenSeconds(),
                    handler.getWidth() / 2, 20, true, Color.WHITE, Assets.fontHud);
        }
        else {
            Text.drawString(g, "Time:  " + handler.getPlayer().getTimeTakenMinutes() + " : " + handler.getPlayer().getTimeTakenSeconds(),
                    handler.getWidth() / 2, 20, true, Color.WHITE, Assets.fontHud);
        }
        Text.drawString(g, "Score:  " + handler.getPlayer().getScore(), handler.getWidth() / 2, 730, true, Color.WHITE, Assets.fontHud);

        // Equiped items
        g.drawImage(Assets.sword, 20, 680, 64, 64, null);

        g.drawImage(Assets.sword, 800, 680, 64, 64, null);
        g.drawImage(Assets.sword, 870, 680, 64, 64, null);
        g.drawImage(Assets.sword, 940, 680, 64, 64, null);
    }

}
