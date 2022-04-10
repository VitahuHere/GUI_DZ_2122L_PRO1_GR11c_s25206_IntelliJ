package exceptions;

import containers.classes.StandardContainer;

public class IrresponsibleSenderWithDangerousGood extends Exception {
    public IrresponsibleSenderWithDangerousGood(StandardContainer container) {
        super("Container: " + container.id + " arrived to warehouse on " + container.arrivalDate +
                " and had to be removed on " + container.dueDate +
                " due to exceeding the maximum storing time. \n" +
                "Sender " + container.sender.name + " " + container.sender.surname + " born: " + container.sender.getBirthday() +
                " received a strike. 2 Strikes will result in blocking containers from offloading to warehouse.");
    }
}
