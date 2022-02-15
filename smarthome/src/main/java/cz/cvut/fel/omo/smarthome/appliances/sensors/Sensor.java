package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;

public interface Sensor {
    /**
     * returns appliance of sensor
     * @return appliance
     */
    Appliance getAppliance();
}