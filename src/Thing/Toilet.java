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
        Action actionBuangAir = new Action("pee", duration, this);
        sim.addAction(actionBuangAir);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionBuangAir);
    }

    public void effect(Sim sim, Action action) {
        while (action.getDurationLeft() > 0) {
            sim.decreaseActionDuration(action);
        }
        sim.changeMood(10);
        sim.changeKekenyangan(-20);
    }

}
