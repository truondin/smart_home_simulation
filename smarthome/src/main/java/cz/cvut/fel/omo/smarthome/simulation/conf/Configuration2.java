package cz.cvut.fel.omo.smarthome.simulation.conf;

import cz.cvut.fel.omo.smarthome.appliances.factory.Factory;
import cz.cvut.fel.omo.smarthome.building.*;
import cz.cvut.fel.omo.smarthome.building.builder.HouseBuilder;
import cz.cvut.fel.omo.smarthome.items.Bike;
import cz.cvut.fel.omo.smarthome.items.Ski;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.creatures.PersonRole;
import cz.cvut.fel.omo.smarthome.creatures.Pet;

public class Configuration2 {

    private House house;

    public Configuration2() {
        //creating house
        HouseBuilder houseBuilder = new HouseBuilder("Baronovic chatka");

        Floor f0 = new Floor();
        Floor f1 = new Floor();
        Floor f2 = new Floor();

        Room r0_1 = new Room(f0, RoomType.LOBBY);
        Room r0_2 = new Room(f0, RoomType.KITCHEN);
        Room r0_3 = new Room(f0, RoomType.DINING_ROOM);
        Room r0_4 = new Room(f0, RoomType.BATHROOM);

        Room r1_1 = new Room(f1, RoomType.BATHROOM);
        Room r1_2 = new Room(f1, RoomType.LIVING_ROOM);
        Room r1_3 = new Room(f1, RoomType.REGULAR_ROOM);

        Room r2_1 = new Room(f2, RoomType.BATHROOM);
        Room r2_2 = new Room(f2, RoomType.BATHROOM);
        Room r2_3 = new Room(f2, RoomType.REGULAR_ROOM);
        Room r2_4 = new Room(f2, RoomType.REGULAR_ROOM);
        Room r2_5 = new Room(f2, RoomType.REGULAR_ROOM);
        Room r2_6 = new Room(f2, RoomType.REGULAR_ROOM);

        Blind b1 = new Blind();
        Window w1 = new Window(b1, r0_2);
        Blind b2 = new Blind();
        Window w2 = new Window(b2, r0_2);
        Blind b3 = new Blind();
        Window w3 = new Window(b3, r0_3);
        Blind b4 = new Blind();
        Window w4 = new Window(b4, r0_4);
        Blind b5 = new Blind();
        Window w5 = new Window(b5, r1_1);
        Blind b6 = new Blind();
        Window w6 = new Window(b6, r1_2);
        Blind b7 = new Blind();
        Window w7 = new Window(b7, r1_2);
        Blind b8 = new Blind();
        Window w8 = new Window(b8, r1_3);
        Blind b9 = new Blind();
        Window w9 = new Window(b9, r2_1);
        Blind b10 = new Blind();
        Window w10 = new Window(b10, r2_2);
        Blind b11 = new Blind();
        Window w11 = new Window(b11, r2_3);
        Blind b12 = new Blind();
        Window w12 = new Window(b12, r2_4);
        Blind b13 = new Blind();
        Window w13 = new Window(b13, r2_5);
        Blind b14 = new Blind();
        Window w14 = new Window(b14, r2_6);
        Blind b15 = new Blind();
        Window w15 = new Window(b15, r2_6);

        houseBuilder.withFloors(f0);
        houseBuilder.withFloors(f1);
        houseBuilder.withFloors(f2);

        houseBuilder.withRooms(r0_1);
        houseBuilder.withRooms(r0_2);
        houseBuilder.withRooms(r0_3);
        houseBuilder.withRooms(r0_4);

        houseBuilder.withRooms(r1_1);
        houseBuilder.withRooms(r1_2);
        houseBuilder.withRooms(r1_3);

        houseBuilder.withRooms(r2_1);
        houseBuilder.withRooms(r2_2);
        houseBuilder.withRooms(r2_3);
        houseBuilder.withRooms(r2_4);
        houseBuilder.withRooms(r2_5);
        houseBuilder.withRooms(r2_6);

        houseBuilder.withWindows(w1);
        houseBuilder.withWindows(w2);
        houseBuilder.withWindows(w3);
        houseBuilder.withWindows(w4);
        houseBuilder.withWindows(w5);
        houseBuilder.withWindows(w6);
        houseBuilder.withWindows(w7);
        houseBuilder.withWindows(w8);
        houseBuilder.withWindows(w9);
        houseBuilder.withWindows(w10);
        houseBuilder.withWindows(w11);
        houseBuilder.withWindows(w12);
        houseBuilder.withWindows(w13);
        houseBuilder.withWindows(w14);
        houseBuilder.withWindows(w15);

        house = houseBuilder.build();

        //creating appliances in house
        Factory factory = new Factory();
        factory.createLamp(r0_1);
        factory.createThermostat(r0_1);
        factory.createVacuumCleaner(r0_1);

        factory.createDishwasher(r0_2);
        factory.createFridge(r0_2);
        factory.createOven(r0_2);
        factory.createLamp(r0_2);
        factory.createThermostat(r0_2);

        factory.createLamp(r0_3);
        factory.createThermostat(r0_3);

        factory.createLamp(r0_4);
        factory.createWashingMachine(r0_4);
        factory.createThermostat(r0_4);

        factory.createLamp(r1_1);
        factory.createWashingMachine(r1_1);
        factory.createThermostat(r1_1);

        factory.createLamp(r1_2);
        factory.createLamp(r1_2);
        factory.createTelevision(r1_2);
        factory.createThermostat(r1_2);

        factory.createLamp(r1_3);
        factory.createTelevision(r1_3);
        factory.createThermostat(r1_3);

        factory.createLamp(r2_1);
        factory.createWashingMachine(r2_1);
        factory.createThermostat(r2_1);
        factory.createVacuumCleaner(r2_1);

        factory.createLamp(r2_2);
        factory.createWashingMachine(r2_2);
        factory.createThermostat(r2_2);

        factory.createLamp(r2_3);
        factory.createTelevision(r2_3);
        factory.createThermostat(r2_3);

        factory.createLamp(r2_4);
        factory.createTelevision(r2_4);
        factory.createThermostat(r2_4);

        factory.createLamp(r2_5);
        factory.createTelevision(r2_5);
        factory.createThermostat(r2_5);

        //creating sport items
        Ski ski1 = new Ski(r0_1);
        Ski ski2 = new Ski(r0_1);
        Ski ski3 = new Ski(r0_1);
        Ski ski4 = new Ski(r0_1);
        Ski ski5 = new Ski(r0_1);

        Bike bike1 = new Bike(r0_1);
        Bike bike2 = new Bike(r0_1);
        Bike bike3 =new Bike(r0_1);
        Bike bike4 =new Bike(r0_1);
        Bike bike5 =new Bike(r0_1);

        // creating people
        Person father = new Person("Květoslav", "Baron",52, PersonRole.Father);
        Person mother = new Person("Petúnie", "Baronová",49, PersonRole.Mother);
        Person child1 = new Person("Hermína", "Baronová",17, PersonRole.Child);
        Person child2 = new Person("Ambrož", "Baron",17, PersonRole.Child);
        Person child3 = new Person("Griselda", "Baronová",13, PersonRole.Child);

        father.setRoom(r0_2);
        mother.setRoom(r0_4);
        child1.setRoom(r1_1);
        child2.setRoom(r1_1);
        child3.setRoom(r1_3);

        //creating pets
        Pet dog1 = new Pet("Barbínka", "Dog");
        dog1.setRoom(r2_3);
        Pet dog2 = new Pet("Mysicka", "Dog");
        dog2.setRoom(r1_2);
    }

    public House getHouse(){
        return house;
    }
}
