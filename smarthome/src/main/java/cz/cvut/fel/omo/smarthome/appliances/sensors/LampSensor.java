package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Lamp;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LampSensor extends SensorDecorator<Lamp>{
    private static final Logger log = Logger.getLogger(LampSensor.class.getName());

    public LampSensor(Lamp appliance) {
        super(appliance);
    }

    /**
     * Checks if room of lamp is empty
     * @return boolean
     */
    private boolean roomIsEmpty(){
        return appliance.getRoom().getPeopleInRoom().isEmpty();
    }

    /**
     * Checks and updates lamp in new hour (tick)
     * @param event next hour event
     */
    @Override
    public void update(NextHourEvent event) {
        if (appliance.getApplianceState().isOn()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getConsumption());
            appliance.lowerDurability();


            if (roomIsEmpty()){
                eventHandler.addEvent(new TurnOffAppliance(appliance,appliance, eventHandler.getTime(), appliance));
            }
        }else if (appliance.getApplianceState().isOff() && !roomIsEmpty()){
            eventHandler.addEvent(new TurnOnAppliance(appliance, appliance, eventHandler.getTime(), appliance));
        }else if (appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new BrokenAppliance(appliance, eventHandler.getTime(), appliance));
        }

        if (willBreak() && !appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new TurnBrokenAppliance(appliance, eventHandler.getTime(), appliance));
            log.log(Level.INFO, "Lamp (id:" + appliance.getId() + ") broke down");

        }
    }
}
