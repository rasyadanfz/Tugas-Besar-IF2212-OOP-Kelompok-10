package src.Thing;

import java.util.ArrayList;

import src.*;

public class MejaKursi extends ActiveItems {
    public MejaKursi(String kodeItem) {
        super("Meja Dan Kursi", kodeItem, 3, 3, 50);
    }

    public MejaKursi() {
        super("Meja Dan Kursi", 3, 3, 50);
    }

    public void makan(Sim sim, Food food) {
        sim.addAction(new Action("eating", 30, this));
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, 30, food);
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