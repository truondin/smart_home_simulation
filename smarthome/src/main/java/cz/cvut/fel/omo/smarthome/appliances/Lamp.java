package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOffAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOnAppliance;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Lamp extends ApplianceBase{
    private static final Logger log = Logger.getLogger(Lamp.class.getName());

    public Lamp(Room room) {
        super(room);
        this.type = ApplianceType.LAMP;
        this.consumption = ApplianceType.LAMP.consumption;
        this.idleConsumption = ApplianceType.LAMP.idleConsumption;

        room.addAppliance(this);
    }


    @Override
    public void update(TurnOffAppliance event) {
        if (event.getAppliance().equals(this)){
            turnOff();
        }
    }

    @Override
    public void update(TurnOnAppliance event) {
        if (event.getAppliance().equals(this)){
            turnOn();
        }
    }

    @Override
    public void turnOn() {
        state.changeToOn();
        log.log(Level.INFO, "Light on");
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO, "Light off");
    }
}
