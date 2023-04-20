package src.Thing;

import src.Sim;

public class TV extends Thing {
    public TV() {
        super("TV", 2, 1, 200);
        // ini agak ngasal ya panjang, lebar, sm harganya, soalnya ini objek bikinan
        // sendiri
    }

    public void nontonTV(Sim sim) {
        // ini juga agak ngasal ya
        sim.changeKesehatan(-10);
        sim.changeMood(20);
    }
}
