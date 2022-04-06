package main;


import app.App;
import containers.classes.StandardContainer;
import containers.classes.ToxicLiquidContainer;
import port.Port;
import ship.Ship;
import utils.ConsoleColors;

import java.util.ArrayList;
import java.util.Timer;


public class Main {
    public static void main(String[] args){
        new Ship("Evergreen", "Amsterdam", 1200, 100, 12, 12,12);
        ArrayList<String> list = new ArrayList<>();
        list.add("moby");
        list.add("moby");

        StandardContainer container = new StandardContainer(12, 12, 12, list, list);
        Port.ships.get(0).loadContainer(container);
        new StandardContainer(12, 12, 12, list, list);
        new StandardContainer(12, 12, 12, list, list);
        new ToxicLiquidContainer(12, 12, 12, list, list, 12, "idk", true);
        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
        Timer timer = new Timer();
        timer.schedule(new TimeOperations(), 0, 1000);
        App.menu();
    }
}
