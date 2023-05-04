package src.Thing;

import src.Sim;
import src.Exceptions.DurationNotValidException;
import src.Action;

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
                effect(sim, actionLihatLukisan);
            } else {
                throw new DurationNotValidException(20);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void effect(Sim sim, Action action) {
        int duration = action.getOriginalDuration();
        int x = duration / 20;
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
