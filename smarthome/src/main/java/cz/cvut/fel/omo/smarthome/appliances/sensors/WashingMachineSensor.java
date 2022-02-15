package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.WashingMachine;
import cz.cvut.fel.omo.smarthome.eventhandler.events.BrokenAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.NextHourEvent;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TaskFinished;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WashingMachineSensor extends SensorDecorator<WashingMachine>{
    private static final int WASH_TIME = 3;
    private int activeTime = 0;

    private static final Logger log = Logger.getLogger(WashingMachineSensor.class.getName());

    public WashingMachineSensor(WashingMachine appliance) {
        super(appliance);
    }

    /**
     * Checks and updates washing machine on next hour
     * @param event
     */
    @Override
    public void update(NextHourEvent event) {
        if (appliance.getApplianceState().isOn()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getConsumption());
            appliance.lowerDurability();

            if (activeTime >= WASH_TIME){
                appliance.getApplianceState().changeToIdle();
                eventHandler.addEvent(new TaskFinished(appliance, eventHandler.getTime()));
                activeTime = 0;
            }else activeTime++;
        }else if (appliance.getApplianceState().isIdle()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getIdleConsumption());
        }else if (appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new BrokenAppliance(appliance,eventHandler.getTime(), appliance));
        }

        if (willBreak() && !appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new BrokenAppliance(appliance,eventHandler.getTime(), appliance));
            log.log(Level.INFO, "Washing machine (id:" + appliance.getId() + ") broke down");
        }
    }
}
