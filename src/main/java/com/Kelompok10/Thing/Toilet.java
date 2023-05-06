package com.Kelompok10.Thing;

import com.Kelompok10.Action;
import com.Kelompok10.Sim;
import com.Kelompok10.Exceptions.DurationNotValidException;
import com.Kelompok10.Exceptions.HouseIsGoneException;
import com.Kelompok10.Exceptions.SimIsDeadException;

public class Toilet extends ActiveItems {
    public Toilet(String kodeItem) {
        super("Toilet", kodeItem, 1, 1, 50);
    }

    public Toilet() {
        super("Toilet", 1, 1, 50);
    }

    public void buangAir(Sim sim, int duration) {
        try {
            if (duration % 10 == 0) {
                Action actionBuangAir = new Action("pee", duration, this);
                sim.addAction(actionBuangAir);
                sim.setStatus("active");
                sim.setInActiveAction(true);
                try {
                    effect(sim, actionBuangAir);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sim.setStatus("idle");
                    sim.setInActiveAction(false);
                    sim.removeAction(actionBuangAir);
                }
            } else {
                throw new DurationNotValidException(10);
            }
        } catch (DurationNotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void effect(Sim sim, Action action) throws HouseIsGoneException, SimIsDeadException {
        int counter = 0;
        boolean keepRunning = true;
        System.out.print("Sisa durasi: ");
        while (action.getDurationLeft() > 0 && keepRunning) {
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
            if (sim.checkKondisiSimMati()) {
                keepRunning = false;
                throw new SimIsDeadException(
                        "Sim " + sim.getNamaLengkap() + " mati karena kelaparan saat buang air :(");
            } else if (action.getActionObject() != null) {
                sim.decreaseActionDuration(action);
                counter++;
                if (counter % 10 == 0) {
                    sim.changeMood(10);
                    sim.changeKekenyangan(-20);
                }
            } else {
                if (sim.getCurrentHouse() == null) {
                    keepRunning = false;
                    throw new HouseIsGoneException(
                            "Rumah tempat sim " + sim.getNamaLengkap() + " buang air hilang karena pemiliknya mati :(");
                }
            }
        }
        System.out.println();
    }

}
