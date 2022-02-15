package cz.cvut.fel.omo.smarthome.eventhandler.events;

import cz.cvut.fel.omo.smarthome.eventhandler.Observer;

public class TaskFinished extends EventBase{
    public TaskFinished(Observer source, int t) {
        super(source, t);
        this.type = EventType.TASK_FINISHED;
    }
}
