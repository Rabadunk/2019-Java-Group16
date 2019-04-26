package com.doogies.savepups.house;

import com.doogies.savepups.Handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HouseGraph {

    private int numOfRooms = 4;

    private Room bedRoom;
    private Room hallwayRoom;
    private Room testRoom;
    private Room lounge;
    private Room bossRoom;

    private List<Integer> house[];
    private ArrayList<Room> rooms;

    public HouseGraph(Handler handler) {
        rooms = new ArrayList<>();
        generateRooms(handler);
    }

    private void constructGraph(int numOfRooms) {
        // Graph is represented by an adjacency list using linked lists

        house = new LinkedList[numOfRooms];

        for(int i = 0; i < numOfRooms; i++) {
            house[i] = new LinkedList<>();
        }

    }

    private void generateRooms(Handler handler) {
        // Load rooms
        testRoom = new Room(handler, "testWorld.txt", 0);
        bedRoom = new Room(handler, "bedroom.txt", 1);
        hallwayRoom = new Room(handler, "hallway.txt", 2);
        lounge = new Room(handler, "lounge.txt", 3);
        bossRoom = new Room(handler, "bossRoom.txt", 4);

         // Store rooms
        rooms.add(testRoom);
        rooms.add(bedRoom);
        rooms.add(hallwayRoom);
        rooms.add(lounge);
        rooms.add(bossRoom);
    }

    private void generateHouse() {
        // Add bedroom and connections
        addRoomEntrance(0, 1);

        // Add hallway and connections
        addRoomEntrance(1, 0);
    }

    public void addRoomEntrance(int thisRoomID, int otherRoomID) {
        house[thisRoomID].add(0, otherRoomID);
    }

    public Room getRoom(int roomID) {
        return rooms.get(roomID);
    }


}
