package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.ApplianceBase;
import cz.cvut.fel.omo.smarthome.appliances.ApplianceType;
import cz.cvut.fel.omo.smarthome.appliances.documents.ApplianceDoc;
import cz.cvut.fel.omo.smarthome.appliances.state.ApplianceState;
import cz.cvut.fel.omo.smarthome.appliances.state.ApplianceStateType;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.Utils;

public abstract class SensorDecorator<T extends ApplianceBase> implements Sensor,Observer, Appliance{
    protected T appliance;
    protected final EventHandler eventHandler = EventHandler.getInstance();

    public SensorDecorator(T appliance) {
        this.appliance = appliance;
        eventHandler.attach(this);
        appliance.getRoom().getFloor().getHouse().addSensor(appliance, this);
    }


    @Override
    public T getAppliance() {
        return appliance;
    }

    /**
     * Returns true if appliance will broke down
     * @return boolean
     */
    protected boolean willBreak(){
        return Utils.randomInt(100) > 95;
    }

    /**
     * returns appliance type
     * @return appliance type
     */
    @Override
    public ApplianceType getApplianceType() {
        return appliance.getApplianceType();
    }

    /**
     * Returns value of consumption
     * @return
     */
    @Override
    public int getConsumption() {
        return appliance.getConsumption();
    }

    /**
     * Returns value of idle consumption
     * @return
     */
    @Override
    public int getIdleConsumption() {
        return appliance.getIdleConsumption();
    }

    /**
     * Returns total consumption of appliance
     * @return
     */
    @Override
    public int getTotalConsumption() {
        return appliance.getTotalConsumption();
    }

    /**
     * Sets total consumption
     * @param value new consumption value
     */
    @Override
    public void setTotalConsumption(int value) {
        appliance.setTotalConsumption(value);
    }

    /**
     * returns room where appliance is
     * @return
     */
    @Override
    public Room getRoom() {
        return appliance.getRoom();
    }

    /**
     * Sets room where appliance is
     * @param room new room of appliance
     */
    @Override
    public void setRoom(Room room) {
        appliance.setRoom(room);
    }

    /**
     * returns appliance type
     * @return
     */
    @Override
    public ApplianceStateType getApplianceStateType() {
        return appliance.getApplianceStateType();
    }

    /**
     * returns appliance state
     * @return
     */
    @Override
    public ApplianceState getApplianceState() {
        return appliance.getApplianceState();
    }

    /**
     * Sets appliance type of appliance
     * @param type new appliance type
     */
    @Override
    public void setApplianceState(ApplianceState type) {
        appliance.setApplianceState(type);
    }

    /**
     * repairs appliance if needed
     * @param person Person who repairs appliance
     */
    @Override
    public void repair(Person person) {
        appliance.repair(person);
    }

    /**
     * turns appliance on
     */
    @Override
    public void turnOn() {
        appliance.turnOn();
    }

    /**
     * turns appliance off
     */
    @Override
    public void turnOff() {
        appliance.turnOff();
    }

    /**
     * returns durability of appliance
     * @return
     */
    @Override
    public int getDurability() {
        return appliance.getDurability();
    }

    /**
     * returns documentation for appliance
     * @return
     */
    @Override
    public ApplianceDoc getDocumentation() {
        return appliance.getDocumentation();
    }

    /**
     * returns boolean if appliance is available
     * @return
     */
    @Override
    public boolean isAvailable() {
        return appliance.isAvailable();
    }

    /**
     * returns id of appliance
     * @return
     */
    @Override
    public int getId() {
        return appliance.getId();
    }

    /**
     * Accepts report for visitor pattern
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        appliance.accept(visitor);
    }

}
