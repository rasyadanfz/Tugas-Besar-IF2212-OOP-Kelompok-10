package src.Thing;

import src.Sim;
import src.Exceptions.DurationNotValidException;
import src.Action;

public class TV extends Thing {
    public TV(String kodeItem) {
        super("TV", kodeItem, 2, 1, 200);
        // ini agak ngasal ya panjang, lebar, sm harganya, soalnya ini objek bikinan
        // sendiri
    }

    public void nontonTV(Sim sim, int duration) {
        // ini juga agak ngasal ya
        // validasi durasi
        try {
            if (duration % 30 == 0) {
                sim.addAction(new Action("nontonTV", duration));
                sim.setStatus("active");
                int x = duration / 30;
                for (int i = 0; i < x; i++) {
                    sim.changeKesehatan(-10);
                    sim.changeMood(20);
                }
            } else {
                throw new DurationNotValidException(30);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
