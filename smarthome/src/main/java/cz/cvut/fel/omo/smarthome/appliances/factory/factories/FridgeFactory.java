package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.Fridge;
import cz.cvut.fel.omo.smarthome.appliances.sensors.FridgeSensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class FridgeFactory implements ApplianceFactory{

    /**
     * Creates appliance in specific room.
     *
     * @param room in which is appliance created into
     * @return appliance type
     */
    @Override
    public Appliance createAppliance(Room room) {
//        return new FridgeSensor(new Fridge(room));
        Sensor sensor = new FridgeSensor(new Fridge(room));
        return sensor.getAppliance();

//        return new Fridge(room);
    }
}
