package src.Thing;

import src.Sim;
import src.Exceptions.DurationNotValidException;
import src.Action;

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
                effect(sim, actionNontonTV);
            } else {
                throw new DurationNotValidException(30);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void effect(Sim sim, Action action) {
        int duration = action.getOriginalDuration();
        int x = duration / 30;
        for (int i = 0; i < x; i++) {
            sim.changeKesehatan(-10);
            sim.changeMood(20);
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
