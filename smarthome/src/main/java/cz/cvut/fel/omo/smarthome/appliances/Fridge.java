package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Fridge extends ApplianceBase {

    private int capacity;
    private final int MAX_CAPACITY = 100;

    private static final Logger log = Logger.getLogger(Fridge.class.getName());

    public Fridge(Room room) {
        super(room);
        this.type = ApplianceType.FRIDGE;
        this.consumption = ApplianceType.FRIDGE.consumption;
        this.idleConsumption = ApplianceType.FRIDGE.idleConsumption;

        this.capacity = 0;
        room.addAppliance(this);
        this.turnOn();
    }

    public void addItems(int amount){
        capacity += amount;
        log.log(Level.INFO,"Items added: " + amount + " current capacity " + capacity);
    }

    public void removeItems(int amount){
        capacity -= amount;
        log.log(Level.INFO, "Items removed: " + amount + " current capacity " + capacity);
    }

    public boolean isFull(){
        return capacity == MAX_CAPACITY;
    }

    public boolean isEmpty(){
        return capacity <= 0;
    }

    @Override
    public void update(TurnOnAppliance event) {
        if (event.getAppliance().equals(this)){
            startCooling();
        }
    }


    @Override
    public void update(TurnIdleAppliance event) {
        if (event.getAppliance().equals(this)){
            turnOn();
        }
    }


    @Override
    public void turnOn() {
        state.changeToIdle();
        log.log(Level.INFO,"Fridge is on, but does not cool.");
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO,"Fridge turned off");
    }

    private void startCooling(){
        state.changeToOn();
        log.log(Level.INFO,"Fridge started cooling.");

    }
}
