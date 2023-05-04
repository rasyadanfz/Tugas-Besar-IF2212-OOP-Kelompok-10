package src.Thing;

import src.Action;
import src.Sim;

public class Toilet extends ActiveItems {
    public Toilet(String kodeItem) {
        super("Toilet", kodeItem, 1, 1, 50);
    }

    public Toilet() {
        super("Toilet", 1, 1, 50);
    }

    public void buangAir(Sim sim, int duration) {
        Action actionBuangAir = new Action("pee", duration, this);
        sim.addAction(actionBuangAir);
        sim.setStatus("active");
        sim.setInActiveAction(true);
        effect(sim, actionBuangAir);
    }

    public void effect(Sim sim, Action action) {
        System.out.print("Sisa durasi: ");
        while (action.getDurationLeft() > 0) {
            int printDuration = action.getDurationLeft() - 1;
            if (printDuration < 10) {
                System.out.print("00" + printDuration);
            } else if (printDuration < 100) {
                System.out.print("0" + printDuration);
            } else {
                System.out.print(printDuration);
            }
            if (printDuration != 0) {
                System.out.print("\b\b\b");
            }
            sim.decreaseActionDuration(action);
        }
        System.out.println();
        sim.changeMood(10);
        sim.changeKekenyangan(-20);
    }

}
