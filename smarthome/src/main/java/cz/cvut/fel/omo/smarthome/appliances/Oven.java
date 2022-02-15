package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOffAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOnAppliance;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Oven extends ApplianceBase{
    private static final Logger log = Logger.getLogger(Oven.class.getName());

    public Oven(Room room) {
        super(room);
        this.type = ApplianceType.OVEN;
        this.consumption = ApplianceType.OVEN.consumption;
        this.idleConsumption = ApplianceType.OVEN.idleConsumption;

        room.addAppliance(this);
    }

    @Override
    public void update(TurnOnAppliance event) {
        if (event.getAppliance().equals(this)){
            startOven();
        }
    }

    @Override
    public void update(TurnOffAppliance event) {
        if (event.getAppliance().equals(this)){
            turnOff();
        }
    }

    @Override
    public void turnOn() {
        state.changeToIdle();
        eventHandler.addEvent(new TurnOnAppliance(this, this, eventHandler.getTime(), this));
        log.log(Level.INFO,"Oven turned on. Waiting to start.");
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO,"Oven turned off");
    }

    public void startOven(){
        state.changeToOn();
        log.log(Level.INFO,"Oven started baking");
    }
}
