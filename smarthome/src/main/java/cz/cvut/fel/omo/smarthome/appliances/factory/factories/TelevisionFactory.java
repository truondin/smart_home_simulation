package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.Television;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.TelevisionSensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class TelevisionFactory implements ApplianceFactory{

    /**
     * Creates appliance in specific room.
     *
     * @param room in which is appliance created into
     * @return appliance type
     */
    @Override
    public Appliance createAppliance(Room room) {
//        return new Television(room);
//        return new TelevisionSensor(new Television(room));
        Sensor sensor = new TelevisionSensor(new Television(room));
        return sensor.getAppliance();

    }
}
