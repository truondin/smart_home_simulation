package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.reports.Visitor;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

import java.util.List;

public interface Event {

    Observer getSource();

    List<Observer> getTargets();

    void addTarget(Observer target);

    boolean targetsContains(Observer target);

    void accept(Visitor visitor);

    EventType getType();
    int getTime();
}
