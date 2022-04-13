package containers.interfaces;

public interface LiquidInterface {
    int valveDiameter = 12;

    default void topUp(){
        System.out.println("Container is being filled up");
    }

    default void drain(){
        System.out.println("Draining container");
    }
}
