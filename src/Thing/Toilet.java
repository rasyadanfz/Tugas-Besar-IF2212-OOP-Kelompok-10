package src.Thing;

import src.Sim;

public class Toilet extends Thing {
    public Toilet(String kodeItem) {
        super("Toilet", kodeItem, 1, 1, 50);
    }

    public Toilet() {
        super("Toilet", 1, 1, 50);
    }

    public static void buangAir(Sim sim, int duration) {
        sim.addAction(new Action("pee", duration));
        sim.setStatus("active");
        while (duration > 0) {
            sim.changeMood(10);
            sim.changeKekenyangan(-20);
            duration--;
        }
    }

}
