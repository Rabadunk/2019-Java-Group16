package com.doogies.savepups.hud;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

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
        g.drawImage(Assets.bed, 15 ,50, null);
        Text.drawString(g, ": Heaps!  jk  its:  " + handler.getPlayer().getInventory().getItem("Bed"), 50,85, false, Color.WHITE, Assets.fontHud);

        // Score and time
        Text.drawString(g, "Score:  ", handler.getWidth() / 2, 20, true, Color.WHITE, Assets.fontHud);
        Text.drawString(g, "Time:  ", handler.getWidth() / 2, 730, true, Color.WHITE, Assets.fontHud);

        // Equiped items
        g.drawImage(Assets.sword, 20, 680, 64, 64, null);

        g.drawImage(Assets.sword, 800, 680, 64, 64, null);
        g.drawImage(Assets.sword, 870, 680, 64, 64, null);
        g.drawImage(Assets.sword, 940, 680, 64, 64, null);
    }

}
