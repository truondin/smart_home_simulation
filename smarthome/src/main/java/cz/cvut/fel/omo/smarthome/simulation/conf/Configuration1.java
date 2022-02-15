package cz.cvut.fel.omo.smarthome.simulation.conf;

import cz.cvut.fel.omo.smarthome.appliances.factory.Factory;
import cz.cvut.fel.omo.smarthome.building.*;
import cz.cvut.fel.omo.smarthome.building.builder.HouseBuilder;
import cz.cvut.fel.omo.smarthome.items.Bike;
import cz.cvut.fel.omo.smarthome.items.Ski;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.creatures.PersonRole;
import cz.cvut.fel.omo.smarthome.creatures.Pet;

public class Configuration1 {
    private House house;

    public Configuration1() {
        // creating house
        HouseBuilder houseBuilder = new HouseBuilder("Truongovic sidlo");

        Floor f0 = new Floor();
        Floor f1 = new Floor();

        Room r0_1 = new Room(f0, RoomType.LOBBY);
        Room r0_2 = new Room(f0, RoomType.LIVING_ROOM);
        Room r0_3 = new Room(f0, RoomType.DINING_ROOM);
        Room r0_4 = new Room(f0, RoomType.KITCHEN);
        Room r0_5 = new Room(f0, RoomType.BATHROOM);

        Room r1_1 = new Room(f1);
        Room r1_2 = new Room(f1);
        Room r1_3 = new Room(f1);
        Room r1_4 = new Room(f1,RoomType.BATHROOM);

        Blind b1 = new Blind();
        Window w1 = new Window(b1, r0_1);
        Blind b2 = new Blind();
        Window w2 = new Window(b2, r0_2);
        Blind b3 = new Blind();
        Window w3 = new Window(b3, r0_3);
        Blind b4 = new Blind();
        Window w4 = new Window(b4, r0_4);
        Blind b5 = new Blind();
        Window w5 = new Window(b5,r0_5);
        Blind b6 = new Blind();
        Window w6 = new Window(b6, r1_1);
        Blind b7 = new Blind();
        Window w7 = new Window(b7, r1_2);
        Blind b8 = new Blind();
        Window w8 = new Window(b8, r1_3);
        Blind b9 = new Blind();
        Window w9 = new Window(b9, r1_4);

        houseBuilder.withFloors(f0);
        houseBuilder.withFloors(f1);

        houseBuilder.withRooms(r0_1);
        houseBuilder.withRooms(r0_2);
        houseBuilder.withRooms(r0_3);
        houseBuilder.withRooms(r0_4);
        houseBuilder.withRooms(r0_5);

        houseBuilder.withRooms(r1_1);
        houseBuilder.withRooms(r1_2);
        houseBuilder.withRooms(r1_3);
        houseBuilder.withRooms(r1_4);

        houseBuilder.withWindows(w1);
        houseBuilder.withWindows(w2);
        houseBuilder.withWindows(w3);
        houseBuilder.withWindows(w4);
        houseBuilder.withWindows(w5);
        houseBuilder.withWindows(w6);
        houseBuilder.withWindows(w7);
        houseBuilder.withWindows(w8);
        houseBuilder.withWindows(w9);

        house = houseBuilder.build();

        // creating appliances in house
        Factory factory = new Factory();
        factory.createDishwasher(r0_4);
        factory.createFridge(r0_4);

        factory.createLamp(r0_1);
        factory.createLamp(r0_2);
        factory.createLamp(r0_3);
        factory.createLamp(r0_4);
        factory.createLamp(r0_5);
        factory.createLamp(r1_1);
        factory.createLamp(r1_2);
        factory.createLamp(r1_3);
        factory.createLamp(r1_4);

        factory.createOven(r0_4);

        factory.createTelevision(r0_2);
        factory.createTelevision(r1_1);
        factory.createTelevision(r1_2);
        factory.createTelevision(r1_3);

        factory.createThermostat(r0_1);
        factory.createThermostat(r0_2);
        factory.createThermostat(r0_3);
        factory.createThermostat(r0_4);
        factory.createThermostat(r0_5);
        factory.createThermostat(r1_1);
        factory.createThermostat(r1_2);
        factory.createThermostat(r1_3);
        factory.createThermostat(r1_4);

        factory.createVacuumCleaner(r0_1);
        factory.createVacuumCleaner(r1_3);

        factory.createWashingMachine(r0_5);

        //creating sport items
        Ski ski1 = new Ski(r0_1);
        Ski ski2 = new Ski(r0_1);
        Ski ski3 = new Ski(r0_1);

        Bike bike1 = new Bike(r0_1);
        Bike bike2 = new Bike(r0_1);
        Bike bike3 =new Bike(r0_1);

        // creating people
        Person father = new Person("Pavel", "Truong",50, PersonRole.Father);
        Person mother = new Person("Pavlina", "Truong",40, PersonRole.Mother);
        Person child1 = new Person("Pavlinka", "Truong",16, PersonRole.Child);
        Person child2 = new Person("Pavel Jr.", "Truong",14, PersonRole.Child);
        Person child3 = new Person("Pavel Jr. II.", "Truong",7, PersonRole.Child);

        father.setRoom(r0_2);
        mother.setRoom(r0_4);
        child1.setRoom(r1_1);
        child2.setRoom(r1_1);
        child3.setRoom(r1_3);

        // creating pet
        Pet dog = new Pet("Hafik", "Dog");
        dog.setRoom(r0_1);
    }

    public House getHouse(){
        return house;
    }
}
