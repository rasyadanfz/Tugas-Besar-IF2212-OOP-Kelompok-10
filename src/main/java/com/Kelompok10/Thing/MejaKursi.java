package com.Kelompok10.Thing;

import com.Kelompok10.*;
import com.Kelompok10.Exceptions.HouseIsGoneException;

public class MejaKursi extends ActiveItems {
    public MejaKursi(String kodeItem) {
        super("Meja Dan Kursi", kodeItem, 3, 3, 50);
    }

    public MejaKursi() {
        super("Meja Dan Kursi", 3, 3, 50);
    }

    public <T extends Item> void makan(Sim sim, T food) {
        Action actionMakan = new Action("eating", 30, this);
        sim.addAction(actionMakan);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        try {
            effect(sim, actionMakan, food);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sim.setStatus("idle");
            sim.setInActiveAction(false);
            sim.removeAction(actionMakan);
        }
        sim.getInventory().removeItem(food.getNama());
    }

    public <T extends Item> void effect(Sim sim, Action action, T food) throws HouseIsGoneException {
        boolean keepRunning = true;
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
            if (action.getActionObject() != null) {
                sim.decreaseActionDuration(action);
            } else {
                if (sim.getCurrentHouse() == null) {
                    keepRunning = false;
                    throw new HouseIsGoneException(
                            "Rumah tempat sim " + sim.getNamaLengkap() + " makan hilang karena pemiliknya mati :(");
                }
            }
        }
        System.out.println();
        if (food instanceof Food) {
            sim.changeKekenyangan(((Food) food).getKekenyangan());
        } else if (food instanceof Ingredient) {
            sim.changeKekenyangan(((Ingredient) food).getKekenyangan());
        }
    }

}