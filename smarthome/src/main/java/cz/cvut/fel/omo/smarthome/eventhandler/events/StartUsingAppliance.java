package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.creatures.Person;

public class StartUsingAppliance extends EventBase{

    private final Appliance appliance;
    private final Person user;

    public StartUsingAppliance(Observer source, Appliance appliance, Person person, int t) {
        super(source, t);
        this.type = EventType.START_USING_APPLIANCE;
        this.appliance = appliance;
        this.user = person;
    }

    public StartUsingAppliance(Observer source, Observer target, Appliance appliance, Person person, int t) {
        super(source, target, t);
        this.type = EventType.START_USING_APPLIANCE;
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
