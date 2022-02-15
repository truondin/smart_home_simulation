package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Dishwasher;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DishwasherSensor extends SensorDecorator<Dishwasher>{
    private static final int WASH_TIME = 3;
    private int activeTime = 0;

    private static final Logger log = Logger.getLogger(DishwasherSensor.class.getName());


    public DishwasherSensor(Dishwasher appliance) {
        super(appliance);
    }


    /**
     * Checks and updates dishwasher in new hour (tick)
     * @param event next hour event
     */
    @Override
    public void update(NextHourEvent event) {
        super.update(event);
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
            eventHandler.addEvent(new BrokenAppliance(appliance, eventHandler.getTime(), appliance));
        }

        if (willBreak() && !appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new TurnBrokenAppliance(appliance, eventHandler.getTime(), appliance));
            log.log(Level.INFO, "Dishwasher (id:" + appliance.getId() + ") broke down");

        }

    }
}
