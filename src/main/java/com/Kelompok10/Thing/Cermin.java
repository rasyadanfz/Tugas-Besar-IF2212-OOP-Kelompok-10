package src.main.java.com.Kelompok10.Thing;

import src.main.java.com.Kelompok10.*;

public class Cermin extends ActiveItems {
    public Cermin(String kodeItem) {
        super("Cermin", kodeItem, 2, 2, 50);
    }

    public Cermin() {
        super("Cermin", 2, 2, 50);
    }

    public void bercermin(Sim sim) {
        Action actionCermin = new Action("bercermin", 5, this);
        sim.addAction(actionCermin);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionCermin);
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
        sim.changeMood(15);
    }

}