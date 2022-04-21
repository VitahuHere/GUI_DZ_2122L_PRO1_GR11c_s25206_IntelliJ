package containers.interfaces;

public interface ElectricInterface {
    default void PowerOn() {
        System.out.println("Container connected");
    }

    default void PowerOff() {
        System.out.println("Container powered off");
    }
}
