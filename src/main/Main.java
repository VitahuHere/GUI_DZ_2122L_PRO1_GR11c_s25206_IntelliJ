package main;

import ship.Ship;

public class Main {
    public static void main(String[] args) {
        Ship ship = new Ship(
                "Evergreen",
                "Amsterdam",
                100,
                80000,
                60,
                20,
                20
        );
        System.out.println(ship);

        Ship ship1 = new Ship(
                "Evergreen",
                "Amsterdam",
                100,
                80000,
                60,
                20,
                20
        );
        System.out.println(ship1);

        Ship ship2 = new Ship(
                "Evergreen",
                "Amsterdam",
                100,
                80000,
                60,
                20,
                20
        );
        System.out.println(ship2);
    }
}
