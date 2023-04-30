package src.Thing;

import src.Action;
import src.Sim;

public abstract class Kasur extends Thing implements Sleep {
    public Kasur(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public void Sleeping(Sim sim, int duration) {
        sim.addAction(new Action("sleeping", duration));
        sim.setStatus("active");
    }

    public void sleepEffect(Sim sim, int duration){
        while (duration > 0){
            // Setiap 4 menit :
            sim.changeMood(30);
            sim.changeKesehatan(20);
            duration -= 240;
        }
    }
}
