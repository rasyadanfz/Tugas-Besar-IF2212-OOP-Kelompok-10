package src.Thing;

import src.*;

public class MejaKursi extends ActiveItems {
    public MejaKursi(String kodeItem) {
        super("Meja Dan Kursi", kodeItem, 3, 3, 50);
    }

    public MejaKursi() {
        super("Meja Dan Kursi", 3, 3, 50);
    }

    public void makan(Sim sim, Food food) {
        Action actionMakan = new Action("eating", 30, this);
        sim.addAction(actionMakan);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionMakan);
    }

    public void effect(Sim sim, Action action) {
    }

    public void effect(Sim sim, Action action, Food food) {
        System.out.print("Sisa durasi: ");
        while (action.getDurationLeft() > 0) {
            int printDuration = action.getDurationLeft() - 1;
            if (printDuration < 10) {
                System.out.print("00" + printDuration);
            } else if (printDuration < 100) {
                System.out.print("0" + printDuration);
            } else {
                System.out.print(printDuration);
            }
            if (printDuration != 0) {
                System.out.print("\b\b\b");
            }
            sim.decreaseActionDuration(action);
        }
        System.out.println();
        sim.changeKekenyangan(food.getKekenyangan());
    }

}