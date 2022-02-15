package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.Fridge;

public class FridgeAPI {

    private final Fridge fridge;

    public FridgeAPI(Fridge fridge) {
        this.fridge = fridge;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void turnOn(){
        fridge.turnOn();
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void turnOff(){
        fridge.turnOff();
    }

}
