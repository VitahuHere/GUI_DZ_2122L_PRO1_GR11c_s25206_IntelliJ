package main;


import containers.classes.*;
import port.Port;
import sender.Sender;
import ship.ContainerLoading;
import ship.Ship;
import utils.ConsoleColors;
import utils.caching.CacheHandler;

import java.io.File;
import java.util.ArrayList;


public class Main {
    public static Thread mainThread = Thread.currentThread();

    public static void main(String[] args) {
        File ignore = new File("cache");
        if (ignore.exists() && ignore.isDirectory()) {
            ConsoleColors.printGreen("Session restored!");
            TimeOperations timeOperations = new TimeOperations();
            timeOperations.start();
            CacheHandler.loadApp();
            ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
            App.menu();
        }
        new Ship("Evergreen", "Amsterdam", 120000, 100, 12, 12, 12);
        new Ship("MaxHaul", "Tokyo", 200000, 150, 70, 30, 20);
        new Ship("GearBest", "Gdansk", 9000, 30, 7, 8, 9);
        new Ship("Maersk", "New York", 1000000, 300, 70, 80, 99);
        new Ship("Virginia", "Snake Island", 670000, 500, 70, 300, 100);
        ArrayList<String> safetyMeasures = new ArrayList<>();
        safetyMeasures.add("Metal lock");
        safetyMeasures.add("Fire extinguisher");

        ArrayList<String> certificates = new ArrayList<>();
        certificates.add("Fuel safe");

        Sender sender = new Sender("Andrew", "Nowacki", "22222222222", "Some Street 123");
        Sender sender2 = new Sender("John", "Narrow", "63031217698", "Other Street 321");
        Sender sender3 = new Sender("Mark", "Stewart", "71052273933", "Next Street 222");
        Sender sender4 = new Sender("John", "Taran", "86082872825", "This Street 333");

        Port.ships.get(0).addContainerOfType(new StandardContainer(500, 40, 6340, safetyMeasures, certificates, sender));
        Port.ships.get(1).addContainerOfType(new StandardContainer(200, 20, 3201, safetyMeasures, certificates, sender));


        Port.ships.get(2).addContainerOfType(new ToxicLiquidContainer(120, 20, 3522, safetyMeasures, certificates, 20, "Plastic", true, sender2));
        Port.ships.get(0).addContainerOfType(new ToxicLiquidContainer(180, 20, 2342, safetyMeasures, certificates, 22, "Plastic", true, sender2));

        Port.ships.get(4).addContainerOfType(new StandardContainer(430, 20, 2341, safetyMeasures, certificates, sender));
        Port.ships.get(3).addContainerOfType(new ChillerContainer(300, 20, 2342, safetyMeasures, certificates, 54, "Steel", 9000, sender3));
        Port.ships.get(4).addContainerOfType(new LiquidsContainer(243, 20, 6544, safetyMeasures, certificates, 9200, sender4));
        Port.ships.get(1).addContainerOfType(new ToxicLooseMaterialContainer(754, 45, 7555, safetyMeasures, certificates, 26, "n/a", true, sender2));

        TimeOperations timeOperations = new TimeOperations();
        timeOperations.start();

        ContainerLoading.loadContainerToWarehouse(new StandardContainer(430, 20, 2341, safetyMeasures, certificates, sender));
        ContainerLoading.loadContainerToWarehouse(new ChillerContainer(420, 20, 4269, safetyMeasures, certificates, 54, "Steel", 9000, sender3));
        ContainerLoading.loadContainerToWarehouse(new LiquidsContainer(243, 20, 5432, safetyMeasures, certificates, 9200, sender4));
        ContainerLoading.loadContainerToWarehouse(new ToxicLooseMaterialContainer(754, 45, 8690, safetyMeasures, certificates, 22, "n/a", true, sender2));

        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
        App.menu();
    }
}