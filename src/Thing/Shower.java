package src.Thing;

import src.Sim;

public class Shower extends Thing {
    public Shower(String kodeItem) {
        // agak ngasal karena ga ada objeknya di spesifikasi
        super("Shower", kodeItem, 1, 1, 50);
    }

    public void mandi(Sim sim) {
        // agak ngasal juga
        // untuk setiap 10 detik
        sim.changeKesehatan(20);
        sim.changeMood(10);
    }
}
