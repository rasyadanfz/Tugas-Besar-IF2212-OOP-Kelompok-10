package src.Thing;

import src.*;

public class MejaKursi extends ActiveItems {
    public MejaKursi(String kodeItem) {
        super("Meja Dan Kursi", kodeItem, 3, 3, 50);
    }

    public MejaKursi() {
        super("Meja Dan Kursi", 3, 3, 50);
    }

    public void makan(Sim sim, Food food) {
        Action actionMakan = new Action("eating", 30, this);
        sim.addAction(actionMakan);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionMakan);
    }

    public void effect(Sim sim, Action action) {
    }

    public void effect(Sim sim, Action action, Food food) {
        while (action.getDurationLeft() > 0) {
            sim.decreaseActionDuration(action);
        }
        sim.changeKekenyangan(food.getKekenyangan());

    }

}