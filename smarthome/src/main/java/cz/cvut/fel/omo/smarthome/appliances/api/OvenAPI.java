package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.Oven;

public class OvenAPI {

    private final Oven oven;

    public OvenAPI(Oven oven) {
        this.oven = oven;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void turnOn(){
        oven.turnOn();
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void turnOff(){
        oven.turnOff();
    }

    /**
     * Basic method to make device work.
     *
     * Changes current state to ON.
     */
    public void bake(){
        oven.startOven();
    }
}
