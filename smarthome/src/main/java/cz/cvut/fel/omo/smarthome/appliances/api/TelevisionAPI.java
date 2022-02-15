package cz.cvut.fel.omo.smarthome.appliances.api;

import cz.cvut.fel.omo.smarthome.appliances.Television;

import java.util.List;

public class TelevisionAPI {

    private final Television television;

    public TelevisionAPI(Television television) {
        this.television = television;
    }

    /**
     * Basic method to turn on device.
     *
     * Changes current state to IDLE.
     */
    public void turnOn(){
        television.turnOn();
    }

    /**
     * Basic method to start watching tv.
     *
     * Changes current state to ON.
     */
    public void watchTv(){
        television.startWatching();
    }

    /**
     * Basing method for installing channel.
     *
     * @param name of desired channel to install.
     */
    public void installChannel(String name){
        television.installChannel(name);
    }

    /**
     *Basing method for installing multiple channels.
     *
     * @param channels list of channels desired to install.
     */
    public void installChannels(List<String> channels){
        television.installChannels(channels);
    }

    /**
     *Basic method for changing TV channels.
     *
     * @param name name of the desired channel.
     */
    public void changeChannel(String name){
        television.changeChannel(name);
    }

    /**
     * Basic method to turn off device.
     *
     * Changes current state to OFF.
     */
    public void turnOff(){
        television.turnOff();
    }
}
