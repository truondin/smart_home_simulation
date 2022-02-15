package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.Lamp;

public class LampAPI {

    private final Lamp lamp;

    public LampAPI(Lamp lamp) {
        this.lamp = lamp;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void turnOn(){
        lamp.turnOn();
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void turnOff(){
        lamp.turnOff();
    }
}
