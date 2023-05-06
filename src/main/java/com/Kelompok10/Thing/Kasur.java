package com.Kelompok10.Thing;

import com.Kelompok10.Action;
import com.Kelompok10.Sim;
import com.Kelompok10.Exceptions.*;

public class Kasur extends ActiveItems implements Sleep {
    public Kasur(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public Kasur(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Sleeping(Sim sim, int duration) {
        try {
            if (duration % 240 == 0) {
                Action actionSleep = new Action("sleeping", duration, this);
                sim.addAction(actionSleep);
                sim.setStatus("active");
                sim.setInActiveAction(true);
                try {
                    effect(sim, actionSleep);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sim.setStatus("idle");
                    sim.setInActiveAction(false);
                    sim.removeAction(actionSleep);
                }
            } else {
                throw new DurationNotValidException(240);
            }
        } catch (DurationNotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void effect(Sim sim, Action action) throws HouseIsGoneException {
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
            if (action.getActionObject() != null) {
                sim.decreaseActionDuration(action);
                counter++;
                if (counter % 240 == 0) {
                    sim.changeMood(30);
                    sim.changeKesehatan(20);
                }
            } else {
                if (sim.getCurrentHouse() == null) {
                    keepRunning = false;
                    throw new HouseIsGoneException(
                            "Rumah tempat sim " + sim.getNamaLengkap() + " tidur hilang karena pemiliknya mati :(");
                }
            }
        }
        sim.setInActiveAction(false);
        System.out.println();
    }
}
