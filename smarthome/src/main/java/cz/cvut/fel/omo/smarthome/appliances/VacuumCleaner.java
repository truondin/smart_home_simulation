package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOnAppliance;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VacuumCleaner extends ApplianceBase{
    private static final Logger log = Logger.getLogger(VacuumCleaner.class.getName());

    public VacuumCleaner(Room room) {
        super(room);
        this.type = ApplianceType.VACUUM_CLEANER;
        this.consumption = ApplianceType.VACUUM_CLEANER.consumption;
        this.idleConsumption = ApplianceType.VACUUM_CLEANER.idleConsumption;

        room.addAppliance(this);
    }


    @Override
    public void update(TurnOnAppliance event) {
        if (event.getAppliance().equals(this)){
            startVacuuming();
        }
    }

    @Override
    public void turnOn() {
        state.changeToIdle();
        log.log(Level.INFO,"Vacuum cleaner turned on and waiting to start");
        eventHandler.addEvent(new TurnOnAppliance(this, this, eventHandler.getTime(), this));
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO,"Vacuum cleaner turned off");
    }

    public void startVacuuming(){
        state.changeToOn();
        log.log(Level.INFO,"Vacuum cleaner started vacuuming");
    }
}
