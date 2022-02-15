package cz.cvut.fel.omo.smarthome.appliances.factory;

import cz.cvut.fel.omo.smarthome.appliances.factory.factories.*;
import cz.cvut.fel.omo.smarthome.building.Room;

public class Factory {
    private final OvenFactory ovenFactory;
    private final DishwasherFactory dishwasherFactory;
    private final FridgeFactory fridgeFactory;
    private final LampFactory lampFactory;
    private final TelevisionFactory televisionFactory;
    private final ThermostatFactory thermostatFactory;
    private final VacuumCleanerFactory vacuumCleanerFactory;
    private final WashingMachineFactory washingMachineFactory;

    public Factory() {
        ovenFactory = new OvenFactory();
        dishwasherFactory = new DishwasherFactory();
        fridgeFactory = new FridgeFactory();
        lampFactory = new LampFactory();
        televisionFactory = new TelevisionFactory();
        thermostatFactory = new ThermostatFactory();
        vacuumCleanerFactory = new VacuumCleanerFactory();
        washingMachineFactory = new WashingMachineFactory();
    }

    public void createOven(Room inRoom){
        ovenFactory.createAppliance(inRoom);
    }

    public void createDishwasher(Room inRoom){
        dishwasherFactory.createAppliance(inRoom);
    }

    public void createFridge(Room inRoom){
        fridgeFactory.createAppliance(inRoom);
    }

    public void createLamp(Room inRoom){
        lampFactory.createAppliance(inRoom);
    }

    public void createTelevision(Room inRoom){
        televisionFactory.createAppliance(inRoom);
    }

    public void createThermostat(Room inRoom){
        thermostatFactory.createAppliance(inRoom);
    }

    public void createVacuumCleaner(Room inRoom){
        vacuumCleanerFactory.createAppliance(inRoom);
    }

    public void createWashingMachine(Room inRoom){
        washingMachineFactory.createAppliance(inRoom);
    }
}
