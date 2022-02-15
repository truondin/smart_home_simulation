package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.appliances.documents.ApplianceDoc;
import cz.cvut.fel.omo.smarthome.appliances.documents.DocumentLazyLoad;
import cz.cvut.fel.omo.smarthome.appliances.state.ApplianceState;
import cz.cvut.fel.omo.smarthome.appliances.state.ApplianceStateType;
import cz.cvut.fel.omo.smarthome.appliances.state.TurnedOffState;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.Constant;
import cz.cvut.fel.omo.smarthome.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ApplianceBase implements Appliance, Observer {
    protected int id;

    protected ApplianceType type;
    protected ApplianceState state;
    protected Room room;
    protected int consumption;
    protected int idleConsumption;
    protected int totalConsumption = 0;
    protected int durability = Constant.DEFAULT_DURABILITY;

    protected final EventHandler eventHandler;
    protected DocumentLazyLoad documentation = new DocumentLazyLoad();

    private static final Logger log = Logger.getLogger(ApplianceBase.class.getName());

    public ApplianceBase(Room room) {
        this.state = new TurnedOffState(this);
        this.room = room;
        this.id = Utils.randomInt(10000);
        room.addAppliance(this);
        room.getFloor().getHouse().addAppliance(this);
        eventHandler = EventHandler.getInstance();
        eventHandler.attach(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isAvailable() {
        return !state.isOn();
    }

    @Override
    public ApplianceDoc getDocumentation() {
        return documentation.getDoc(type);
    }


    public void lowerDurability() {
        durability--;
        if (durability < 0){
            state.changeToBroken();
            log.log(Level.INFO, "Appliance (id:" + id + " broke down");
        }
    }

    @Override
    public void update(TurnOffAppliance event) {
        if (this.equals(event.getSource())){
            state.changeToOff();
        }
    }

    @Override
    public void update(TurnOnAppliance event) {
        if (this.equals(event.getSource())){
            state.changeToOn();
        }
    }

    @Override
    public void update(TurnBrokenAppliance event) {
        if (this.equals(event.getSource())) {
            state.changeToBroken();
        }
    }

    @Override
    public void update(TurnIdleAppliance event) {
        if (this.equals(event.getSource())){
            state.changeToIdle();
        }
    }

    @Override
    public int getIdleConsumption() {
        return idleConsumption;
    }

    @Override
    public ApplianceType getApplianceType(){
        return type;
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int getConsumption() {
        return consumption;
    }

    @Override
    public int getTotalConsumption() {
        return totalConsumption;
    }

    @Override
    public void setTotalConsumption(int value) {
        totalConsumption = value;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public ApplianceStateType getApplianceStateType() {
        return state.getStateType();
    }

    @Override
    public void repair(Person person) {
        if (this.state.isBroken() && Utils.randomInt(100) > 10){
            this.state.changeToOff();
            durability = Constant.DEFAULT_DURABILITY;
            eventHandler.addEvent(new ApplianceRepaired(person, this, eventHandler.getTime(), this));
        }else eventHandler.addEvent(new BrokenAppliance(this, eventHandler.getTime(), this));
    }

    @Override
    public void setApplianceState(ApplianceState type) {
        this.state = type;
    }

    @Override
    public ApplianceState getApplianceState() {
        return state;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitAppliance(this);
    }

    @Override
    public String getName() {
        return type + " id: " + id;
    }
}
