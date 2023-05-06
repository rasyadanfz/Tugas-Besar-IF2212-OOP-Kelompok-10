package com.Kelompok10.Thing;

import com.Kelompok10.Sim;
import com.Kelompok10.Exceptions.*;
import com.Kelompok10.Action;

public class Lukisan extends ActiveItems {
    public Lukisan(String kodeItem) {
        // ngasal karena objeknya ga ada di spesifikasi
        super("Lukisan", kodeItem, 1, 1, 100);
    }

    public Lukisan() {
        // ngasal karena objeknya ga ada di spesifikasi
        super("Lukisan", 1, 1, 100);
    }

    public void lihatLukisan(Sim sim, int duration) {
        // untuk setiap 20 detik (ngasal juga)
        try {
            if (duration % 20 == 0) {
                Action actionLihatLukisan = new Action("lihatLukisan", duration, this);
                sim.addAction(actionLihatLukisan);
                sim.setStatus("active");
                try {
                    effect(sim, actionLihatLukisan);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sim.setStatus("idle");
                    sim.setInActiveAction(false);
                    sim.removeAction(actionLihatLukisan);
                }
            } else {
                throw new DurationNotValidException(20);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void effect(Sim sim, Action action) throws SimIsDeadException, HouseIsGoneException {
        int counter = 0;
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
            if (sim.checkKondisiSimMati()) {
                keepRunning = false;
                throw new SimIsDeadException(
                        "Sim " + sim.getNamaLengkap() + " mati karena sakit saat melihat lukisan :(");
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
                            "Rumah tempat sim " + sim.getNamaLengkap()
                                    + " melihat lukisan hilang karena pemiliknya mati :(");
                }
            }
        }
        System.out.println();
    }
}
