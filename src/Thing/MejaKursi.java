package src.Thing;

import src.*;

public class MejaKursi extends ActiveItems {
    public MejaKursi(String kodeItem) {
        super("Meja Dan Kursi", kodeItem, 3, 3, 50);
    }

    public MejaKursi() {
        super("Meja Dan Kursi", 3, 3, 50);
    }

    public void makan(Sim sim, Food food, int duration) {
        sim.addAction(new Action("eating", duration, this));
        sim.setStatus("active");
        sim.setInActiveAction(true);
    }

    public void effect(Sim sim, int duration) {
    }

    public void effect(Sim sim, int duration, Food food) {
        while (duration > 0) {
            // setiap datu siklus makan 30 detik
            sim.changeKekenyangan(food.getKekenyangan());
            duration -= 30;
        }

    }

}