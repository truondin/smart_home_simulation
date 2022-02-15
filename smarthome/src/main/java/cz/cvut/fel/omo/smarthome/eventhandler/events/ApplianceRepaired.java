package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

import java.util.List;

public class ApplianceRepaired extends EventBase{

    private final Appliance appliance;

    public ApplianceRepaired(Observer source, int t, Appliance appliance) {
        super(source, t);
        this.type = EventType.APPLIANCE_REPAIRED;
        this.appliance = appliance;
    }

    public ApplianceRepaired(Observer source, List<Observer> targets, int t, Appliance appliance) {
        super(source, targets, t);
        this.type = EventType.APPLIANCE_REPAIRED;
        this.appliance = appliance;
    }

    public ApplianceRepaired(Observer source, Observer target, int t, Appliance appliance) {
        super(source, target, t);
        this.type = EventType.APPLIANCE_REPAIRED;
        this.appliance = appliance;
    }

    public Appliance getAppliance() {
        return appliance;
    }
}
