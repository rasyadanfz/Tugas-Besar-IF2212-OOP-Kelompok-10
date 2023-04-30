package src;

public class Action {
    String actionName;
    int originalDuration;
    int durationLeft;

    public Action(String actionName, int duration){
        this.actionName = actionName;
        originalDuration = duration;
        durationLeft = duration;
    }

    public String getActionName(){
        return actionName;
    }

    public int getDurationLeft(){
        return durationLeft;
    }

    public void decreaseDuration(){
        durationLeft--;
    }
}
