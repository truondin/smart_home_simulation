package cz.cvut.fel.omo.smarthome.reports;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.utils.Constant;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class ConsumptionReport implements Visitor{

    private final StringBuilder stringBuilder = new StringBuilder();
    private final Logger log = Logger.getLogger(ConsumptionReport.class.getName());

    @Override
    public void visitAppliance(Appliance appliance) {
        stringBuilder.append("Appliance ").append(appliance.getApplianceType())
                .append(" (id:").append(appliance.getId()).append(")")
                .append(" consumption: ").append(appliance.getTotalConsumption()).append(" kWh")
                .append(" which is ")
                .append(appliance.getTotalConsumption() * Constant.KWH_PER_CZK).append(" czk.\n \n");
    }

    @Override
    public void visitHouse(House house) {
        stringBuilder.append("*** Consumption report ***\n\n");

        int totalConsumption = 0;
        for (Appliance appliance: house.getAppliances()) {
            totalConsumption += appliance.getTotalConsumption();
        }

        stringBuilder.append("Total consumption: ")
                .append(totalConsumption).append(" kWh ")
                .append("which is ")
                .append(totalConsumption * Constant.KWH_PER_CZK).append(" czk.\n ");

        stringBuilder.append("-------------------- \n");

        house.getAppliances().forEach(appliance -> appliance.accept(this));

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constant.CONSUMPTION_REPORT_NAME))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch(IOException e) {
            log.info("Creating txt file failed.");
        }
    }
}
