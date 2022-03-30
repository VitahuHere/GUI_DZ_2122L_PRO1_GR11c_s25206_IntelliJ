package app;

import containers.classes.*;
import utils.ConsoleColors;
import utils.Evaluators;

public class ContainerCreation {
    public ContainerCreation(){
        createContainer();
    }

    protected void createContainer(){
        int choice;
        ConsoleColors.printYellow("Welcome to Container creation page!");
        ConsoleColors.printBlue("""
                Please select container type:
                1. Standard container
                2. Heavy container
                3. Chiller container
                4. Container for explosives
                5. Container for liquids
                6. Toxic liquids container
                7. Toxic loose material container
                0. Back
                """);
        choice = Evaluators.getIntFromInput(0, 7);


        switch(choice){
            case 1 -> new StandardContainer();
            case 2 -> new HeavyContainer();
            case 3 -> new ChillerContainer();
            case 4 -> new ExplosivesContainer();
            case 5 -> new LiquidsContainer();
            case 6 -> new ToxicLiquidContainer();
            case 7 -> new ToxicLooseMaterialContainer();
            case 0 -> App.menu();
        }

    }
}


