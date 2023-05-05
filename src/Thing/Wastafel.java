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
            int printDuration = action.getDurationLeft() - 1;
            if (printDuration < 10) {
                System.out.print("00" + printDuration);
            } else if (printDuration < 100) {
                System.out.print("0" + printDuration);
            } else {
                System.out.print(printDuration);
            }
            if (printDuration != 0) {
                System.out.print("\b\b\b");
            }
            sim.decreaseActionDuration(action);
        }
        System.out.println();
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