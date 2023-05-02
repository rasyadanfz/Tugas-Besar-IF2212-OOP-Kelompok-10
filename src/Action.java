package src;

import src.Thing.ActiveItems;

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

    public ActiveItems getActionObject() {
        return actionObject;
    }
}
