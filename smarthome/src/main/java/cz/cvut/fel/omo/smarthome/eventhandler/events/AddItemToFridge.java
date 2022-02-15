package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

import java.util.List;

public class AddItemToFridge extends EventBase{
    public AddItemToFridge(Observer source, int t) {
        super(source, t);
        this.type = EventType.ADD_ITEM_TO_FOOD;
    }

    public AddItemToFridge(Observer source, List<Observer> targets, int t) {
        super(source, targets, t);
        this.type = EventType.ADD_ITEM_TO_FOOD;

    }

    public AddItemToFridge(Observer source, Observer target, int t) {
        super(source, target, t);
        this.type = EventType.ADD_ITEM_TO_FOOD;

    }
}
