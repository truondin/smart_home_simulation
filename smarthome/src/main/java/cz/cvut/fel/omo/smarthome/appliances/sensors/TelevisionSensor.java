package cz.cvut.fel.omo.smarthome.appliances.sensors;

import cz.cvut.fel.omo.smarthome.appliances.Television;
import cz.cvut.fel.omo.smarthome.eventhandler.events.*;
import cz.cvut.fel.omo.smarthome.creatures.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelevisionSensor extends SensorDecorator<Television>{

    private final Set<Person> watchers;
    private static final Logger log = Logger.getLogger(TelevisionSensor.class.getName());

    public TelevisionSensor(Television appliance) {
        super(appliance);
        this.watchers = new HashSet<>();
    }

    /**
     * changes tv channel
     * @param channel channel to switch
     */
    public void changeChannel(String channel){
        appliance.changeChannel(channel);
    }

    /**
     * installs new channel on tv
     * @param name new channel
     */
    public void installChannel(String name){
        appliance.installChannel(name);
    }

    /**
     * installs list of channels
     * @param channels list of new channels
     */
    public void installChannels(List<String> channels){
        appliance.installChannels(channels);
    }

    /**
     * turns television on
     */
    public void startWatching(){
        appliance.startWatching();
    }

    /**
     * returns current channel
     * @return name of channel
     */
    public String getChannel() {
        return appliance.getChannel();
    }

    /**
     * checks and updates television on new hour
     * @param event
     */
    @Override
    public void update(NextHourEvent event) {
        if (appliance.getApplianceState().isOn()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getConsumption());
            appliance.lowerDurability();

            log.log(Level.INFO,"Watching channel: " + appliance.getChannel());

            if (watchers.isEmpty()){
                eventHandler.addEvent(new TurnOffAppliance(appliance, appliance, eventHandler.getTime(), appliance));
            }

        }else if(appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new BrokenAppliance(appliance,eventHandler.getTime(), appliance));
        }else if (appliance.getApplianceState().isIdle()){
            appliance.setTotalConsumption(appliance.getTotalConsumption() + appliance.getIdleConsumption());
        }



        if (willBreak() && !appliance.getApplianceState().isBroken()){
            eventHandler.addEvent(new TurnBrokenAppliance(appliance, eventHandler.getTime(), appliance));
            log.log(Level.INFO, "Television (id:" + appliance.getId() + ") broke down");

        }
    }

    /**
     * adds new person who watch television
     * @param person new watcher
     */
    public void addWatcher(Person person){
        watchers.add(person);
    }

    /**
     * removes person from list of people who watch television
     * @param person person to remove
     */
    public void removeWatcher(Person person){
        watchers.remove(person);
    }

    /**
     * Set of people who watch television
     * @return
     */
    public Set<Person> getWatchers() {
        return watchers;
    }
}
