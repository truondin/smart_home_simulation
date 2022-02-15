package cz.cvut.fel.omo.smarthome;

import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.reports.ActivityAndUsageReport;
import cz.cvut.fel.omo.smarthome.reports.ConsumptionReport;
import cz.cvut.fel.omo.smarthome.reports.EventReport;
import cz.cvut.fel.omo.smarthome.reports.HouseConfigurationReport;
import cz.cvut.fel.omo.smarthome.simulation.Simulation;
import cz.cvut.fel.omo.smarthome.simulation.conf.Configuration1;

public class Main {
    public static void main(String[] args) {
//        Configuration2 conf = new Configuration2();
        Configuration1 conf = new Configuration1();

        House house = conf.getHouse();
        Simulation simulation = new Simulation(house);

        simulation.run(7);

        ActivityAndUsageReport activityAndUsageReport = new ActivityAndUsageReport();
        ConsumptionReport consumptionReport = new ConsumptionReport();
        EventReport eventReport = new EventReport();
        HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport();

        activityAndUsageReport.visitEventHandler(EventHandler.getInstance());
        consumptionReport.visitHouse(house);
        eventReport.visitEventHandler(EventHandler.getInstance());
        houseConfigurationReport.visitHouse(house);
    }
}
