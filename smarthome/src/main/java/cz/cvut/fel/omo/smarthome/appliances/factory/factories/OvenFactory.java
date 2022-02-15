package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.Oven;
import cz.cvut.fel.omo.smarthome.appliances.sensors.OvenSensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class OvenFactory implements ApplianceFactory{

    /**
     * Creates appliance in specific room.
     *
     * @param room in which is appliance created into
     * @return appliance type
     */
    @Override
    public Appliance createAppliance(Room room) {
//        return new Oven(room);
        Sensor sensor =  new OvenSensor(new Oven(room));
        return sensor.getAppliance();
//        return new OvenSensor(new Oven(room));
    }
}
