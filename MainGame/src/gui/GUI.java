package gui;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;

import java.awt.*;

public class GUI {

    private Handler handler;

    public GUI(Handler handler){
        this.handler = handler;
    }

    public void tick(){
        System.out.println("GUI");

    }

    public void render(Graphics g){
        g.drawImage(Assets.bed, 100 ,100, null);

    }

}
