package cz.cvut.fel.omo.smarthome.appliances.state;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceBase;

public class IdleState extends ApplianceStateBase{

    public IdleState(ApplianceBase appliance) {
        super(appliance);
        this.stateType = ApplianceStateType.IDLE;
    }
}
