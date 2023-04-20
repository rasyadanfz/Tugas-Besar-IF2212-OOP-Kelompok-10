package src.Thing;

import src.Sim;

public class Lukisan extends Thing {
    public Lukisan() {
        // ngasal karena objeknya ga ada di spesifikasi
        super("Lukisan", 1, 1, 100);
    }

    public void mengamati(Sim sim) {
        // untuk setiap 20 detik (ngasal juga)
        sim.changeMood(20);
    }
}
