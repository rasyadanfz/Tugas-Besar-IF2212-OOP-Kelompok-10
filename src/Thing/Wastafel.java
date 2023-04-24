package src.Thing;
import src.Sim;

public class Wastafel extends Thing {
    public Wastafel(){
        super("Wastafel", 1, 1, 40);
    }

    public void cuciTangan(Sim sim){
        sim.changeKesehatan(10);
    }

    public void buyItem(Sim sim) {}
}