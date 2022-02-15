package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.creatures.Person;

public class BlindsRoll extends EventBase{
    private Room room;
    private Person person;

    public BlindsRoll(Observer source, int t, Room room, Person person) {
        super(source, t);
        this.type = EventType.ROLL_BLINDS;
        this.room = room;
        this.person = person;
    }

    public Room getRoom() {
        return room;
    }

    public Person getPerson() {
        return person;
    }
}
