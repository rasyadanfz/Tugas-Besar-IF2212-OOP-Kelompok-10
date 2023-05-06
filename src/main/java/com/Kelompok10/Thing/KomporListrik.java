package src.main.java.com.Kelompok10.Thing;

import src.main.java.com.Kelompok10.*;

public class KomporListrik extends Kompor {
    public KomporListrik(String kodeItem) {
        super("Kompor Listrik", kodeItem, 1, 1, 200);
    }

    public KomporListrik() {
        super("Kompor Listrik", 1, 1, 200);
    }

    public void Cooking(Sim sim, Food food) {
    }

    public void effect(Sim sim, int duration) {
    }

    public void buyItem(Sim sim) {
    }
}