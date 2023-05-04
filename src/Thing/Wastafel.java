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
        System.out.print("Sisa durasi: ");
        while (action.getDurationLeft() > 0) {
            if (action.getDurationLeft() < 10) {
                System.out.print("00" + action.getDurationLeft());
            } else if (action.getDurationLeft() < 100) {
                System.out.print("0" + action.getDurationLeft());
            } else {
                System.out.print(action.getDurationLeft());
            }
            System.out.print("\b\b\b");
            sim.decreaseActionDuration(action);
            if (action.getDurationLeft() == 0) {
                System.out.print(000);
            }
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