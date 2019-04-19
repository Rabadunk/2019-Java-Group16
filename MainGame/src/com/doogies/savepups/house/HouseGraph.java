package com.doogies.savepups.house;

import com.doogies.savepups.Handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HouseGraph {

    private final int numOfRooms = 3;

    private Room bedRoom;
    private Room hallwayRoom;
    private Room testRoom;

    private List<Integer> house[];
    private ArrayList<Room> rooms;

    public HouseGraph(Handler handler) {
        constructGraph(numOfRooms);
        generateRooms(handler);
        generateHouse();
    }

    private void constructGraph(int numOfRooms) {
        // Graph is represented by an adjacency list using linked lists

        house = new LinkedList[numOfRooms];
        rooms = new ArrayList<>();

        for(int i = 0; i < numOfRooms; i++) {
            house[i] = new LinkedList<>();
        }

    }

    private void generateRooms(Handler handler) {
        // Load rooms
        bedRoom = new Room(handler, "res/rooms/bedroom.txt", "res/rooms/statics/bedroom.txt", 0);
        hallwayRoom = new Room(handler, "res/rooms/hallway.txt", "res/rooms/statics/hallway.txt",1);
        testRoom = new Room(handler, "res/rooms/testWorld.txt", "res/rooms/statics/hallway.txt",2);

        // Store rooms
        rooms.add(bedRoom);
        rooms.add(hallwayRoom);
        rooms.add(testRoom);
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
