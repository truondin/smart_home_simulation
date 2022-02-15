package cz.cvut.fel.omo.smarthome.appliances.state;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceBase;

public class TurnedOnState extends ApplianceStateBase{

    public TurnedOnState(ApplianceBase appliance) {
        super(appliance);
        this.stateType = ApplianceStateType.ON;
    }
}
