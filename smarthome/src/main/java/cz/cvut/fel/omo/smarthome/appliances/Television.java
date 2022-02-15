package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOffAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.TurnOnAppliance;
import cz.cvut.fel.omo.smarthome.utils.Constant;
import cz.cvut.fel.omo.smarthome.utils.Utils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Television extends ApplianceBase{
    private final List<String> channels;
    private String channel;

    private static final Logger log = Logger.getLogger(Television.class.getName());

    public Television(Room room) {
        super(room);
        this.type = ApplianceType.TV;
        this.consumption = ApplianceType.TV.consumption;
        this.idleConsumption = ApplianceType.TV.idleConsumption;
        this.channels = Constant.DEFAULT_CHANNELS;

        room.addAppliance(this);
    }


    public void changeChannel(String channel){
        if (channels.contains(channel)){
            this.channel = channel;
            log.log(Level.INFO,"TV channel changed");
        }else{
            log.log(Level.INFO,"TV does not have this channel");
        }
    }

    public void installChannel(String name){
        if (!channels.contains(name)){
            channels.add(name);
            log.log(Level.INFO,"New channel has been installed");
        }
    }

    public void installChannels(List<String> channels){
        channels.forEach(this::installChannel);
    }

    @Override
    public void update(TurnOnAppliance event) {
        if (event.getAppliance().equals(this)){
            startWatching();
        }
    }

    @Override
    public void update(TurnOffAppliance event) {
        if (event.getAppliance().equals(this)){
            turnOff();
        }
    }

    @Override
    public void turnOn() {
        state.changeToIdle();
        eventHandler.addEvent(new TurnOnAppliance(this, this, eventHandler.getTime(), this));
        log.log(Level.INFO,"TV turned on.");
    }

    @Override
    public void turnOff() {
        state.changeToOff();
        log.log(Level.INFO,"TV turned off.");
    }

    public void startWatching(){
        if (channel == null){
            channel = channels.get(Utils.randomInt(channels.size()));
        }

        state.changeToOn();
        log.log(Level.INFO,"started watching channel: " + channel);
    }

    public String getChannel() {
        return channel;
    }

}
