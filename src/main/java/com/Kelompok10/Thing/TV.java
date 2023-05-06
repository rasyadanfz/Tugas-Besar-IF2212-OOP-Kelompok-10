package com.Kelompok10.Thing;

import com.Kelompok10.Sim;
import com.Kelompok10.Exceptions.*;
import com.Kelompok10.Action;

public class TV extends ActiveItems {
    public TV(String kodeItem) {
        super("TV", kodeItem, 2, 1, 200);
        // ini agak ngasal ya panjang, lebar, sm harganya, soalnya ini objek bikinan
        // sendiri
    }

    public TV() {
        super("TV", 2, 1, 200);
        // ini agak ngasal ya panjang, lebar, sm harganya, soalnya ini objek bikinan
        // sendiri
    }

    public void nontonTV(Sim sim, int duration) {
        // ini juga agak ngasal ya
        // validasi durasi
        try {
            if (duration % 30 == 0) {
                Action actionNontonTV = new Action("nontonTV", duration, this);
                sim.addAction(actionNontonTV);
                sim.setStatus("active");
                sim.setInActiveAction(true);
                try {
                    effect(sim, actionNontonTV);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sim.setStatus("idle");
                    sim.setInActiveAction(false);
                    sim.removeAction(actionNontonTV);
                }
            } else {
                throw new DurationNotValidException(30);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void effect(Sim sim, Action action) throws SimIsDeadException, HouseIsGoneException {
        boolean keepRunning = true;
        int counter = 0;
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
                        "Sim " + sim.getNamaLengkap() + " mati karena sakit saat nonton TV :(");
            } else if (action.getActionObject() != null) {
                sim.decreaseActionDuration(action);
                counter++;
                if (counter % 30 == 0) {
                    sim.changeKesehatan(-10);
                    sim.changeMood(20);
                }
            } else {
                if (sim.getCurrentHouse() == null) {
                    keepRunning = false;
                    throw new HouseIsGoneException(
                            "Rumah tempat sim " + sim.getNamaLengkap() + " nonton TV hilang karena pemiliknya mati :(");
                }
            }
        }
        System.out.println();
    }
}
