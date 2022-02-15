package cz.cvut.fel.omo.smarthome.creatures;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.appliances.ApplianceType;
import cz.cvut.fel.omo.smarthome.appliances.Fridge;
import cz.cvut.fel.omo.smarthome.appliances.Television;
import cz.cvut.fel.omo.smarthome.appliances.sensors.TelevisionSensor;
import cz.cvut.fel.omo.smarthome.building.Blind;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.Observer;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.items.SportItemType;
import cz.cvut.fel.omo.smarthome.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.Constant;
import cz.cvut.fel.omo.smarthome.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Person extends LivingBeing{
    private String firstname;
    private String surname;
    private int age;
    private boolean isDoingSport;
    private PersonRole role;

    private static final Logger log = Logger.getLogger(Person.class.getName());

    public Person(String firstname, String surname, int age, PersonRole role) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.role = role;
        isDoingSport = false;

        eventHandler.attach(this);
    }

    private void doRandomThing(){

        int i = Utils.randomInt(9);

        switch (i) {
            case 0 -> useDishwasher();
            case 1 -> useOven();
            case 2 -> {
                Television tv = watchesTv();
                if (tv != null) {
                    if (Utils.randomBoolean()){
                        stopWatchingTv(tv);
                    }else{
                        changeTvChannel(tv);
                    }
                } else useTelevision();
            }
            case 3 -> useVacuumCleaner();
            case 4 -> useWashingMachine();
            case 5 -> {
                useBike();
                isDoingSport = true;
            }
            case 6 ->{
                useSki();
                isDoingSport = true;
            }
            case 7 -> useFridge();
            case 8 -> rollUpBlinds();
        }

    }

    private void moveToRoom(Room room){
        if (getRoom() != null) getRoom().removePerson(this);
        this.room = room;
        room.addPerson(this);

        log.log(Level.INFO, "Person " + getName() + " moved to room " + room.getRoomType());
        eventHandler.addEvent(new PersonMoveEvent(room,this, eventHandler.getTime()));
    }

    private void leaveHouse(){
        if (getRoom() != null) getRoom().removePerson(this);
        this.room = null;

        log.log(Level.INFO, "Person " + getName() + "left the house");
        eventHandler.addEvent(new PersonMoveEvent(null,this, eventHandler.getTime()));
    }

    private void rollUpBlinds(){
        List<Room> rooms = new ArrayList<>();
        getHouse().getFloors().forEach(floor -> rooms.addAll(floor.getRooms()));

        if (!rooms.isEmpty()){
            Room chosen = rooms.get(Utils.randomInt(rooms.size()));

            chosen.getWindowsList().forEach(window -> {
                Blind blind = window.getBlind();
                if (blind.isRolledUp()){
                    blind.rollDown();
                }else blind.rollUp();
            });

            log.log(Level.INFO, "Person " + getName() + " rolled up blinds in room " + chosen.getRoomType());
            eventHandler.addEvent(new BlindsRoll(this,  eventHandler.getTime(), chosen, this));
        }
    }

    private void takeSomeFood(Fridge fridge){
        fridge.removeItems(Utils.randomInt(5));
    }

    private void addFood(Fridge fridge){
        fridge.addItems(Utils.randomInt(5));
    }

    private void useFridge(){
        List<Appliance> fridges = getHouse().getAppliances().stream()
                .filter(appliance -> appliance.getApplianceType().equals(ApplianceType.FRIDGE))
                .collect(Collectors.toList());

        if (!fridges.isEmpty()){
            Fridge fridge = (Fridge) fridges.get(Utils.randomInt(fridges.size()));

            moveToRoom(fridge.getRoom());
            if (Utils.randomBoolean()){
                if (!fridge.isEmpty()){
                    takeSomeFood(fridge);

                    log.log(Level.INFO, "Person " + getName() + " took out something from fridge (id:" + fridge.getId() + ")");
                    eventHandler.addEvent(new RemoveItemFromFridge(this, fridge, eventHandler.getTime()));
                }
            }else {
                if (!fridge.isFull()){
                    addFood(fridge);

                    log.log(Level.INFO, "Person " + getName() + " added something to fridge (id:" + fridge.getId() + ")");
                    eventHandler.addEvent(new AddItemToFridge(this, fridge, eventHandler.getTime()));
                }
            }
        }
    }

    private void useDishwasher(){
        List<Appliance> dishwashers =  getHouse().getAppliances().stream()
                .filter(appliance -> appliance.getApplianceType().equals(ApplianceType.DISHWASHER) && !appliance.getApplianceState().isOn())
                .collect(Collectors.toList());


        if (!dishwashers.isEmpty()){
            Appliance chosen = dishwashers.get(Utils.randomInt(dishwashers.size()));

            moveToRoom(chosen.getRoom());
            chosen.turnOn();

            log.log(Level.INFO, "Person " + getName() + " used DISHWASHER (id:" + chosen.getId() + ")");
            eventHandler.addEvent(new StartUsingAppliance(this,(Observer) chosen, chosen, this, eventHandler.getTime()));
        }
    }

    private void useOven(){
        List<Appliance> ovens =  getHouse().getAppliances().stream()
                .filter(appliance -> appliance.getApplianceType().equals(ApplianceType.OVEN) && !appliance.getApplianceState().isOn())
                .collect(Collectors.toList());

        if (!ovens.isEmpty()) {
            Appliance chosen = ovens.get(Utils.randomInt(ovens.size()));
            moveToRoom(chosen.getRoom());
            chosen.turnOn();

            log.log(Level.INFO, "Person " + getName() + " used OVEN (id:" + chosen.getId() + ")");
            eventHandler.addEvent(new StartUsingAppliance(this,(Observer) chosen, chosen, this, eventHandler.getTime()));

        }
    }

    private void useTelevision(){
        List<Appliance> televisions =  getHouse().getAppliances().stream()
                .filter(appliance -> appliance.getApplianceType().equals(ApplianceType.TV) && !appliance.getApplianceState().isOn())
                .collect(Collectors.toList());

        if (!televisions.isEmpty()){
            Appliance chosen = televisions.get(Utils.randomInt(televisions.size()));

            moveToRoom(chosen.getRoom());
            chosen.turnOn();

            Television tv = (Television) chosen;
            TelevisionSensor sensor = (TelevisionSensor) getHouse().getSensors().get(tv);

            sensor.addWatcher(this);

            log.log(Level.INFO, "Person " + getName() + " used TELEVISION (id:" + chosen.getId() + ")");
            eventHandler.addEvent(new StartUsingAppliance(this,(Observer) chosen, chosen, this, eventHandler.getTime()));
        }
    }

    private void stopWatchingTv(Television tv){
        TelevisionSensor sensor = (TelevisionSensor) getHouse().getSensors().get(tv);

        sensor.removeWatcher(this);

        log.log(Level.INFO, "Person " + getName() + " stopped watching TELEVISION (id:" + tv.getId() + ")");
        eventHandler.addEvent(new StopUsingAppliance(this, tv, tv, this, eventHandler.getTime()));
    }

    private void changeTvChannel(Television tv){
        tv.changeChannel(Constant.DEFAULT_CHANNELS.get(Utils.randomInt(Constant.DEFAULT_CHANNELS.size())));
        log.log(Level.INFO, "Person " + getName() + " changed TV channel");
    }

    private Television watchesTv(){
        List<Appliance> televisions =  getHouse().getAppliances().stream()
                .filter(appliance -> appliance.getApplianceType().equals(ApplianceType.TV) && !appliance.getApplianceState().isOn())
                .collect(Collectors.toList());

        for(Appliance tv : televisions){
            Television curr = (Television) tv;
            TelevisionSensor sensor = (TelevisionSensor) getHouse().getSensors().get(tv);

            if (sensor.getWatchers().contains(this)) return curr;
        }
        return null;
    }

    private void useVacuumCleaner(){
        List<Appliance> vacuums =  getHouse().getAppliances().stream()
                .filter(appliance -> appliance.getApplianceType().equals(ApplianceType.VACUUM_CLEANER) && !appliance.getApplianceState().isOn())
                .collect(Collectors.toList());

        if (!vacuums.isEmpty()){
            Appliance chosen = vacuums.get(Utils.randomInt(vacuums.size()));

            moveToRoom(chosen.getRoom());
            chosen.turnOn();

            log.log(Level.INFO, "Person " + getName() + " used VACUUM CLEANER (id:" + chosen.getId() + ")");
            eventHandler.addEvent(new StartUsingAppliance(this,(Observer) chosen, chosen, this, eventHandler.getTime()));
        }
    }

    private void useWashingMachine(){
        List<Appliance> washers =  getHouse().getAppliances().stream()
                .filter(appliance -> appliance.getApplianceType().equals(ApplianceType.WASHING_MACHINE) && !appliance.getApplianceState().isOn())
                .collect(Collectors.toList());

        if (!washers.isEmpty()){
            Appliance chosen = washers.get(Utils.randomInt(washers.size()));

            moveToRoom(chosen.getRoom());
            chosen.turnOn();

            log.log(Level.INFO, "Person " + getName() + " used WASHING MACHINE (id:" + chosen.getId() + ")");
            eventHandler.addEvent(new StartUsingAppliance(this,(Observer) chosen, chosen, this, eventHandler.getTime()));

        }
    }

    private void useBike(){
        List<SportItem> bikes = getHouse().getSportItems().stream()
                .filter(ski -> ski.getType().equals(SportItemType.Bike) && ski.isAvailable())
                .collect(Collectors.toList());

        if (!bikes.isEmpty()){
            SportItem chosen = bikes.get(Utils.randomInt(bikes.size()));

            moveToRoom(chosen.getRoom());
            leaveHouse();

            log.log(Level.INFO, "Person " + getName() + " used BIKE (id:" + chosen.getId() + ")");
            eventHandler.addEvent(new StartUsingSportItem(this,chosen, chosen, this, eventHandler.getTime()));
        }
    }

    private void useSki(){
        List<SportItem> skies = getHouse().getSportItems().stream()
                .filter(ski -> ski.getType().equals(SportItemType.Ski) && ski.isAvailable())
                .collect(Collectors.toList());

        if (!skies.isEmpty()){
            SportItem chosen = skies.get(Utils.randomInt(skies.size()));

            moveToRoom(chosen.getRoom());
            leaveHouse();

            log.log(Level.INFO, "Person " + getName() + " used SKI (id:" + chosen.getId() + ")");
            eventHandler.addEvent(new StartUsingSportItem(this,chosen, chosen, this, eventHandler.getTime()));
        }

    }

    public void accept(Visitor visitor){
        visitor.visitPerson(this);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    public void setRoom(Room room) {
        this.room = room;
        room.getFloor().getHouse().addPerson(this);
    }

    @Override
    public String getName() {
        return firstname + " " + surname;
    }

    @Override
    public void update(NextHourEvent event) {
        if (!isDoingSport){
            doRandomThing();
        }

    }

    @Override
    public void update(StopUsingSportItem event) {
        if (event != null && this.equals(event.getUser())){
            SportItem item = event.getItem();

            moveToRoom(item.getRoom());
            isDoingSport = false;
            log.log(Level.INFO, "Person "+ getName() + "stopped using sport item");
        }
    }

    @Override
    public void update(BrokenAppliance event) {
        if (this.role.equals(PersonRole.Father) && !isDoingSport){
            Appliance appliance = event.getAppliance();
            appliance.getDocumentation();
            appliance.repair(this);
            event.addTarget(this);

            log.log(Level.INFO, "Person " + getName() + " repaired appliance");
        }
    }

    @Override
    public void update(TaskFinished event) {

        Appliance appliance = (Appliance) event.getSource();
        if (this.role.equals(PersonRole.Mother) && !isDoingSport) {
            switch (appliance.getApplianceType()) {
                case WASHING_MACHINE, OVEN, DISHWASHER -> {
                    eventHandler.addEvent(new TurnOffAppliance(this, event.getSource(), eventHandler.getTime(), appliance));
                    eventHandler.addEvent(new StopUsingAppliance(this, appliance, this, eventHandler.getTime()));
                    event.addTarget(this);

                    log.log(Level.INFO, "Person " + getName() + " finished task");
                }
            }
        }
    }


}
