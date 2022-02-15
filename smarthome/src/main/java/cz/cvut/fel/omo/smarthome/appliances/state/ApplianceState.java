package cz.cvut.fel.omo.smarthome.appliances.state;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceBase;

public interface ApplianceState {
    ApplianceStateType getStateType();

    ApplianceBase getAppliance();

    void changeToOn();
    void changeToOff();
    void changeToBroken();
    void changeToIdle();

    boolean isOn();
    boolean isOff();
    boolean isBroken();
    boolean isIdle();
}
