package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.creatures.Person;

public class StopUsingSportItem extends EventBase{
    private Person user;
    private SportItem item;

    public StopUsingSportItem(Observer source, Person person, SportItem item, int t) {
        super(source, t);
        this.type = EventType.STOP_USING_SPORT_ITEM;
        this.user = person;
        this.item = item;
    }

    public StopUsingSportItem(Observer source, Observer target, Person person, SportItem item, int t) {
        super(source, target, t);
        this.type = EventType.STOP_USING_SPORT_ITEM;
        this.user = person;
        this.item = item;
    }

    public Person getUser() {
        return user;
    }

    public SportItem getItem() {
        return item;
    }
}
