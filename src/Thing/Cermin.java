package src.Thing;

import src.*;

public class Cermin extends Thing {
    public Cermin(String kodeItem) {
        super("Cermin", kodeItem, 2, 2, 50);
    }

    public Cermin() {
        super("Cermin", 2, 2, 50);
    }

    public static void bercermin(Sim sim) {
        sim.changeMood(15);
    }

}