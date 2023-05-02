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

    public void effect(Sim sim, Action action) {
        while (action.getDurationLeft() > 0) {
            sim.decreaseActionDuration(action);
        }
        sim.changeKesehatan(5);
    }

    public void cuciTangan(Sim sim) {
        Action actionCuciTangan = new Action("washingHand", 5, this);
        sim.addAction(actionCuciTangan);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionCuciTangan);
    }
}