package com.Kelompok10.Thing;

import com.Kelompok10.*;

public class MejaKursi extends ActiveItems {
    public MejaKursi(String kodeItem) {
        super("Meja Dan Kursi", kodeItem, 3, 3, 50);
    }

    public MejaKursi() {
        super("Meja Dan Kursi", 3, 3, 50);
    }

    public <T extends Item & Eatable> void makan(Sim sim, T food) {
        Action actionMakan = new Action("eating", 30, this);
        sim.addAction(actionMakan);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionMakan, food);
        sim.getInventory().removeItem(food.getNama());
    }

    public <T extends Item & Eatable> void effect(Sim sim, Action action, T food) {

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
            try {
                sim.decreaseActionDuration(action);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        System.out.println();
    }

}