package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.WashingMachine;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.WashingMachineSensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class WashingMachineFactory implements ApplianceFactory{
    @Override
    public Appliance createAppliance(Room room) {
//        return new Fridge(room);
//        return new FridgeSensor(new Fridge(room));
        Sensor sensor = new WashingMachineSensor(new WashingMachine(room));
        return sensor.getAppliance();

    }
}
