package src.Thing;

import src.Action;
import src.Sim;
import src.Exceptions.DurationNotValidException;

public abstract class Kasur extends ActiveItems implements Sleep {
    public Kasur(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public Kasur(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Sleeping(Sim sim, int duration) {
        try {
            if (duration % 40 == 0) {
                Action actionSleep = new Action("sleeping", duration, this);
                sim.addAction(actionSleep);
                sim.setStatus("active");
                sim.setInActiveAction(true);
                effect(sim, actionSleep);
            } else {
                throw new DurationNotValidException(40);
            }
        } catch (DurationNotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void effect(Sim sim, Action action) {
        int duration = action.getOriginalDuration();
        int x = duration / 40;
        for (int i = 0; i < x; i++) {
            sim.changeMood(30);
            sim.changeKesehatan(20);
        }
        System.out.print("Sisa durasi: ");
        while (action.getDurationLeft() > 0) {
            if (action.getDurationLeft() < 10) {
                System.out.print("00" + action.getDurationLeft());
            } else if (action.getDurationLeft() < 100) {
                System.out.print("0" + action.getDurationLeft());
            } else {
                System.out.print(action.getDurationLeft());
            }
            System.out.print("\b\b\b");
            sim.decreaseActionDuration(action);
            if (action.getDurationLeft() == 0) {
                System.out.print(000);
            }
        }
    }
}
