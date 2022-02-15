package cz.cvut.fel.omo.smarthome.reports;

import cz.cvut.fel.omo.smarthome.appliances.Appliance;
import cz.cvut.fel.omo.smarthome.building.*;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.creatures.Person;
import cz.cvut.fel.omo.smarthome.creatures.Pet;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class HouseConfigurationReport implements Visitor{

    private final StringBuilder stringBuilder = new StringBuilder();
    private final Logger log = Logger.getLogger(ConsumptionReport.class.getName());

    @Override
    public void visitHouse(House house) {
        stringBuilder.append("*** House Configuration report *** \n\n");

        stringBuilder.append("House: ")
                .append(house.getName())
                .append("\n");

        for (Floor floor: house.getFloors()) {
            floor.accept(this);
        }

        stringBuilder.append("-----------------\n")
                .append("Appliances:\n\n");

        for (Appliance appliance: house.getAppliances()) {
            appliance.accept(this);
        }

        stringBuilder.append("-----------------\n")
                .append("Sport Items:\n\n");

        for (SportItem sportItem: house.getSportItems()) {
            sportItem.accept(this);
        }

        stringBuilder.append("-----------------\n")
                .append("People:\n\n");

        for (Person person: house.getPeople()) {
            person.accept(this);
        }
        stringBuilder.append("-----------------\n")
                .append("Pets:\n\n");
        for (Pet pet: house.getPets()) {
            pet.accept(this);
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constant.HOUSE_CONFIGURATION_REPORT_NAME))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch(IOException e) {
            log.info("Creating txt file failed.");
        }
    }

    @Override
    public void visitFloor(Floor floor) {
        stringBuilder.append("\nFloor level: ").append(floor.getLevel()).append(" \n");

        for (Room room: floor.getRooms()) {
            room.accept(this);
        }
    }

    @Override
    public void visitRoom(Room room) {

        stringBuilder.append("\t Room: ")
                .append(room.getRoomType()).append(" \n");

        for (Window window: room.getWindowsList()) {
            window.accept(this);
        }
    }

    @Override
    public void visitWindow(Window window) {

        stringBuilder.append("\t Window");

        if (window.getBlind() != null) {

            String status = window.getBlind().isRolledUp()? "OPENED" : "CLOSED";

            stringBuilder.append(" - blind status: ")
                    .append(status).append("\n \n");
        }
    }

    @Override
    public void visitAppliance(Appliance appliance) {
        stringBuilder.append("Appliance: ")
                .append(appliance.getApplianceType()).append(" (id: ").append(appliance.getId()).append(")")
                .append("\n \t \t in room: ").append(appliance.getRoom().getRoomType())
                .append(" on floor: ").append(appliance.getRoom().getFloor().getLevel())
                .append("\n \t \t durability: ").append(appliance.getDurability())
                .append("\n \t \t state: ").append(appliance.getApplianceStateType())
                .append("\n \t \t consumption: ").append(appliance.getConsumption()).append(" kWh")
                .append("\n \t \t idle consumption: ").append(appliance.getIdleConsumption()).append(" kWh \n \n");
    }

    @Override
    public void visitSportItem(SportItem sportItem) {
        stringBuilder.append("Sport item (id:").append(sportItem.getId())
                .append(") type: ").append(sportItem.getType()).append("\n \n");
    }

    @Override
    public void visitPerson(Person person) {
        stringBuilder.append(person.getFirstname()).append(" ").append(person.getSurname())
                .append("\n \t  age: ").append(person.getAge())
                .append("\n \t  role: ").append(person.getRole()).append("\n \n");
    }

    @Override
    public void visitPet(Pet pet) {
        stringBuilder.append(pet.getName())
                .append("´s breed is ").append(pet.getBreed())
                .append(".\n \n");
    }

}
