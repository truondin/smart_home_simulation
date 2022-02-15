package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

import java.util.List;

public class RemoveItemFromFridge extends EventBase{
    public RemoveItemFromFridge(Observer source, int t) {
        super(source, t);
        this.type = EventType.REMOVE_ITEM_TO_FOOD;
    }

    public RemoveItemFromFridge(Observer source, List<Observer> targets, int t) {
        super(source, targets, t);
        this.type = EventType.REMOVE_ITEM_TO_FOOD;
    }

    public RemoveItemFromFridge(Observer source, Observer target, int t) {
        super(source, target, t);
        this.type = EventType.REMOVE_ITEM_TO_FOOD;
    }
}
