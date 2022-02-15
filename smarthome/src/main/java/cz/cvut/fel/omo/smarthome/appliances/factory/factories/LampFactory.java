package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.Lamp;
import cz.cvut.fel.omo.smarthome.appliances.sensors.LampSensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class LampFactory implements ApplianceFactory{

    /**
     * Creates appliance in specific room.
     *
     * @param room in which is appliance created into
     * @return appliance type
     */
    @Override
    public Appliance createAppliance(Room room) {
        Sensor sensor = new LampSensor(new Lamp(room));
        return sensor.getAppliance();
//        return new LampSensor(new Lamp(room));
//        return new Lamp(room);
    }
}
