package cz.cvut.fel.omo.smarthome.appliances;

public enum ApplianceType {
    DISHWASHER(10,5),
    WASHING_MACHINE(8,4),
    VACUUM_CLEANER( 10,5),
    FRIDGE( 8,4),

    OVEN(10,5),
    TV(10,5),
    LAMP(6,3),
    THERMOSTAT(4,2);

    int consumption;
    int idleConsumption;

    ApplianceType(int consumption, int idleConsumption) {
        this.consumption = consumption;
        this.idleConsumption = idleConsumption;
    }

    public int getConsumption(){
        return consumption;
    }

    public int getIdleConsumption(){
        return idleConsumption;
    }
}
