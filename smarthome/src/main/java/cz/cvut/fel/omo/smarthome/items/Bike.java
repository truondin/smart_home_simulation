package cz.cvut.fel.omo.smarthome.items;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.NextHourEvent;
import cz.cvut.fel.omo.smarthome.eventhandler.events.StartUsingSportItem;
import cz.cvut.fel.omo.smarthome.eventhandler.events.StopUsingSportItem;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bike extends SportItem{
    private static final int RIDE_TIME = 4;
    private int useTime = 0;

    private static final Logger log = Logger.getLogger(Bike.class.getName());


    public Bike(Room room) {
        super(room);
        this.type = SportItemType.Bike;
    }

    @Override
    public void update(NextHourEvent event) {
        if (!available){
            if (useTime >= RIDE_TIME){
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

            log.log(Level.INFO, "BIKE" + getName() + " are being used");
        }
    }

    @Override
    public String getName() {
        return type + " id:" + id;
    }
}
