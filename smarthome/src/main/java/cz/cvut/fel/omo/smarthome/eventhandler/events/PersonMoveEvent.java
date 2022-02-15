package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

public class PersonMoveEvent extends EventBase{
    private final Room toRoom;

    public PersonMoveEvent(Room room, Observer source, int t) {
        super(source, t);
        this.toRoom = room;
        this.type = EventType.PERSON_MOVE_EVENT;
    }

    public Room getToRoom() {
        return toRoom;
    }
}
