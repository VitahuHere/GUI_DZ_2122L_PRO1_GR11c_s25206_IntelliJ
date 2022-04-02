package main;


import app.App;
import containers.classes.StandardContainer;
import containers.classes.ToxicLiquidContainer;
import port.Port;
import ship.Ship;
import utils.ConsoleColors;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args){
        Ship ship = new Ship("moby", "Amsterdam", 1200, 100, 12, 12,12);
        Port.ships.add(ship);
        App.ships.add(ship);
        ArrayList<String> list = new ArrayList<>();
        list.add("moby");
        list.add("moby");

        StandardContainer container = new StandardContainer(12, 12, 12, list, list);
        Port.ships.get(0).loadContainer(container);
        new StandardContainer(12, 12, 12, list, list);
        new StandardContainer(12, 12, 12, list, list);
        new ToxicLiquidContainer(12, 12, 12, list, list, 12, "idk", true);
        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
        App.menu();
    }
}
