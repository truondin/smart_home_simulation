package cz.cvut.fel.omo.smarthome.appliances;

import cz.cvut.fel.omo.smarthome.appliances.documents.ApplianceDoc;
import cz.cvut.fel.omo.smarthome.appliances.state.ApplianceState;
import cz.cvut.fel.omo.smarthome.appliances.state.ApplianceStateType;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.reports.Visitor;

public interface Appliance {
    ApplianceType getApplianceType();
    int getConsumption();
    int getIdleConsumption();
    int getTotalConsumption();
    void setTotalConsumption(int value);
    Room getRoom();
    void setRoom(Room room);
    ApplianceStateType getApplianceStateType();
    ApplianceState getApplianceState();
    void setApplianceState(ApplianceState type);
    void repair(Person person);
    void turnOn();
    void turnOff();
    int getDurability();
    ApplianceDoc getDocumentation();
    boolean isAvailable();
    int getId();

    void accept(Visitor visitor);
}
