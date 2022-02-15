package cz.cvut.fel.omo.smarthome.eventhandler;

import cz.cvut.fel.omo.smarthome.eventhandler.events.*;

public interface Observer {

    default String getName(){
        return "Missing name";
    };

    default void update(BlindsRoll event){};

    default void update(AddItemToFridge event){};

    default void update(RemoveItemFromFridge event){};

    default void update(ApplianceRepaired event){};

    default void update(BrokenAppliance event){};

    default void update(PersonMoveEvent event){};

    default void update(PetMoveEvent event) {};

    default void update(StartUsingAppliance event) {};

    default void update(StopUsingAppliance event) {};

    default void update(TaskFinished event) {};

    default void update(TurnBrokenAppliance event) {};

    default void update(TurnIdleAppliance event) {};

    default void update(TurnOffAppliance event) {};

    default void update(TurnOnAppliance event) {};

    default void update(NextHourEvent event) {};

    default void update(StartUsingSportItem event) {};

    default void update(StopUsingSportItem event) {};

}
