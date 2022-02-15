package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.Dishwasher;

public class DishwasherAPI {

    private final Dishwasher dishwasher;

    public DishwasherAPI(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void turnOn(){
        dishwasher.turnOn();
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void turnOff(){
        dishwasher.turnOff();
    }

    /**
     * Basic method to make device work.
     *
     * Changes current state to ON.
     */
    public void start(){
        dishwasher.start();
    }

}
