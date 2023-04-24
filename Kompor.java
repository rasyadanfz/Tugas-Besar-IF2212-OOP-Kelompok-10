package src.Thing;
import src.Sim;

public abstract class Kompor extends Thing implements Cooking {
    public Kompor(String nama, int panjang, int lebar, int harga){
        super(nama, panjang, lebar, harga);
    }
    public void Cooking(Sim sim, Food food){
        int waktu;
        waktu = 1.5 * food.getKekenyangan();
        sim.setMood((sim.getMood()) + 10);
    }
}

