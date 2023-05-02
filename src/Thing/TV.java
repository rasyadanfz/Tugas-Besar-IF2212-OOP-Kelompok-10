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
        while (action.getDurationLeft() > 0) {
            sim.decreaseActionDuration(action);
        }
    }
}
