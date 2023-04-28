package src.Thing;
import src.*;

public abstract class Kompor extends Thing implements Cook {
    public Kompor(String nama, String kodeItem, int panjang, int lebar, int harga){
        super(nama, kodeItem, panjang, lebar, harga);
    }
    
    public void Cooking(Sim sim, Food food){
        int waktu;
        waktu = 1.5 * food.getKekenyangan();
        sim.setMood((sim.getMood()) + 10);
    }
}

