package src.Thing;

import src.*;

public abstract class Kompor extends ActiveItems implements Cook {
    public Kompor(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public Kompor(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Cooking(Sim sim, Food food) {
        int duration = (int) (1.5 * food.getKekenyangan());
        sim.addAction(new Action("cooking", duration, this));
        sim.setStatus("active");
        while (duration > 0) {
            sim.changeMood(10);
            duration--;
        }
    }
}