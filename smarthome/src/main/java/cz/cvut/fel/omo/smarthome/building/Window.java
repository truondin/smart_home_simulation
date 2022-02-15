package cz.cvut.fel.omo.smarthome.building;

import cz.cvut.fel.omo.smarthome.reports.Visitor;

public class Window {

    private Room room;
    private Blind blind;

    public Window(Room room) {
        this.room = room;
    }

    public Window(Blind blind, Room room) {
        this.blind = blind;
        this.room = room;
    }

    public Blind getBlind() {
        return blind;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void accept(Visitor visitor){
        visitor.visitWindow(this);
    }
}
