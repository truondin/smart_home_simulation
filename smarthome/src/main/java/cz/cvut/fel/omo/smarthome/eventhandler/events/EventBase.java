package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.reports.Visitor;

import java.util.ArrayList;
import java.util.List;

public abstract class EventBase implements Event{
    private final Observer source;
    private final List<Observer> targets;
    protected EventType type;
    private final int time;

    public EventBase(Observer source, int t) {
        this.source = source;
        targets = new ArrayList<>();
        this.time = t;
    }

    public EventBase(Observer source, List<Observer> targets, int t) {
        this.source = source;
        this.targets = targets;
        this.time = t;
    }

    public EventBase(Observer source, Observer target, int t) {
        this.source = source;
        targets = new ArrayList<>();
        targets.add(target);
        this.time = t;
    }

    @Override
    public Observer getSource() {
        return source;
    }

    @Override
    public List<Observer> getTargets() {
        return targets;
    }

    @Override
    public void addTarget(Observer target) {
        if (!targets.contains(target)){
            targets.add(target);
        }
    }

    @Override
    public boolean targetsContains(Observer target) {
        return targets.contains(target);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitEvent(this);
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public int getTime() {
        return time;
    }
}
