package containers.interfaces;

public interface ElectricInterface {
    default void powerOn() {
        System.out.println("Container connected");
    }

    default void powerOff() {
        System.out.println("Container powered off");
    }
}
