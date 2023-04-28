package src.Thing;
import src.Sim;

public class Toilet extends Thing {
    public Toilet(String kodeItem){
        super("Toilet", kodeItem, 1, 1, 50);
    }
    
    public void buangAir(Sim sim){
        sim.changeMood(10);
        // masih bingung
        sim.changeKekenyangan();
    }

    public void buyItem(Sim sim) {}
}