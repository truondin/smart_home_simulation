package cz.cvut.fel.omo.smarthome.building;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.creatures.Pet;
import cz.cvut.fel.omo.smarthome.reports.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private Floor floor;
    private RoomType roomType;
    private List<Window> windows;

    private List<Appliance> appliances;
    private List<SportItem> sportItems;
    private List<Person> peopleInRoom;
    private List<Pet> petsInRoom;

    public Room(Floor floor) {
        this.floor = floor;
        roomType = RoomType.REGULAR_ROOM;

        windows = new ArrayList<>();
        appliances = new ArrayList<>();
        peopleInRoom = new ArrayList<>();
        petsInRoom = new ArrayList<>();
        sportItems = new ArrayList<>();
    }

    public Room(Floor floor, RoomType roomType) {
        this.floor = floor;
        this.roomType = roomType;

        windows = new ArrayList<>();
        appliances = new ArrayList<>();
        peopleInRoom = new ArrayList<>();
        petsInRoom = new ArrayList<>();
        sportItems = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) {

        if (!appliances.contains(appliance)) appliances.add(appliance);
    }

    public void removeAppliance(Appliance appliance){
        appliances.remove(appliance);
    }

    public void addSportItem(SportItem sportItem) {

        if (!sportItems.contains(sportItem)) sportItems.add(sportItem);
    }

    public void removeSportItems(SportItem sportItem){
        sportItems.remove(sportItem);
    }


    public void addWindow(Window window){

        if (!windows.contains(window)){
            windows.add(window);
        }
    }

    public void addPerson(Person person){
        if (!peopleInRoom.contains(person)){
            peopleInRoom.add(person);
        }
    }

    public void removePerson(Person person){
        peopleInRoom.remove(person);
    }

    public void addPet(Pet pet){
        if (!petsInRoom.contains(pet)){
            petsInRoom.add(pet);
        }
    }

    public void removePet(Pet pet){
        petsInRoom.remove(pet);
    }

    public List<Window> getWindowsList() {
        return windows;
    }

    public void setWindowsList(List<Window> windows) {
        this.windows = windows;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public List<Person> getPeopleInRoom() {
        return peopleInRoom;
    }

    public void setPeopleInRoom(List<Person> peopleInRoom) {
        this.peopleInRoom = peopleInRoom;
    }

    public List<Pet> getPetsInRoom() {
        return petsInRoom;
    }

    public void setPetsInRoom(List<Pet> petsInRoom) {
        this.petsInRoom = petsInRoom;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void accept(Visitor visitor){
        visitor.visitRoom(this);
    }
}
