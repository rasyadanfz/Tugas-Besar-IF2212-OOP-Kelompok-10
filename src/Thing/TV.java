package src.Thing;

import src.Sim;
import src.Action;

public class TV extends Thing {
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

        sim.addAction(new Action("sleeping", duration));
        sim.setStatus("active");
        while (duration > 0) {
            // setiap 1 menit:
            sim.changeKesehatan(-10);
            sim.changeMood(20);
            duration--;
        }
    }
}
