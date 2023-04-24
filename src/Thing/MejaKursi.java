package src.Thing;
import src.*;

public class MejaKursi extends Thing {
    public MejaKursi(){
        super("Meja dan Kursi", 3, 3, 50);
    }
    
    public void makan(Sim sim, Food food){
        sim.changeKekenyangan(food.getKekenyangan());
    }

    public void buyItem(Sim sim) {}
}