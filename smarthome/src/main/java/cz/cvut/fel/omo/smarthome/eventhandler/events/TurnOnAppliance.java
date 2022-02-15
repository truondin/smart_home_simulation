package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

import java.util.List;

public class TurnOnAppliance extends EventBase{
    private Appliance appliance;

    public TurnOnAppliance(Observer source, int t, Appliance appliance) {
        super(source, t);
        this.type = EventType.TURN_ON_APPLIANCE;
        this.appliance = appliance;
    }

    public TurnOnAppliance(Observer source, List<Observer> targets, int t,Appliance appliance) {
        super(source, targets, t);
        this.type = EventType.TURN_ON_APPLIANCE;
        this.appliance = appliance;
    }

    public TurnOnAppliance(Observer source, Observer target, int t, Appliance appliance) {
        super(source, target, t);
        this.type = EventType.TURN_ON_APPLIANCE;
        this.appliance = appliance;
    }

    public Appliance getAppliance() {
        return appliance;
    }
}
