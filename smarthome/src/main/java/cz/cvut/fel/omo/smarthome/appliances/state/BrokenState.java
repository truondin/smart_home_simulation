package cz.cvut.fel.omo.smarthome.appliances.state;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceBase;

public class BrokenState extends ApplianceStateBase{

    public BrokenState(ApplianceBase appliance) {
        super(appliance);
        this.stateType = ApplianceStateType.BROKEN;
    }
}
