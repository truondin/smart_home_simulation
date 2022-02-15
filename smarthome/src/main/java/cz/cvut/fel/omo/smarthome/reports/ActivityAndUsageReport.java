package cz.cvut.fel.omo.smarthome.reports;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.events.BlindsRoll;
import cz.cvut.fel.omo.smarthome.eventhandler.events.EventType;
import cz.cvut.fel.omo.smarthome.eventhandler.events.StartUsingAppliance;
import cz.cvut.fel.omo.smarthome.eventhandler.events.StartUsingSportItem;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ActivityAndUsageReport implements Visitor{
    private final StringBuilder stringBuilder = new StringBuilder();
    private final Logger log = Logger.getLogger(ConsumptionReport.class.getName());


    @Override
    public void visitEventHandler(EventHandler eventHandler) {
        stringBuilder.append("*** Appliance report *** \n\n");
        applianceUsage(eventHandler);
        stringBuilder.append("\n ---------- \n\n");
        sportItemsUsage(eventHandler);
        stringBuilder.append("\n ---------- \n\n");
        windowBlindsUsage(eventHandler);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constant.ACTIVITY_AND_USAGE_REPORT_NAME))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch(IOException e) {
            log.info("Creating txt file failed.");
        }
    }

    @Override
    public void visitPerson(Person person) {
        stringBuilder.append(person.getFirstname())
                .append(" ")
                .append(person.getSurname());
    }

    @Override
    public void visitAppliance(Appliance appliance) {
        stringBuilder.append(appliance.getApplianceType())
                .append(" (id: ").append(appliance.getId())
                .append(") ");
    }

    @Override
    public void visitSportItem(SportItem sportItem) {
        stringBuilder.append(sportItem.getType())
                .append(" (id: ").append(sportItem.getId())
                .append(") ");
    }

    private void sportItemsUsage(EventHandler eventHandler){
        stringBuilder.append("Sport items report: \n\n");

        List<StartUsingSportItem> usageEvents = eventHandler.getPrevEvents().stream()
                .filter(event -> event.getType().equals(EventType.START_USING_SPORT_ITEM))
                .map(event -> (StartUsingSportItem) event)
                .collect(Collectors.toList());

        HashSet<Person> people = usageEvents.stream().map(StartUsingSportItem::getUser).collect(Collectors.toCollection(HashSet::new));
        HashSet<SportItem> items = usageEvents.stream().map(StartUsingSportItem::getItem).collect(Collectors.toCollection(HashSet::new));

        for (Person p : people){
            stringBuilder.append("Person ");
            visitPerson(p);
            stringBuilder.append(" used: \n");
            for (SportItem i : items){
                int count = (int) usageEvents.stream()
                        .filter(event -> event.getItem().equals(i) && event.getUser().equals(p))
                        .count();

                if (count > 0){
                    stringBuilder.append("\t");
                    visitSportItem(i);
                    stringBuilder.append(count);
                    stringBuilder.append(" times.\n");
                }
            }
        }
    }

    private void windowBlindsUsage(EventHandler eventHandler){
        stringBuilder.append("Window blinds report: \n\n");

        List<BlindsRoll> usageEvents = eventHandler.getPrevEvents().stream()
                .filter(event -> event.getType().equals(EventType.ROLL_BLINDS))
                .map(event -> (BlindsRoll) event)
                .collect(Collectors.toList());

        HashSet<Person> people = usageEvents.stream().map(BlindsRoll::getPerson).collect(Collectors.toCollection(HashSet::new));
        HashSet<Room> rooms = usageEvents.stream().map(BlindsRoll::getRoom).collect(Collectors.toCollection(HashSet::new));

        for (Person p : people) {
            stringBuilder.append("Person ");
            visitPerson(p);
            stringBuilder.append(" rolled blinds in rooms: \n");
            for (Room room : rooms) {
                int count = (int) usageEvents.stream()
                        .filter(event -> event.getRoom().equals(room) && event.getPerson().equals(p))
                        .count();


                if (count > 0) {
                    stringBuilder.append("\t")
                            .append(room.getRoomType()).append(" ")
                            .append(count)
                            .append(" times.\n");
                }
            }
        }
    }

    private void applianceUsage(EventHandler eventHandler) {

        List<StartUsingAppliance> usageEvents = eventHandler.getPrevEvents().stream()
                .filter(event -> event.getType().equals(EventType.START_USING_APPLIANCE))
                .map(event -> (StartUsingAppliance) event)
                .collect(Collectors.toList());

        HashSet<Person> people = usageEvents.stream().map(StartUsingAppliance::getUser).collect(Collectors.toCollection(HashSet::new));
        HashSet<Appliance> appliances = usageEvents.stream().map(StartUsingAppliance::getAppliance).collect(Collectors.toCollection(HashSet::new));

        for (Person p : people) {
            stringBuilder.append("Person ");
            visitPerson(p);
            stringBuilder.append(" used: \n");
            for (Appliance appliance : appliances) {
                int count = (int) usageEvents.stream()
                        .filter(event -> event.getAppliance().equals(appliance) && event.getUser().equals(p))
                        .count();


                if (count > 0) {
                    stringBuilder.append("\t");
                    visitAppliance(appliance);
                    stringBuilder.append(count);
                    stringBuilder.append(" times.\n");
                }
            }
        }
    }
}
