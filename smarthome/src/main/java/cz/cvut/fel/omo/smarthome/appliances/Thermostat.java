package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOffAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOnAppliance;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Thermostat extends ApplianceBase{
    private int temperature;
    private static final Logger log = Logger.getLogger(Thermostat.class.getName());

    public Thermostat(Room room) {
        super(room);
        this.type = ApplianceType.THERMOSTAT;
        this.consumption = ApplianceType.THERMOSTAT.consumption;
        this.idleConsumption = ApplianceType.THERMOSTAT.idleConsumption;
        this.temperature = Constant.HOME_TEMPERATURE;

        room.addAppliance(this);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }


    @Override
    public void update(TurnOnAppliance event) {
        if (event.getAppliance().equals(this)){
            turnOn();
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
        state.changeToOn();
        log.log(Level.INFO,"Thermostat started heating");
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO,"Thermostat turned off");
    }

}
