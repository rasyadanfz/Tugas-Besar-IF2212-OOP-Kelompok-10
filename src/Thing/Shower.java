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
        sim.addAction(new Action("mandi", 30, this));
        sim.setStatus("active");
        sim.setInActiveAction(true);
    }

    public void effect(Sim sim, int duration) {
        while (duration > 0) {
            // Setiap 30 detik :
            sim.changeMood(5);
            sim.changeKesehatan(5);
            duration -= 30;
        }
    }
}
