package src;

public class Timer {
    private int time;
    private static Timer timer;

    private Timer(){
        time = 0;
    }

    public static synchronized Timer getTimer(){
        if (timer == null){
            timer = new Timer();
        }
        return timer;
    }

    public int getTime(){
        return time;
    }

    public void addTime(int add){
        time += add;
    }

    public void reduceTime(int subtractor){
        if (time - subtractor < 0){
            time = 0;
        }
        else{
            time -= subtractor;
        }
    }
}
