package cz.cvut.fel.omo.smarthome.eventhandler;

import cz.cvut.fel.omo.smarthome.reports.Visitor;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EventHandler {
    private final List<Observer> observers;
    private final LinkedList<Event> prevEvents;

    private final LinkedList<NextHourEvent> nextHourEvents;

    private final LinkedList<BrokenAppliance> brokenApplianceEvents;
    private final LinkedList<PersonMoveEvent> personMoveEvents;
    private final LinkedList<PetMoveEvent> petMoveEvents;
    private final LinkedList<StartUsingAppliance> startUsingAppliancesEvents;
    private final LinkedList<StartUsingSportItem> startUsingSportItemsEvents;
    private final LinkedList<StopUsingAppliance> stopUsingAppliancesEvents;
    private final LinkedList<StopUsingSportItem> stopUsingSportItemsEvents;
    private final LinkedList<TaskFinished> taskFinishedEvents;
    private final LinkedList<TurnBrokenAppliance> turnBrokenAppliancesEvents;
    private final LinkedList<TurnIdleAppliance> turnIdleAppliancesEvents;
    private final LinkedList<TurnOffAppliance> turnOffAppliancesEvents;
    private final LinkedList<TurnOnAppliance> turnOnAppliancesEvents;
    private final LinkedList<ApplianceRepaired> applianceRepairedEvents;
    private final LinkedList<AddItemToFridge> addItemToFridgeEvents;
    private final LinkedList<RemoveItemFromFridge> removeItemFromFridgeEvents;
    private final LinkedList<BlindsRoll> blindsRollEvents;

    private static EventHandler instance;
    private int time;

    private EventHandler() {
        observers = new ArrayList<>();
        prevEvents = new LinkedList<>();
        brokenApplianceEvents = new LinkedList<>();
        nextHourEvents = new LinkedList<>();
        personMoveEvents = new LinkedList<>();
        petMoveEvents = new LinkedList<>();
        startUsingAppliancesEvents = new LinkedList<>();
        startUsingSportItemsEvents = new LinkedList<>();
        stopUsingAppliancesEvents = new LinkedList<>();
        stopUsingSportItemsEvents = new LinkedList<>();
        taskFinishedEvents = new LinkedList<>();
        turnBrokenAppliancesEvents = new LinkedList<>();
        turnIdleAppliancesEvents = new LinkedList<>();
        turnOffAppliancesEvents = new LinkedList<>();
        turnOnAppliancesEvents = new LinkedList<>();
        applianceRepairedEvents = new LinkedList<>();
        addItemToFridgeEvents = new LinkedList<>();
        removeItemFromFridgeEvents = new LinkedList<>();
        blindsRollEvents = new LinkedList<>();

        time = 0;
    }

    /**
     * Method returns instance.
     * @return instance
     */
    public static EventHandler getInstance(){
        if (instance == null) {
            instance = new EventHandler();
        }

        return instance;
    }

    /**
     * Method adds blinds roll.
     * @param e
     */
    public void addEvent(BlindsRoll e){
        blindsRollEvents.add(e);
    }

    /**
     * Method adds item to fridge.
     * @param e
     */
    public void addEvent(AddItemToFridge e){
        addItemToFridgeEvents.add(e);
    }

    /**
     * Method removes item from fridge.
     * @param e
     */
    public void addEvent(RemoveItemFromFridge e){
        removeItemFromFridgeEvents.add(e);
    }

    /**
     * Method repairs an appliance.
     * @param e
     */
    public void addEvent(ApplianceRepaired e){
        applianceRepairedEvents.add(e);
    }

    /**
     * Method breaks an appliance.
     * @param e
     */
    public void addEvent(BrokenAppliance e){
        brokenApplianceEvents.add(e);
    }

    /**
     * Method moves to next hour.
     * @param e
     */
    public void addEvent(NextHourEvent e){
        nextHourEvents.add(e);
    }

    /**
     * Method moves person.
     * @param e
     */
    public void addEvent(PersonMoveEvent e){
        personMoveEvents.add(e);
    }

    /**
     * Method moves pet.
     * @param e
     */
    public void addEvent(PetMoveEvent e){
        petMoveEvents.add(e);
    }

    /**
     * Method starts using appliance.
     * @param e
     */
    public void addEvent(StartUsingAppliance e){
        startUsingAppliancesEvents.add(e);
    }

    /**
     * Method starts using sport item.
     * @param e
     */
    public void addEvent(StartUsingSportItem e){
        startUsingSportItemsEvents.add(e);
    }

    /**
     * Method stops using appliance.
     * @param e
     */
    public void addEvent(StopUsingAppliance e){
        stopUsingAppliancesEvents.add(e);
    }

    /**
     * Method stops using sport item.
     * @param e
     */
    public void addEvent(StopUsingSportItem e){
        stopUsingSportItemsEvents.add(e);
    }

    /**
     * Method finishes task.
     * @param e
     */
    public void addEvent(TaskFinished e){
        taskFinishedEvents.add(e);
    }

    /**
     * Method turns on appliance.
     * @param e
     */
    public void addEvent(TurnOnAppliance e){
        turnOnAppliancesEvents.add(e);
    }

    /**
     * Method turns off appliance.
     * @param e
     */
    public void addEvent(TurnOffAppliance e){
        turnOffAppliancesEvents.add(e);
    }

    /**
     * Method turns appliance to idle mode.
     * @param e
     */
    public void addEvent(TurnIdleAppliance e){
        turnIdleAppliancesEvents.add(e);
    }

    /**
     * Method breaks appliance.
     * @param e
     */
    public void addEvent(TurnBrokenAppliance e){
        turnBrokenAppliancesEvents.add(e);
    }


    /**
     * Method attaches observer.
     * @param toAttach
     */
    public void attach(Observer toAttach){
        if (!observers.contains(toAttach)) observers.add(toAttach);
    }

    /**
     * Method detaches observer.
     * @param toDetach
     */
    public void detach(Observer toDetach){
        observers.remove(toDetach);
    }

    /**
     * Method notifies observers.
     * @param time
     */
    public void notifyObservers(int time){
        this.time = time;

        if (!nextHourEvents.isEmpty()) notifyAllObservers(nextHourEvents.peek());
        if (!brokenApplianceEvents.isEmpty()) notifyAllObservers(brokenApplianceEvents.peek());
        if (!personMoveEvents.isEmpty()) notifyAllObservers(personMoveEvents.peek());
        if (!petMoveEvents.isEmpty()) notifyAllObservers(petMoveEvents.peek());
        if (!startUsingAppliancesEvents.isEmpty()) notifyAllObservers(startUsingAppliancesEvents.peek());
        if (!startUsingSportItemsEvents.isEmpty()) notifyAllObservers(startUsingSportItemsEvents.peek());
        if (!stopUsingAppliancesEvents.isEmpty()) notifyAllObservers(stopUsingAppliancesEvents.peek());
        if (!stopUsingSportItemsEvents.isEmpty()) notifyAllObservers(stopUsingSportItemsEvents.peek());
        if (!taskFinishedEvents.isEmpty()) notifyAllObservers(taskFinishedEvents.peek());
        if (!turnBrokenAppliancesEvents.isEmpty()) notifyAllObservers(turnBrokenAppliancesEvents.peek());
        if (!turnIdleAppliancesEvents.isEmpty()) notifyAllObservers(turnIdleAppliancesEvents.peek());
        if (!turnOnAppliancesEvents.isEmpty()) notifyAllObservers(turnOnAppliancesEvents.peek());
        if (!turnOffAppliancesEvents.isEmpty()) notifyAllObservers(turnOffAppliancesEvents.peek());
        if (!applianceRepairedEvents.isEmpty()) notifyAllObservers(applianceRepairedEvents.peek());
        if (!addItemToFridgeEvents.isEmpty()) notifyAllObservers(addItemToFridgeEvents.peek());
        if (!removeItemFromFridgeEvents.isEmpty()) notifyAllObservers(removeItemFromFridgeEvents.peek());
        if (!blindsRollEvents.isEmpty()) notifyAllObservers(blindsRollEvents.peek());

        nextHourEvents.pollFirst();
        brokenApplianceEvents.pollFirst();
        personMoveEvents.pollFirst();
        petMoveEvents.pollFirst();
        startUsingAppliancesEvents.pollFirst();
        startUsingSportItemsEvents.pollFirst();
        stopUsingAppliancesEvents.pollFirst();
        stopUsingSportItemsEvents.pollFirst();
        taskFinishedEvents.pollFirst();
        turnBrokenAppliancesEvents.pollFirst();
        turnIdleAppliancesEvents.pollFirst();
        turnOnAppliancesEvents.pollFirst();
        turnOffAppliancesEvents.pollFirst();
        applianceRepairedEvents.pollFirst();

    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(BlindsRoll event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(AddItemToFridge event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(RemoveItemFromFridge event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(ApplianceRepaired event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(BrokenAppliance event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(NextHourEvent event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(PersonMoveEvent event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(PetMoveEvent event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(StartUsingAppliance event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(StartUsingSportItem event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(StopUsingAppliance event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(StopUsingSportItem event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(TaskFinished event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(TurnBrokenAppliance event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(TurnIdleAppliance event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(TurnOffAppliance event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    /**
     * Method notifies all observers
     * @param event
     */
    public void notifyAllObservers(TurnOnAppliance event){
        observers.forEach(observer -> observer.update(event));
        prevEvents.add(event);
    }

    public void accept(Visitor visitor){
        visitor.visitEventHandler(this);
    }

    public LinkedList<Event> getPrevEvents() {
        return prevEvents;
    }

    public int getTime() {
        return time;
    }
}
