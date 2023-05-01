package src.Thing;

import src.Sim;
import src.Exceptions.DurationNotValidException;
import src.Action;
import src.ActionEffect;

public class Lukisan extends ActiveItems {
    public Lukisan(String kodeItem) {
        // ngasal karena objeknya ga ada di spesifikasi
        super("Lukisan", kodeItem, 1, 1, 100);
    }

    public Lukisan() {
        // ngasal karena objeknya ga ada di spesifikasi
        super("Lukisan", 1, 1, 100);
    }

    public void lihatLukisan(Sim sim, int duration) {
        // untuk setiap 20 detik (ngasal juga)
        try {
            if (duration % 20 == 0) {
                sim.addAction(new Action("lihatLukisan", duration, this));
                sim.setStatus("active");
            } else {
                throw new DurationNotValidException(20);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void effect(Sim sim, int duration) {
        int x = duration / 20;
        for (int i = 0; i < x; i++) {
            sim.changeKesehatan(-10);
            sim.changeMood(20);
        }
    }
}
