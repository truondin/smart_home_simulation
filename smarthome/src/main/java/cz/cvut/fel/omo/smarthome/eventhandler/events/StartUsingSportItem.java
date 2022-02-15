package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.creatures.Person;

public class StartUsingSportItem extends EventBase{

    private SportItem item;
    private Person user;

    public StartUsingSportItem(Observer source, SportItem item, Person person, int t) {
        super(source, t);
        this.type = EventType.START_USING_SPORT_ITEM;
        this.item = item;
        this.user = person;
    }

    public StartUsingSportItem(Observer source, Observer target, SportItem item, Person person, int t) {
        super(source, target, t);
        this.type = EventType.START_USING_SPORT_ITEM;
        this.item = item;
        this.user = person;
    }

    public SportItem getItem() {
        return item;
    }

    public Person getUser() {
        return user;
    }
}
