package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.Thermostat;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.ThermostatSensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class ThermostatFactory implements ApplianceFactory{

    /**
     * Creates appliance in specific room.
     *
     * @param room in which is appliance created into
     * @return appliance type
     */
    @Override
    public Appliance createAppliance(Room room) {
//        return new Thermostat(room);
//        return new ThermostatSensor(new Thermostat(room));
        Sensor sensor = new ThermostatSensor(new Thermostat(room));
        return sensor.getAppliance();
    }
}
