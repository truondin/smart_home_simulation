package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

public class PetMoveEvent extends EventBase{
    private Room toRoom;

    public PetMoveEvent(Room room, Observer source, int t) {
        super(source, t);
        toRoom = room;
        this.type = EventType.PET_MOVE_EVENT;
    }

    public Room getToRoom() {
        return toRoom;
    }
}
