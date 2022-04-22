package utils;

import containers.classes.StandardContainer;
import sender.Sender;
import utils.caching.loaders.LoadSenders;

import java.util.ArrayList;
import java.util.Collections;

public class Returns {
    public static ArrayList<String> listContainersToSave(ArrayList<StandardContainer> containers) {
        ArrayList<String> containerList = new ArrayList<>();
        Collections.sort(containers);
        for (StandardContainer container : containers) {
            containerList.add("[" + container.toSaveString() + "]\n\t");
        }
        return containerList;
    }

    public static ArrayList<String> listContainers(ArrayList<StandardContainer> containers) {
        ArrayList<String> containerList = new ArrayList<>();
        for (StandardContainer container : containers) {
            containerList.add(
                    "\nid: " + container.id +
                            ", container type: " + container.getClass().getSimpleName() +
                            ", sender: " + (container.sender == null ? "not set" : container.sender.name + " " + container.sender.surname) +
                            (container.arrivalDate == null ? "" : ", arrival date: " + container.arrivalDate) +
                            (container.dueDate == null ? "" : ", due date: " + container.dueDate)
            );
        }
        return containerList;
    }

    public static Sender findSender(String PESEL) {
        for (Sender sender : LoadSenders.senders) {
            if (sender.PESEL.equals(PESEL)) {
                return sender;
            }
        }
        return null;
    }
}
