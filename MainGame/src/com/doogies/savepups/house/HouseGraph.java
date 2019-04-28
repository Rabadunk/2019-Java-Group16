package com.doogies.savepups.house;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.assets.TileAssets;

import java.util.ArrayList;

public class HouseGraph {

    public static Room bedRoom;
    public static Room hallwayRoom;
    public static Room testRoom;
    public static Room lounge;
    public static Room bossRoom;
    public static Room mazeRoom;
    public static Handler handler;

    public static ArrayList<ArrayList> house;
    private ArrayList<Room> rooms;

    public HouseGraph(Handler handler) {
        this.handler = handler;
        rooms = new ArrayList<>();
        house = new ArrayList<>();
        constructGraph(6);
        generateRooms(handler);
    }

    private void constructGraph(int numOfRooms) {
        // Graph is represented by an adjacency list using linked lists

        for(int i = 0; i < numOfRooms + 1; i++) {
            house.add(new ArrayList<AStarNode>());
            for(int j = 0; j < numOfRooms + 1; j++) {
                house.get(i).add(new AStarNode(1, 1, TileAssets.damagedFloor, false, handler));
            }
        }

    }

    private void generateRooms(Handler handler) {
        // Load rooms
        testRoom = new Room(handler, "shortestPathTestRoom.txt", 0);
        bedRoom = new Room(handler, "bedroom.txt", 1);
        hallwayRoom = new Room(handler, "hallway.txt", 2);
        lounge = new Room(handler, "lounge.txt", 3);
        bossRoom = new Room(handler, "bossRoom.txt", 4);
        mazeRoom = new Room(handler, "mazeRoom.txt", 5);

         // Store rooms
        rooms.add(testRoom);
        rooms.add(bedRoom);
        rooms.add(hallwayRoom);
        rooms.add(lounge);
        rooms.add(bossRoom);
        rooms.add(mazeRoom);
    }

    public static void addRoomEntrance(int thisRoomID, int otherRoomId, AStarNode node) {
        house.get(thisRoomID).add(otherRoomId, node);
    }

    public Room getRoom(int roomID) {
        return rooms.get(roomID);
    }


}
