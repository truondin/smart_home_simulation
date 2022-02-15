package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Fridge;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

public class FridgeSensor extends SensorDecorator<Fridge>{

    private static final int IDLE_TIME = 5;
    private static final int COOLING_TIME = 3;
    private int idleTime = 0;
    private int coolingTime = 0;

    public FridgeSensor(Fridge appliance) {
        super(appliance);
    }


    /**
     * Checks and updates fridge in new hour (tick)
     * @param event next hour event
     */
    @Override
    public void update(NextHourEvent event) {
        if (appliance.getApplianceState().isIdle()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getIdleConsumption());

            if (idleTime >= IDLE_TIME){
                idleTime = 0;
                eventHandler.addEvent(new TurnOnAppliance(appliance, appliance, eventHandler.getTime(), appliance));
            }else idleTime++;
        }else if(appliance.getApplianceState().isOn()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getConsumption());
            appliance.lowerDurability();

            if (coolingTime >= COOLING_TIME){
                coolingTime = 0;
                eventHandler.addEvent(new TurnIdleAppliance(appliance, appliance, eventHandler.getTime(), appliance));
            }else coolingTime++;
        }else if (appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new BrokenAppliance(appliance, eventHandler.getTime(), appliance));
        }

        if (willBreak() && !appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new TurnBrokenAppliance(appliance, eventHandler.getTime(), appliance));
        }
    }
}
