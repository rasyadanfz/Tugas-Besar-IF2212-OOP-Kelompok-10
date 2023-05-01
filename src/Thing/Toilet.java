package src.Thing;

import src.Action;
import src.Sim;

public class Toilet extends ActiveItems {
    public Toilet(String kodeItem) {
        super("Toilet", kodeItem, 1, 1, 50);
    }

    public Toilet() {
        super("Toilet", 1, 1, 50);
    }

    public void buangAir(Sim sim, int duration) {
        sim.addAction(new Action("pee", duration, this));
        sim.setStatus("active");
        sim.setInActiveAction(true);
    }

    public void effect(Sim sim, int duration) {
        sim.changeMood(10);
        sim.changeKekenyangan(-20);
    }

}
