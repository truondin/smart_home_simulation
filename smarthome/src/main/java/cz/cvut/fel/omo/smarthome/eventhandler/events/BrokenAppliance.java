package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

public class BrokenAppliance extends EventBase {
    private final Appliance appliance;

    public BrokenAppliance(Observer source, int t, Appliance appliance) {
        super(source, t);
        this.type = EventType.BREAK_APPLIANCE;
        this.appliance = appliance;

    }

    public Appliance getAppliance() {
        return appliance;
    }
}
