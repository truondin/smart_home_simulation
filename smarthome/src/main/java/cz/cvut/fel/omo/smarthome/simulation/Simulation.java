package cz.cvut.fel.omo.smarthome.simulation;

import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.events.NextHourEvent;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class Simulation {
    private final EventHandler eventHandler = EventHandler.getInstance();
    private final House house;

    public Simulation(House house) {
        this.house = house;
    }

    public void run(int days){
        int simulationTime = days * Constant.HOURS_IN_DAY;

        for (int i = 1; i <= simulationTime+1; i++){
            eventHandler.notifyObservers(i);

            eventHandler.addEvent(new NextHourEvent(null, i));
        }
    }


    public House getHouse() {
        return house;
    }
}
