package src.Thing;

import src.*;

public class KomporGas extends Kompor {
    public KomporGas(String kodeItem) {
        super("Kompor Gas", kodeItem, 2, 1, 100);
    }

    public KomporGas() {
        super("Kompor Gas", 2, 1, 100);
    }

    public void Cooking(Sim sim, Food food) {
    }

    public void effect(Sim sim, int duration) {
    }

    public void buyItem(Sim sim) {
    }
}