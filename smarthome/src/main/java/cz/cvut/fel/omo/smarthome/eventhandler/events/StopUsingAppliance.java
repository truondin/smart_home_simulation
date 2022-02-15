package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.creatures.Person;

public class StopUsingAppliance extends EventBase{

    private Appliance appliance;
    private Person user;

    public StopUsingAppliance(Observer source, Appliance appliance, Person person, int t) {
        super(source, t);
        this.type = EventType.STOP_USING_APPLIANCE;
        this.appliance = appliance;
        this.user = person;
    }

    public StopUsingAppliance(Observer source, Observer target, Appliance appliance, Person person, int t) {
        super(source, target, t);
        this.type = EventType.STOP_USING_APPLIANCE;
        this.appliance = appliance;
        this.user = person;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public Person getUser() {
        return user;
    }
}
