package cz.cvut.fel.omo.smarthome.items;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.Utils;

public abstract class SportItem implements Observer {
    protected int id;
    protected Person user;
    protected boolean available;
    protected SportItemType type;
    protected final Room room;

    protected final EventHandler eventHandler = EventHandler.getInstance();

    public SportItem(Room room) {
        this.user = null;
        this.available = true;
        this.room = room;
        this.id = Utils.randomInt(10000);
        room.addSportItem(this);
        room.getFloor().getHouse().addSportItem(this);
        eventHandler.attach(this);
    }

    public void accept(Visitor visitor){
        visitor.visitSportItem(this);
    }

    public int getId() {
        return id;
    }

    public Person getUser(){
        return user;
    }

    public void setUser(Person user){
        this.user = user;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailability(boolean availability){
        available = availability;
    }

    public SportItemType getType() {
        return type;
    }

    public Room getRoom() {
        return room;
    }

}
