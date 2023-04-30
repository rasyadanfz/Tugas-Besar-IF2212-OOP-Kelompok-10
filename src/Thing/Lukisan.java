package src.Thing;

import src.Sim;
import src.Action;

public class Lukisan extends Thing {
    public Lukisan(String kodeItem) {
        // ngasal karena objeknya ga ada di spesifikasi
        super("Lukisan", kodeItem, 1, 1, 100);
    }

    public void lihatLukisan(Sim sim, int duration) {
        sim.addAction(new Action("sleeping", duration));
        sim.setStatus("active");
        // untuk setiap 20 detik (ngasal juga)
        while (duration > 0) {
            sim.changeMood(20);
            duration--;
        }

    }
}
