package cz.cvut.fel.omo.smarthome.building;


import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.sensors.Sensor;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.creatures.Pet;
import cz.cvut.fel.omo.smarthome.reports.Visitor;

import java.util.*;

public class House {
    private List<Floor> floors;
    private int houseLevel = 0;
    private String name;
    private List<Person> people;
    private List<Pet> pets;
    private List<Appliance> appliances;
    private List<SportItem> sportItems;
    private Map<Appliance, Sensor> sensors;

    public House(String name) {
        floors = new ArrayList<>();
        appliances = new ArrayList<>();
        this.name = name;
        people = new ArrayList<>();
        pets = new ArrayList<>();
        sportItems = new ArrayList<>();

        sensors = new HashMap<>();

    }

    public int getHouseLevel() {
        return houseLevel;
    }

    public void addHouseLevel(){
        houseLevel++;
    }

    public void addFloor(Floor toAdd){
        if (!floors.contains(toAdd)){
            floors.add(toAdd);
        }
    }

    public void addPerson(Person toAdd){
        if (!people.contains(toAdd)){
            people.add(toAdd);
        }
    }

    public void addPet(Pet toAdd){
        if (!pets.contains(toAdd)){
            pets.add(toAdd);
        }
    }

    public void addSportItem(SportItem toAdd){
        if (!sportItems.contains(toAdd)){
            sportItems.add(toAdd);
        }
    }

    public void addAppliance(Appliance toAdd){
        if (!appliances.contains(toAdd)){
            appliances.add(toAdd);
        }
    }

    public void addSensor(Appliance key, Sensor value){
        sensors.put(key, value);
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    public void accept(Visitor visitor){
        visitor.visitHouse(this);
    }

    public String getName() {
        return name;
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public List<SportItem> getSportItems() {
        return sportItems;
    }

    public Map<Appliance, Sensor> getSensors() {
        return sensors;
    }
}
