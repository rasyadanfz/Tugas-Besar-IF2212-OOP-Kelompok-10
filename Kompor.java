package src.Thing;
import src.sim;

public abstract class Kompor extends Thing implements Cooking {
    public Kompor(String nama, int panjang, int lebar, int harga){
        super(nama, panjang, lebar, harga);
    }
    public void Cooking(Sim sim){
        int waktu;
        waktu = 1.5 * nama.getKekenyangan();
        sim.setMood((sim.getMood()) + 10);
    }
}

