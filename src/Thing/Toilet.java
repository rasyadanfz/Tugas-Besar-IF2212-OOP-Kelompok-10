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
            if (action.getDurationLeft() < 10) {
                System.out.print("00" + action.getDurationLeft());
            } else if (action.getDurationLeft() < 100) {
                System.out.print("0" + action.getDurationLeft());
            } else {
                System.out.print(action.getDurationLeft());
            }
            System.out.print("\b\b\b");
            sim.decreaseActionDuration(action);
            if (action.getDurationLeft() == 0) {
                System.out.println(000);
            }
        }
        sim.changeMood(10);
        sim.changeKekenyangan(-20);
    }

}
