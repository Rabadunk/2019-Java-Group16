package com.doogies.savepups.house;

import com.doogies.savepups.Handler;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AStarNode {

    public int x, y;
    public boolean isSolid;
    public int worldID;
    public float f, g, h;
    private ArrayList<AStarNode> neighbours;
    private ArrayList<AStarNode> neighboursHorizontalVertical;
    private ArrayList<AStarNode> neighboursDiagonal;
    private Handler handler;
    private BufferedImage texture;
    private boolean isEntry = false;
    public int roomSpawnX, roomSpawnY;

    public AStarNode previous;

    public AStarNode(int x, int y, BufferedImage texture, boolean isSolid, Handler handler) {
        this.x = x;
        this.y = y;
        this.isSolid = isSolid;
        this.handler = handler;
        this.neighbours = new ArrayList();
        this.neighboursHorizontalVertical = new ArrayList();
        this.neighboursDiagonal = new ArrayList();
        this.texture = texture;
        this.worldID = 0;

        f = 0;
        g = 0;
        h = 0;

    }

    public void generateNeighbours(AStarPathFinder pathFinder, int ourXIndex, int ourYIndex) {
        generateHorizontalAndVerticalNeighbours(pathFinder, ourXIndex, ourYIndex);
        generateDiagonalNeighbours(pathFinder, ourXIndex, ourYIndex);
    }

    private void generateHorizontalAndVerticalNeighbours(AStarPathFinder pathFinder, int ourXIndex, int ourYIndex) {
        if(ourXIndex < pathFinder.getWidth() - 1) {
            neighboursHorizontalVertical.add(pathFinder.getGrid()[ourXIndex + 1][ourYIndex]);
            neighbours.add(pathFinder.getGrid()[ourXIndex + 1][ourYIndex]);

        }

        if(ourXIndex > 0) {
            neighboursHorizontalVertical.add(pathFinder.getGrid()[ourXIndex - 1][ourYIndex]);
            neighbours.add(pathFinder.getGrid()[ourXIndex - 1][ourYIndex]);
        }

        if(ourYIndex < pathFinder.getHeight() - 1) {
            neighboursHorizontalVertical.add(pathFinder.getGrid()[ourXIndex][ourYIndex + 1]);
            neighbours.add(pathFinder.getGrid()[ourXIndex][ourYIndex + 1]);
        }

        if(ourYIndex > 0) {
            neighboursHorizontalVertical.add(pathFinder.getGrid()[ourXIndex][ourYIndex - 1]);
            neighbours.add(pathFinder.getGrid()[ourXIndex][ourYIndex - 1]);
        }
    }

    private void generateDiagonalNeighbours(AStarPathFinder pathFinder, int ourXIndex, int ourYIndex){
        if (ourXIndex > 0 && ourYIndex > 0) {
            neighboursDiagonal.add(pathFinder.getGrid()[ourXIndex - 1][ourYIndex - 1 ]);
            neighbours.add(pathFinder.getGrid()[ourXIndex - 1][ourYIndex - 1 ]);
        }

        if (ourXIndex < pathFinder.getWidth() - 1 && ourYIndex > 0) {
            neighboursDiagonal.add(pathFinder.getGrid()[ourXIndex + 1][ourYIndex - 1 ]);
            neighbours.add(pathFinder.getGrid()[ourXIndex + 1][ourYIndex - 1 ]);
        }

        if (ourXIndex > 0 && ourYIndex < pathFinder.getHeight() - 1) {
            neighboursDiagonal.add(pathFinder.getGrid()[ourXIndex - 1][ourYIndex + 1 ]);
            neighbours.add(pathFinder.getGrid()[ourXIndex - 1][ourYIndex + 1 ]);
        }

        if (ourXIndex < pathFinder.getWidth() - 1 && ourYIndex < pathFinder.getHeight() - 1) {
            neighboursDiagonal.add(pathFinder.getGrid()[ourXIndex + 1][ourYIndex + 1 ]);
            neighbours.add(pathFinder.getGrid()[ourXIndex + 1][ourYIndex + 1 ]);
        }
    }

    public void renderAStarNode(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
    }

    public void renderAStarNode(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }

    public ArrayList<AStarNode> getNeighbours() {
        return neighbours;
    }

    public boolean isEntry() {
        return isEntry;
    }

    public void setEntry(boolean bool) {
        isEntry = true;
    }


    public ArrayList<AStarNode> getHorizontalVerticalNeighbours() {
        return neighboursHorizontalVertical;
    }
}
