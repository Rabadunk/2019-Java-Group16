package com.doogies.savepups.house;

import com.doogies.savepups.Handler;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class AStarPathFinder {

    private AStarNode[][] grid;
    private int width, height;
    private  Handler handler;

    public AStarPathFinder(int width, int height, Handler handler) {
        this.width = width;
        this.height = height;
        this.handler = handler;
        grid = new AStarNode[width][height];
    }

    public AStarNode pathFind(AStarNode startNode, AStarNode endNode) {
        ArrayList<AStarNode> openSet = new ArrayList();
        ArrayList<AStarNode> closedSet = new ArrayList();
        ArrayList<AStarNode> path;
        AStarNode temp;
        openSet.add(startNode);

        clearData();

        System.out.println("StartNodeX: " + startNode.x + " StartNodeY: " + startNode.y);
        System.out.println("endNodeX: " + endNode.x + " endNodeY: " + endNode.y);

        if(startNode == endNode) {
            return startNode;
        }

        while(!openSet.isEmpty()) {

            int lowestFScoreIndex = 0;

            for(int i = 0; i < openSet.size(); i++) {
                if(openSet.get(i).f < openSet.get(i).f) {
                    lowestFScoreIndex = i;
                }
            }

            AStarNode current = openSet.get(lowestFScoreIndex);
            //System.out.println("NodeX: " + current.x + " NodeY: " + current.y);

            if(current == endNode) {
                temp = current;

                while(temp.previous != startNode) {

                    System.out.println("NodeX: " + temp.x + " NodeY: " + temp.y);

                    temp = temp.previous;
                }

                System.out.println("ReturnedNodeX: " + temp.x + " ReturnedNodeY: " + temp.y);

                return temp;

            }

            openSet.remove(current);
            closedSet.add(current);

            //System.out.println(current.getNeighbours().size());
            for(AStarNode neighbourNode : current.getNeighbours()) {
                if(!closedSet.contains(neighbourNode) && !neighbourNode.isSolid) {

                    boolean newPath = false;
                    float tempG = current.g + 1;
                    if(openSet.contains(neighbourNode)) {

                        if(tempG < neighbourNode.g) {
                            neighbourNode.g = tempG;
                            newPath = true;
                        }

                    } else {
                        neighbourNode.g = tempG;
                        openSet.add(neighbourNode);
                        newPath = true;
                    }

                    if(newPath) {
                        neighbourNode.h = heuristic(startNode, endNode);
                        neighbourNode.f = neighbourNode.h + neighbourNode.g;
                        neighbourNode.previous = current;
                    }
                }
            }


//            for(AStarNode node : openSet) {
//                node.renderAStarNode(g, (int) (node.x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
//                        (int) (node.y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()),
//                        Color.green);
//            }
//
//            for(AStarNode node : closedSet) {
//                node.renderAStarNode(g, (int) (node.x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
//                        (int) (node.y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()),
//                        Color.red);
//            }

        }

        System.out.println("Path find complete");


        return startNode;
    }

    public float heuristic(AStarNode startNode, AStarNode endNode) {

        float dx = Math.abs(endNode.x - startNode.x);
        float dy = Math.abs(endNode.y - startNode.y);

        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        return dist;

    }

    public void makeNeighboursForNodes() {

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                grid[i][j].generateNeighbours(this, i, j);
            }
        }

    }

    public void clearData() {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                grid[i][j].previous = null;
                grid[i][j].g = 0;
                grid[i][j].h = 0;
                grid[i][j].f = 0;
            }
        }
    }


    public void addNode(int x, int y, AStarNode node){
        grid[x][y] = node;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void renderGrid(Graphics g) {

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {

                getNode(x, y).renderAStarNode(g,
                        (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), Color.BLACK);
            }

        }
    }

    public AStarNode[][] getGrid() {
        return grid;
    }

    public AStarNode getNode(int x, int y) {

        if(x < 0 || y < 0 || x >= width || y >= height)
            return grid[0][0];

        AStarNode node = grid[x][y];
        if(node == null) {
            return grid[1][1];
        }
        return grid[x][y];

    }


}
