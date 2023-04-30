package src.Thing;
import src.Sim;

public class Wastafel extends Thing {
    public Wastafel(String kodeItem){
        super("Wastafel", kodeItem, 1, 1, 40);
    }

    public void cuciTangan(Sim sim){
        sim.changeKesehatan(10);
    }

   
}