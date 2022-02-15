package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Oven;
import cz.cvut.fel.omo.smarthome.eventhandler.events.BrokenAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.NextHourEvent;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TaskFinished;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnBrokenAppliance;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvenSensor extends SensorDecorator<Oven>{
    private static final int BAKE_TIME = 2;
    private int bakeTime = 0;

    private static final Logger log = Logger.getLogger(OvenSensor.class.getName());

    public OvenSensor(Oven appliance) {
        super(appliance);
    }

    /**
     * Checks and updates oven by next hour (tick)
     * @param event next hour event
     */
    @Override
    public void update(NextHourEvent event) {
        if (appliance.getApplianceState().isOn()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getConsumption());
            appliance.lowerDurability();

            if (bakeTime >= BAKE_TIME){
                bakeTime = 0;
                eventHandler.addEvent(new TaskFinished(appliance, eventHandler.getTime()));
            }else bakeTime++;

        }else if(appliance.getApplianceState().isIdle()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getIdleConsumption());
        }else if (appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new BrokenAppliance(appliance, eventHandler.getTime(), appliance));
        }


        if (willBreak() && !appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new TurnBrokenAppliance(appliance, eventHandler.getTime(), appliance));
            log.log(Level.INFO, "Oven (id:" + appliance.getId() + ") broke down");
        }
    }
}
