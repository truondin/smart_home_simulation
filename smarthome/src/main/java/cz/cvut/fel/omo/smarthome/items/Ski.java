package cz.cvut.fel.omo.smarthome.items;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ski extends SportItem{
    private static final int SKI_TIME = 3;
    private int useTime = 0;

    private static final Logger log = Logger.getLogger(Ski.class.getName());

    public Ski(Room room) {
        super(room);
        this.type = SportItemType.Ski;
    }

    @Override
    public void update(NextHourEvent event) {
        if (!available){
            if (useTime >= SKI_TIME){
                useTime = 0;

                eventHandler.addEvent(new StopUsingSportItem(this, user, user,this, eventHandler.getTime()));

                user = null;
                available = true;

            }else useTime++;
        }
    }

    @Override
    public void update(StartUsingSportItem event) {
        if (this.equals(event.getItem())){
            this.user = event.getUser();
            available = false;

            log.log(Level.INFO, "SKI" + getName() + " are being used");
        }
    }

    @Override
    public String getName() {
        return type + " id:" + id;
    }
}
