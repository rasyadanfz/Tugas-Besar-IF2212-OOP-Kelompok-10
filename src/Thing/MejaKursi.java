package src.Thing;
import src.*;

public class MejaKursi extends Thing {
    public MejaKursi(String kodeItem){
        super("Meja dan Kursi", kodeItem, 3, 3, 50);
    }
    
    public void makan(Sim sim, Food food){
        sim.changeKekenyangan(food.getKekenyangan());
    }

    public void buyItem(Sim sim) {}
}