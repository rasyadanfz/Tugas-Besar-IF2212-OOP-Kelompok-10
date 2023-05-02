package src.Thing;

import src.Action;
import src.Sim;

public class Shower extends ActiveItems {
    public Shower(String kodeItem) {
        // agak ngasal karena ga ada objeknya di spesifikasi
        super("Shower", kodeItem, 1, 1, 50);
    }

    public Shower() {
        // agak ngasal karena ga ada objeknya di spesifikasi
        super("Shower", 1, 1, 50);
    }

    public void mandi(Sim sim) {
        Action actionMandi = new Action("mandi", 30, this);
        sim.addAction(actionMandi);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionMandi);
    }

    public void effect(Sim sim, Action action) {
        while (action.getDurationLeft() > 0) {
            sim.decreaseActionDuration(action);
        }
        sim.changeMood(5);
        sim.changeKesehatan(5);
    }
}
