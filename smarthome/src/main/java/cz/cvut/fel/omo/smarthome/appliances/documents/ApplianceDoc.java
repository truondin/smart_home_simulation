package cz.cvut.fel.omo.smarthome.appliances.documents;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceType;

public class ApplianceDoc {
    private final ApplianceType type;
    private final String documentation;

    public ApplianceDoc(ApplianceType type, String documentation) {
        this.type = type;
        this.documentation = documentation;
    }

    public ApplianceType getType() {
        return type;
    }

    public String getDocumentation() {
        return documentation;
    }
}
