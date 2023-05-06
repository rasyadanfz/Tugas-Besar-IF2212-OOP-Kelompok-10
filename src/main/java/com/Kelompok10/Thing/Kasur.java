package com.Kelompok10.Thing;

import com.Kelompok10.Action;
import com.Kelompok10.Sim;
import com.Kelompok10.Exceptions.DurationNotValidException;

public abstract class Kasur extends ActiveItems implements Sleep {
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
                effect(sim, actionSleep);
            } else {
                throw new DurationNotValidException(240);
            }
        } catch (DurationNotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void effect(Sim sim, Action action) {
        int duration = action.getOriginalDuration();
        int x = duration / 240;
        for (int i = 0; i < x; i++) {
            sim.changeMood(30);
            sim.changeKesehatan(20);
        }
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
        sim.setInActiveAction(false);
        System.out.println();
    }
}
