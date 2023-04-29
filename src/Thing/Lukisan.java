package src.Thing;

import src.Sim;

public class Lukisan extends Thing {
    public Lukisan(String kodeItem) {
        // ngasal karena objeknya ga ada di spesifikasi
        super("Lukisan", kodeItem, 1, 1, 100);
    }

    public void lihatLukisan(Sim sim) {
        // untuk setiap 20 detik (ngasal juga)
        sim.changeMood(20);
    }
}
