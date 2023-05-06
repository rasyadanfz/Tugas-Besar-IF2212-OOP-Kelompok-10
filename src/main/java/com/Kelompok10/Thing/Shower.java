package src.main.java.com.Kelompok10.Thing;

import src.main.java.com.Kelompok10.Action;
import src.main.java.com.Kelompok10.Sim;

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
        sim.changeMood(5);
        sim.changeKesehatan(5);
    }
}
