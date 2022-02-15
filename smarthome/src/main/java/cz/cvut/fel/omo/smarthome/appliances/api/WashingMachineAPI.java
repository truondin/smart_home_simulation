package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.WashingMachine;

public class WashingMachineAPI {

    private final WashingMachine washingMachine;

    public WashingMachineAPI(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void turnOn(){
        washingMachine.turnOn();
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void turnOff(){
        washingMachine.turnOff();
    }

    /**
     * Basic method to make device work.
     *
     * Changes current state to ON.
     */
    public void startWasher(){
        washingMachine.startWasher();
    }
}
