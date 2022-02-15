package cz.cvut.fel.omo.smarthome.appliances.state;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceBase;

public class TurnedOffState extends ApplianceStateBase{
    public TurnedOffState(ApplianceBase appliance) {
        super(appliance);
        this.stateType = ApplianceStateType.OFF;
    }
}
