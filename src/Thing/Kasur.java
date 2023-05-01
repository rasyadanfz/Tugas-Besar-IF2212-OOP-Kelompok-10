package src.Thing;

import src.Action;
import src.Sim;
import src.Exceptions.DurationNotValidException;

public abstract class Kasur extends Thing implements Sleep {
    public Kasur(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public Kasur(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Sleeping(Sim sim, int duration) {
        sim.addAction(new Action("sleeping", duration));
        sim.setStatus("active");
        sleepEffect(sim, duration);
    }

    public void sleepEffect(Sim sim, int duration) {
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
    }
}
