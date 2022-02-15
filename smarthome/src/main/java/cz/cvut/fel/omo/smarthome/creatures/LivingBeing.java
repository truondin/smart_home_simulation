package cz.cvut.fel.omo.smarthome.creatures;

import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

public abstract class LivingBeing implements Observer {
    protected Room room;
    protected final EventHandler eventHandler = EventHandler.getInstance();

    public boolean isInHouse(){
        return room != null;
    }

    public Room getRoom() {
        return room;
    }

    public House getHouse(){
        return room.getFloor().getHouse();
    }


}
