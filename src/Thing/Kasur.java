package src.Thing;
import src.Sim;

public abstract class Kasur extends Thing implements Sleep {
    public Kasur(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Sleeping(Sim sim) {
        // butuh timer buat ngukur 4 menit
        // untuk setiap 4 menit:
        sim.changeMood((sim.getMood()) + 30);
        sim.changeKekenyangan((sim.getKesehatan()) + 20);
    }
}
