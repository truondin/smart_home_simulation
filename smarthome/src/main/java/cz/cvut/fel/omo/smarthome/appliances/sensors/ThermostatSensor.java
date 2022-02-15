package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Thermostat;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThermostatSensor extends SensorDecorator<Thermostat>{

    private static final Logger log = Logger.getLogger(ThermostatSensor.class.getName());

    public ThermostatSensor(Thermostat appliance) {
        super(appliance);
    }

    /**
     * Checks temperature of room
     *
     * activates if temperature is too low
     * deactivates if temperature is too high
     */
    private void checkTemperature(){
        log.log(Level.INFO, "Temperature: " + appliance.getTemperature());

        if (appliance.getTemperature() >= 24 && !appliance.getApplianceState().isOn()){
            eventHandler.addEvent(new TurnOffAppliance(appliance, appliance, eventHandler.getTime(), appliance));
            log.log(Level.INFO, "Low temperature, start heating");
        }else if (appliance.getTemperature() <= 18 && !appliance.getApplianceState().isOff()){
            eventHandler.addEvent(new TurnOnAppliance(appliance, appliance, eventHandler.getTime(), appliance));
            log.log(Level.INFO, "High temperature, stop heating");
        }
    }

    /**
     * Checks and updates thermostat on new hour
     * @param event next hour event
     */
    @Override
    public void update(NextHourEvent event) {
        checkTemperature();

        if (appliance.getApplianceState().isOn()){
            appliance.setTemperature(appliance.getTemperature() + 2);
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getConsumption());
            appliance.lowerDurability();

        }else if(appliance.getApplianceState().isOff()){
            appliance.setTemperature(appliance.getTemperature() - 1);

        }else if (appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new BrokenAppliance(appliance, eventHandler.getTime(), appliance));
        }
    }
}
