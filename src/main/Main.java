package main;


import containers.classes.*;
import port.Port;
import sender.Sender;
import ship.Ship;
import utils.CacheHandler;
import utils.ConsoleColors;

import java.util.ArrayList;
import java.util.Timer;


public class Main {
    public static void main(String[] args) {
        new Ship("Evergreen", "Amsterdam", 120000, 100, 12, 12, 12);
        new Ship("MaxHaul", "Tokyo", 200000, 150, 70, 30, 20);
        new Ship("GearBest", "Gdansk", 9000, 30, 7, 8, 9);
        ArrayList<String> safetyMeasures = new ArrayList<>();
        safetyMeasures.add("Metal lock");

        ArrayList<String> certificates = new ArrayList<>();
        certificates.add("Fuel safe");

        Sender sender = new Sender("Andrew", "Nowacki", "22222222222", "Some Street 123");
        Sender sender2 = new Sender("John", "Narrow", "63031217698", "Other Street 321");
        Sender sender3 = new Sender("Mark", "Stewart", "71052273933", "Next Street 222");
        Sender sender4 = new Sender("Jennifer", "Taran", "86082872825", "This Street 333");

        Port.ships.get(0).addContainerOfType(new StandardContainer(500, 40, 6340, safetyMeasures, certificates, sender));
        Port.ships.get(0).addContainerOfType(new StandardContainer(200, 20, 3201, safetyMeasures, certificates, sender));


        Port.ships.get(0).addContainerOfType(new ToxicLiquidContainer(120, 20, 3522, safetyMeasures, certificates, 20, "Plastic", true, sender2));
        Port.ships.get(0).addContainerOfType(new ToxicLiquidContainer(180, 20, 2342, safetyMeasures, certificates, 22, "Plastic", true, sender2));


        new StandardContainer(430, 20, 2341, safetyMeasures, certificates, sender);
        new ChillerContainer(420, 20, 4269, safetyMeasures, certificates, 54, "Steel", 9000, sender3);
        new LiquidsContainer(243, 20, 5432, safetyMeasures, certificates, 9200, sender4);
        new ToxicLooseMaterialContainer(754, 45, 8690, safetyMeasures, certificates, 22, "n/a", true, sender);

        Port.warehouse.addContainer(new StandardContainer(430, 20, 2341, safetyMeasures, certificates, sender));
        Port.warehouse.addContainer(new ChillerContainer(420, 20, 4269, safetyMeasures, certificates, 54, "Steel", 9000, sender3));
        Port.warehouse.addContainer(new LiquidsContainer(243, 20, 5432, safetyMeasures, certificates, 9200, sender4));
        Port.warehouse.addContainer(new ToxicLooseMaterialContainer(754, 45, 8690, safetyMeasures, certificates, 22, "n/a", true, sender));

        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
        Timer timer = new Timer();
        timer.schedule(new TimeOperations(), 0, 1000);
        CacheHandler.saveApp();
    }
}
