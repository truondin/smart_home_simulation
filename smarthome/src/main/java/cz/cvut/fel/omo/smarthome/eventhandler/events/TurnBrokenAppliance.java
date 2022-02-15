package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

public class TurnBrokenAppliance extends EventBase{
    private Appliance appliance;

    public TurnBrokenAppliance(Observer source, int t, Appliance appliance) {
        super(source, t);
        this.type = EventType.TURN_BROKEN_APPLIANCE;
        this.appliance = appliance;
    }

    public Appliance getAppliance() {
        return appliance;
    }
}
