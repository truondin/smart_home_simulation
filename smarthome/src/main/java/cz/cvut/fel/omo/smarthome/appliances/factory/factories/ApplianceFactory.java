package cz.cvut.fel.omo.smarthome.appliances.factory.factories;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.building.Room;

public interface ApplianceFactory {
    Appliance createAppliance(Room room);
}
