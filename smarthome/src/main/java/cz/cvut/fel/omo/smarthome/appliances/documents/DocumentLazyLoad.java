package cz.cvut.fel.omo.smarthome.appliances.documents;

import cz.cvut.fel.omo.smarthome.appliances.ApplianceType;

public class DocumentLazyLoad {
    private ApplianceDoc doc;

    public ApplianceDoc getDoc(ApplianceType appliance){
        if (doc == null){
            Documentations docs = new Documentations();
            doc = docs.getDocumentation(appliance);
        }

        return doc;
    }

}
