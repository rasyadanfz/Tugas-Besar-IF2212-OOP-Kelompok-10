package src.Thing;
import src.*;

public abstract class Kompor extends Thing implements Cook {
    public Kompor(String nama, String kodeItem, int panjang, int lebar, int harga){
        super(nama, kodeItem, panjang, lebar, harga);
    }
    
    public void Cooking(Sim sim, Food food, int duration){
        sim.addAction(new Action("cooking", duration));
        sim.setStatus("active");
        int duration;
        duration = 1.5 * food.getKekenyangan();
        while (duration > 0) {
        sim.setMood((sim.getMood()) + 10);
        duration--;
    }
}

