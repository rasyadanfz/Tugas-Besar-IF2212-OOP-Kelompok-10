package src;

import src.Thing.ActiveItems;
import src.Thing.Thing;

public class Action {
    ActiveItems actionObject;
    String actionName;
    int originalDuration;
    int durationLeft;

    public Action(String actionName, int duration, ActiveItems actionObject) {
        this.actionName = actionName;
        originalDuration = duration;
        durationLeft = duration;
        this.actionObject = actionObject;
    }

    public String getActionName() {
        return actionName;
    }

    public int getDurationLeft() {
        return durationLeft;
    }

    public int getOriginalDuration() {
        return originalDuration;
    }

    public void decreaseDuration() {
        durationLeft--;
    }

    public void decreaseDuration(int besarPengurangan) {
        durationLeft -= besarPengurangan;
    }

    public ActiveItems getActionObject() {
        return actionObject;
    }
}
