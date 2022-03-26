package main;


import app.App;
import containers.classes.StandardContainer;
import ship.Ship;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args){
        ArrayList<StandardContainer> al = new ArrayList<>();
        ArrayList<String> tab = new ArrayList<>();
        tab.add("none");
        StandardContainer sc = new StandardContainer(12, 12, 12, tab, tab);
        StandardContainer sc1 = new StandardContainer(11, 11, 11, tab, tab);
        StandardContainer sc2 = new StandardContainer(10, 10, 10, tab, tab);
        al.add(sc);
        al.add(sc1);
        al.add(sc2);
        System.out.println(al);
        Ship ship = new Ship("ar", "no", 2000000, 4, 1, 2, 1);
        System.out.println(ship);
    }
}
