package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.Thermostat;

public class ThermostatAPI {

    private final Thermostat thermostat;

    public ThermostatAPI(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void startHeating(){
        thermostat.turnOn();
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void stopHeating(){
        thermostat.turnOff();
    }
}
