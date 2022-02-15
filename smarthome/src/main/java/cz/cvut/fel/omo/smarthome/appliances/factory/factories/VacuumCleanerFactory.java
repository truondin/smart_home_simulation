package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.VacuumCleaner;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.appliances.sensors.VacuumCleanerSensor;
import cz.cvut.fel.omo.smarthome.building.Room;

public class VacuumCleanerFactory implements ApplianceFactory{

    /**
     * Creates appliance in specific room.
     *
     * @param room in which is appliance created into
     * @return appliance type
     */
    @Override
    public Appliance createAppliance(Room room) {
//        return new VacuumCleaner(room);
//        return new VacuumCleanerSensor(new VacuumCleaner(room));

        Sensor sensor = new VacuumCleanerSensor(new VacuumCleaner(room));
        return  sensor.getAppliance();
    }
}
