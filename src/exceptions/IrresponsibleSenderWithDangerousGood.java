package exceptions;

public class IrresponsibleSenderWithDangerousGood extends Exception {
    public IrresponsibleSenderWithDangerousGood(int containerId, String warehouseArrivalDate, String utilizationDate) {
        super("Container: " + containerId + " arrived on " + warehouseArrivalDate + " and had to be removed on " +
                utilizationDate + " due to exceeding" + " the maximum storing time");
    }
}
