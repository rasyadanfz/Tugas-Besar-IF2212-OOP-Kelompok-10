package src;

public class Timer {
    private int time;
    private int day;
    private static Timer timer = new Timer();

    private Timer() {
        day = 1;
        time = 720;
    }

    public static Timer getTimer() {
        return timer;
    }

    public int getTime() {
        return time;
    }

    // Reduce Time
    public void reduceTime() {
        time--;
        if (time == 0) {
            changeDay();
            time = 720;
        }
    }

    public void reduceTime(int subtractor, GameManager game) {
        for (int i = 0; i < subtractor; i++) {
            try {
                if (game.getIsCheatEnabled()) {
                    Thread.sleep(100);
                } else {
                    Thread.sleep(1000); // Tunggu 1 detik
                }
                // Kurangi setiap aksi yang ada di setiap sim dengan 1 detik
                for (int j = 0; i < game.getSimList().size(); i++) {
                    if (!game.getSimList().get(j).getActionList().isEmpty()) {
                        for (int k = 0; k < game.getSimList().get(j).getActionList().size(); k++) {
                            game.getSimList().get(j)
                                    .decreaseActionDuration(game.getSimList().get(j).getActionList().get(k));
                        }
                    }
                }
                reduceTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void changeDay() {
        day++;
        time = 720;
    }

}
