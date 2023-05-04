package src;

public class Timer {
    private int time;
    private int day;
    private static Timer timer = new Timer();

    private Timer() {
        day = 1;
        time = 0;
    }

    public synchronized int getTotalTime() {
        return ((day - 1) * 720) + time;
    }

    public static synchronized Timer getTimer() {
        return timer;
    }

    public synchronized int getDay() {
        return day;
    }

    public synchronized int getTime() {
        return time;
    }

    public synchronized void increaseTime() {
        time++;
        if (time == 720) {
            changeDay();
            time = 0;
        }
    }

    public synchronized void changeDay() {
        day++;
    }

}
