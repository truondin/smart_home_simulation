package cz.cvut.fel.omo.smarthome.appliances.state;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceBase;


public abstract class ApplianceStateBase implements ApplianceState {
    protected ApplianceBase appliance;
    protected ApplianceStateType stateType;
    protected int consumption;

    public ApplianceStateBase(ApplianceBase appliance) {
        this.appliance = appliance;
    }


    public int getConsumption() {
        return consumption;
    }

    @Override
    public ApplianceStateType getStateType() {
        return stateType;
    }

    @Override
    public ApplianceBase getAppliance() {
        return appliance;
    }

    @Override
    public void changeToOn() {
        this.appliance.setApplianceState(new TurnedOnState(this.appliance));
    }

    @Override
    public void changeToOff() {
        appliance.setApplianceState(new TurnedOffState(this.appliance));
    }

    @Override
    public void changeToBroken() {
        appliance.setApplianceState(new BrokenState(this.appliance));
    }

    @Override
    public void changeToIdle() {
        appliance.setApplianceState(new IdleState(appliance));
    }

    @Override
    public boolean isOn() {
        return stateType.equals(ApplianceStateType.ON);
    }

    @Override
    public boolean isOff() {
        return stateType.equals(ApplianceStateType.OFF);
    }

    @Override
    public boolean isIdle() {
        return stateType.equals(ApplianceStateType.IDLE);
    }

    @Override
    public boolean isBroken() {
        return stateType.equals(ApplianceStateType.BROKEN);
    }
}
