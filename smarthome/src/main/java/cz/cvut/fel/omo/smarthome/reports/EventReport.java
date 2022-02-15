package cz.cvut.fel.omo.smarthome.reports;

import cz.cvut.fel.omo.smarthome.eventhandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventhandler.events.Event;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class EventReport implements Visitor{
    private final StringBuilder stringBuilder = new StringBuilder();
    private final Logger log = Logger.getLogger(ConsumptionReport.class.getName());

    @Override
    public void visitEventHandler(EventHandler eventHandler) {
        List<Event> events = eventHandler.getPrevEvents();
        stringBuilder.append("*** Event report *** \n");

        groupByEvent(events);
        groupBySource(events);
        groupByTargets(events);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constant.EVENT_REPORT_NAME))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch(IOException e) {
            log.info("Creating txt file failed.");
        }
    }

    @Override
    public void visitEvent(Event event) {
        stringBuilder.append("Event type: ")
                .append(event.getType())
                .append(" time: ").append(event.getTime());
        if (event.getSource() != null){
            stringBuilder.append(", source: ").append(event.getSource().getName());
        }
        if (event.getTargets() != null && !event.getTargets().isEmpty()){
            stringBuilder.append(", targets: ");
            event.getTargets().forEach(t -> stringBuilder.append(t.getName()));
        }


        stringBuilder.append("\n");
    }

    private void groupByEvent(List<Event> events){
        stringBuilder.append("---------- \n \n")
                .append("Event reports group by event types:\n");

        events.stream().sorted(Comparator.comparing(Event::getType)).forEach(event -> event.accept(this));
        stringBuilder.append("\n");
    }

    private void groupBySource(List<Event> events){
        stringBuilder.append("---------- \n \n")
                .append("Event reports group by source:\n");

        events.stream().filter(event -> event.getSource() != null)
                .sorted(Comparator.comparing(event -> event.getSource().getName()))
                .forEach(event -> event.accept(this));
        stringBuilder.append("\n");
    }

    private void groupByTargets(List<Event> events){
        stringBuilder.append("Event reports group by source:\n")
                .append("---------- \n \n");

        events.stream().filter(event -> event.getTargets() != null && !event.getTargets().isEmpty())
                .sorted(Comparator.comparing(event -> event.getTargets().get(0).getName()))
                .forEach(event -> event.accept(this));
        stringBuilder.append("\n");
    }
}
