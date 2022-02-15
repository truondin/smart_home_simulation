package cz.cvut.fel.omo.smarthome.creatures;

import cz.cvut.fel.omo.smarthome.eventhandler.events.NextHourEvent;
import cz.cvut.fel.omo.smarthome.reports.Visitor;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.PetMoveEvent;
import cz.cvut.fel.omo.smarthome.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pet extends LivingBeing{
    private String name;
    private String breed;

    private static final Logger log = Logger.getLogger(Person.class.getName());

    public Pet(String name, String breed) {
        this.name = name;
        this.breed = breed;
        eventHandler.attach(this);
    }

    public void setRoom(Room room) {
        this.room = room;
        room.getFloor().getHouse().addPet(this);
    }

    public void accept(Visitor visitor){
        visitor.visitPet(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    private void moveToRoom(Room room){
        if (getRoom() != null) getRoom().removePet(this);
        this.room = room;
        room.addPet(this);

        log.log(Level.INFO, "Pet " + this.name + " moved to room " + room.getRoomType());
        eventHandler.addEvent(new PetMoveEvent(room,this, eventHandler.getTime()));
    }

    @Override
    public void update(NextHourEvent event) {
        List<Room> rooms = new ArrayList<>();

        getHouse().getFloors().forEach(floor -> rooms.addAll(floor.getRooms()));

        // go to random room
        if (!rooms.isEmpty() && Utils.randomBoolean()){
            moveToRoom(rooms.get(Utils.randomInt(rooms.size())));
        }
    }

}
