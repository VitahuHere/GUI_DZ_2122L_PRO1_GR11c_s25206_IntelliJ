package main;


import containers.classes.StandardContainer;
import containers.classes.ToxicLiquidContainer;
import port.Port;
import sender.Sender;
import ship.Ship;
import utils.ConsoleColors;

import java.util.ArrayList;
import java.util.Timer;


public class Main {
    public static void main(String[] args){
        new Ship("Evergreen", "Amsterdam", 120000, 100, 12, 12,12);
        ArrayList<String> list = new ArrayList<>();
        list.add("metal lock");

        Sender sender = new Sender("Andrew", "Nowacki", "22222222222", "Some Street 123");
        Sender sender2 = new Sender("Andrew", "Nowacki", "63031217698", "Some Street 123");
        Sender sender3 = new Sender("Andrew", "Nowacki", "71052273933", "Some Street 123");
        Sender sender4 = new Sender("Andrew", "Nowacki", "86082872825", "Some Street 123");

        for (int i = 0; i < 3; i++) {
            Port.ships.get(0).addContainerOfType(new StandardContainer(12, 12, 12, list, list, sender));
        }

        for (int i = 0; i < 10; i++) {
            Port.ships.get(0).addContainerOfType(new ToxicLiquidContainer(12, 12, 12, list, list, 12, "idk", true, sender));
        }

        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
        Timer timer = new Timer();
        timer.schedule(new TimeOperations(), 0, 1000);
        App.menu();
    }
}
