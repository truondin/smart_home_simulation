package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.VacuumCleaner;

public class VacuumCleanerAPI {
    private final VacuumCleaner vacuumCleaner;

    public VacuumCleanerAPI(VacuumCleaner vacuumCleaner) {
        this.vacuumCleaner = vacuumCleaner;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void turnOn() {
        vacuumCleaner.turnOn();
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void turnOff(){
        vacuumCleaner.turnOff();
    }

    /**
     * Basic method to make device work.
     *
     * Changes current state to ON.
     */
    public void start(){
        vacuumCleaner.startVacuuming();
    }
}
