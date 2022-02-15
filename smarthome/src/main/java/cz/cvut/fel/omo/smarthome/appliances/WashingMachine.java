package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOnAppliance;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WashingMachine extends ApplianceBase{

    private static final Logger log = Logger.getLogger(WashingMachine.class.getName());

    public WashingMachine(Room room) {
        super(room);
        this.type = ApplianceType.WASHING_MACHINE;
        this.consumption = ApplianceType.WASHING_MACHINE.consumption;
        this.idleConsumption = ApplianceType.WASHING_MACHINE.idleConsumption;

        room.addAppliance(this);
    }

    @Override
    public void update(TurnOnAppliance event) {
        if (event.getAppliance().equals(this)){
            startWasher();
        }
    }

    @Override
    public void turnOn() {
        state.changeToIdle();
        log.log(Level.INFO, "Washing machine turned on and waiting for start");
        eventHandler.addEvent(new TurnOnAppliance(this, this, eventHandler.getTime(), this));
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO, "Washing machine turned off.");
    }

    public void startWasher(){
        state.changeToOn();

        log.log(Level.INFO, "Washing machine started washing sequence.");
    }
}
