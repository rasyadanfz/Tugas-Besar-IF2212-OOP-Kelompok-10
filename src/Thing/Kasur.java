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
        Action actionSleep = new Action("sleeping", duration, this);
        sim.addAction(actionSleep);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionSleep);
    }

    public void effect(Sim sim, Action action) {
        int duration = action.getOriginalDuration();
        try {
            if (duration % 240 == 0) {
                int x = duration / 40;
                for (int i = 0; i < x; i++) {
                    sim.changeMood(30);
                    sim.changeKesehatan(20);
                }
            } else {
                throw new DurationNotValidException(40);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while (action.getDurationLeft() > 0) {
            sim.decreaseActionDuration(action);
        }
    }
}
