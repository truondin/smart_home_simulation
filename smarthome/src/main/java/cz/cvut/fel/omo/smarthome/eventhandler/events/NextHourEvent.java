package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

public class NextHourEvent extends EventBase{

    public NextHourEvent(Observer source, int t) {
        super(source,t);
        this.type = EventType.NEXT_CYCLE_EVENT;
    }
}
