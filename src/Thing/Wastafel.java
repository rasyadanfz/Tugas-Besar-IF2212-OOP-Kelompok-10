package src.Thing;
import src.Sim;

public class Wastafel extends Thing {
    public Wastafel(String kodeItem){
        super("Wastafel", kodeItem, 1, 1, 40);
    }

    public void cuciTangan(Sim sim, int duration){
        sim.addAction(new Action("eating", duration));
        sim.setStatus("active");
        while (duration>0) {
        sim.changeKesehatan(10);
        duration--;
        }
    }
}