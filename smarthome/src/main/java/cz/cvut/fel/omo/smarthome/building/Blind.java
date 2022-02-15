package cz.cvut.fel.omo.smarthome.building;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Blind {

    private boolean isRolledUp;

    private static final Logger log = Logger.getLogger(Blind.class.getName());

    public Blind() {
        this.isRolledUp = true;
    }

    public void rollUp() {
        this.isRolledUp = true;
        log.log(Level.INFO, " Blinds are rolling up");
    }

    public void rollDown() {
        this.isRolledUp = false;
        log.log(Level.INFO, " Blinds are rolling down");
    }

    public boolean isRolledUp() {
        return isRolledUp;
    }
}
