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
        Text.drawString(g, "Health: " + handler.getPlayer().getHealth(), 15,40, false, Color.WHITE, Assets.font28);
        g.drawImage(Assets.bed, 15 ,50, null);
        Text.drawString(g, ": Heaps", 50,85, false, Color.WHITE, Assets.font28);

    }

}
