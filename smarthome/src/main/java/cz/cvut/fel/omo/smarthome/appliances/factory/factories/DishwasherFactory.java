package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.Dishwasher;
import cz.cvut.fel.omo.smarthome.appliances.sensors.DishwasherSensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class DishwasherFactory implements ApplianceFactory{

    /**
     * Creates appliance in specific room.
     *
     * @param room in which is appliance created into
     * @return appliance type
     */
    @Override
    public Appliance createAppliance(Room room) {
//        Dishwasher curr = new Dishwasher(room);
//        new DishwasherSensor(curr);
        Sensor sensor = new DishwasherSensor(new Dishwasher(room));
        return sensor.getAppliance();
//        return curr;
    }
}
