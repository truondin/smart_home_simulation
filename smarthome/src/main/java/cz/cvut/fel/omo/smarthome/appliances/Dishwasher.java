package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOffAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOnAppliance;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Dishwasher extends ApplianceBase{
    private static final Logger log = Logger.getLogger(Dishwasher.class.getName());

    public Dishwasher(Room room) {
        super(room);
        this.type = ApplianceType.DISHWASHER;
        this.consumption = ApplianceType.DISHWASHER.consumption;
        this.idleConsumption = ApplianceType.DISHWASHER.idleConsumption;

        room.addAppliance(this);
    }

    @Override
    public void update(TurnOnAppliance event) {
        if (event.targetsContains(this)){
            start();
        }
    }

    @Override
    public void update(TurnOffAppliance event) {
        if (event.targetsContains(this)){
            turnOff();
        }
    }

    @Override
    public void turnOn() {
        state.changeToIdle();
        eventHandler.addEvent(new TurnOnAppliance(this, this, eventHandler.getTime(), this));
        log.log(Level.INFO, "Dishwasher turned on and waiting to start");
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO, "Dishwasher turned off");
    }

    public void start(){
        state.changeToOn();
        log.log(Level.INFO, "Dishwasher started working");
    }
}
