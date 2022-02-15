package cz.cvut.fel.omo.smarthome.building.builder;

import cz.cvut.fel.omo.smarthome.building.Floor;
import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.building.Window;

import java.util.ArrayList;
import java.util.List;

public class HouseBuilder {

    private List<Floor> floors;
    private List<Room> rooms;
    private List<Window> windows;
    private String name;

    public HouseBuilder(String name) {
        floors = new ArrayList<>();
        rooms = new ArrayList<>();
        windows = new ArrayList<>();
        this.name = name;
    }

    public HouseBuilder withFloors(List<Floor> floors) {
        this.floors = floors;
        return this;
    }

    public HouseBuilder withFloors(Floor floor) {
        if (!floors.contains(floor)){
            floors.add(floor);
        }

        return this;
    }

    public HouseBuilder withRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public HouseBuilder withRooms(Room room) {
        if (!rooms.contains(room)){
            rooms.add(room);
        }
        return this;
    }

    public HouseBuilder withWindows(List<Window> windows) {
        this.windows = windows;
        return this;
    }

    public HouseBuilder withWindows(Window window) {
        if (!windows.contains(window)){
            windows.add(window);
        }
        return this;
    }

    public House build() {
        if (this.rooms == null || this.floors == null || this.windows ==null) {
            throw new IllegalArgumentException("Some required arguments are not provided: rooms, floors, windows");
        }

        House house = new House(name);

        buildWindows();
        buildRooms();
        buildFloors(house);
        house.setFloors(floors);

        return house;
    }

    private void buildFloors(House house){
        for (Floor f: floors){
            f.setHouse(house);
        }
    }

    private void buildRooms(){
        for (Room r : rooms){
            Floor roomFloor = r.getFloor();
            for (Floor f : floors){
                if (f == roomFloor){
                    f.addRoom(r);
                }
            }
        }
    }

    private void buildWindows(){
        for (Window w : windows){
            Room windowRoom = w.getRoom();
            for (Room r : rooms){
                if (r == windowRoom){
                    r.addWindow(w);
                }
            }
        }

    }

}
