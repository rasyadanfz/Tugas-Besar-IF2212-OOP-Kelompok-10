package src.Thing;
import src.Sim;

public class MejaKursi extends Thing{
    public MejaKursi(){
        super("Meja dan Kursi", 3, 3, 50);
    }
    public void makan(Sim sim){
        sim.changeKekenyangan(food.getKekenyangan());
    }
}