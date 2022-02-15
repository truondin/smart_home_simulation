package cz.cvut.fel.omo.smarthome.appliances.documents;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceType;

import java.util.HashSet;

public class Documentations {
    private final HashSet<ApplianceDoc> docs;

    public Documentations() {
        this.docs = new HashSet<>();
        docs.add(new ApplianceDoc(ApplianceType.WASHING_MACHINE, "Washing Machine documentation."));
        docs.add(new ApplianceDoc(ApplianceType.VACUUM_CLEANER, "Vacuum cleaner documentation."));
        docs.add(new ApplianceDoc(ApplianceType.THERMOSTAT, "Thermostat documentation."));
        docs.add(new ApplianceDoc(ApplianceType.TV, "Television documentation."));
        docs.add(new ApplianceDoc(ApplianceType.OVEN, "Oven documentation."));
        docs.add(new ApplianceDoc(ApplianceType.LAMP, "Lamp documentation."));
        docs.add(new ApplianceDoc(ApplianceType.FRIDGE, "Fridge documentation."));
        docs.add(new ApplianceDoc(ApplianceType.DISHWASHER, "Dishwasher documentation."));
    }

    public ApplianceDoc getDocumentation(ApplianceType appliance){
        return docs.stream().filter(doc -> doc.getType().equals(appliance)).findFirst().orElse(null);
    }

}
