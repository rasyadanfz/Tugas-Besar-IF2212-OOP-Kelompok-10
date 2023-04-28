package src.Thing;

import src.Sim;

public class TV extends Thing {
    public TV(String kodeItem) {
        super("TV", kodeItem, 2, 1, 200);
        // ini agak ngasal ya panjang, lebar, sm harganya, soalnya ini objek bikinan
        // sendiri
    }

    public void nontonTV(Sim sim) {
        // ini juga agak ngasal ya
        // setiap 1 menit:
        sim.changeKesehatan(-10);
        sim.changeMood(20);
    }
}
