package cz.cvut.fel.omo.smarthome.building;

import cz.cvut.fel.omo.smarthome.reports.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private House house;
    private List<Room> rooms;
    private int level;

    public Floor(House house) {
        this.house = house;
        level = house.getHouseLevel();
        house.addHouseLevel();
    }

    public Floor() {
        level = -1;
    }

    public void addRoom(Room toAdd){
        if (rooms == null){
            rooms = new ArrayList<>();
        }

        if (!rooms.contains(toAdd)){
            rooms.add(toAdd);
        }
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
        level = house.getHouseLevel();
        house.addHouseLevel();
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void accept(Visitor visitor){
        visitor.visitFloor(this);
    }

    public int getLevel() {
        return level;
    }
}
