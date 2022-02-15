package cz.cvut.fel.omo.smarthome.reports;


import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.building.*;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.events.Event;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.creatures.Pet;


public interface Visitor {

    /**
     * Visits evenet handler.
     * @param eventHandler
     */
    default void visitEventHandler(EventHandler eventHandler) {

    }

    /**
     * Visists event.
     * @param event
     */
    default void visitEvent(Event event) {

    }

    /**
     * Visits person.
     * @param person
     */
    default void visitPerson(Person person){

    }

    /**
     * Visits pet.
     * @param pet
     */
    default void visitPet(Pet pet){

    }

    /**
     * Visits appliance.
     * @param appliance
     */
    default void visitAppliance(Appliance appliance){

    }

    /**
     * Visits window.
     * @param window
     */
    default void visitWindow(Window window) {

    }

    /**
     * Visits room.
     * @param room
     */
    default void visitRoom(Room room) {

    }

    /**
     * Visits floor.
     * @param floor
     */
    default void visitFloor(Floor floor) {

    }

    /**
     * Visits house.
     * @param house
     */
    default void visitHouse(House house) {

    }

    /**
     * Visists sport item.
     * @param sportItem
     */
    default void visitSportItem(SportItem sportItem){

    }
}
