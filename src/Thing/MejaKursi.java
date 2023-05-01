package src.Thing;

import src.*;

public class MejaKursi extends Thing {
    public MejaKursi(String kodeItem) {
        super("Meja Dan Kursi", kodeItem, 3, 3, 50);
    }

    public MejaKursi() {
        super("Meja Dan Kursi", 3, 3, 50);
    }

    public void makan(Sim sim, Food food) {
        sim.changeKekenyangan(food.getKekenyangan());
    }

    public void buyItem(Sim sim) {
    }
}