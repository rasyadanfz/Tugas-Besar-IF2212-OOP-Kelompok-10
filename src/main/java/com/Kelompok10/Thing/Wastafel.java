package com.Kelompok10.Thing;

import com.Kelompok10.Action;
import com.Kelompok10.Sim;
import com.Kelompok10.Exceptions.HouseIsGoneException;

public class Wastafel extends ActiveItems {
    public Wastafel(String kodeItem) {
        super("Wastafel", kodeItem, 1, 1, 40);
    }

    public Wastafel() {
        super("Wastafel", 1, 1, 40);
    }

    public void effect(Sim sim, Action action) throws HouseIsGoneException {
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
                            "Rumah tempat sim " + sim.getNamaLengkap()
                                    + " cuci tangan hilang karena pemiliknya mati :(");
                }
            }
        }
        System.out.println();
        sim.changeKesehatan(5);
    }

    public void cuciTangan(Sim sim) {
        Action actionCuciTangan = new Action("washingHand", 5, this);
        sim.addAction(actionCuciTangan);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        try {
            effect(sim, actionCuciTangan);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sim.setStatus("idle");
            sim.setInActiveAction(false);
            sim.removeAction(actionCuciTangan);
        }
    }
}