package src.Thing;

import src.Action;
import src.Sim;

public class Wastafel extends ActiveItems {
    public Wastafel(String kodeItem) {
        super("Wastafel", kodeItem, 1, 1, 40);
    }

    public Wastafel() {
        super("Wastafel", 1, 1, 40);
    }

    public void effect(Sim sim, int duration) {
        sim.changeKesehatan(5);
        duration--;
    }

    public void cuciTangan(Sim sim) {
        sim.addAction(new Action("washingHand", 5, this));
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, 5);
    }
}