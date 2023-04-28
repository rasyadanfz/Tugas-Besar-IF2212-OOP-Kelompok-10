package src.Thing;
import src.*;

public class Cermin extends Thing {
    public Cermin() {
        super("Cermin", 2, 2, 50);
    }

    public void bercermin(Sim sim){
        sim.changeMood(15);
    }

    public void buyItem(Sim sim) {}
}