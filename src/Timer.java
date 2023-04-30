package src;

public class Timer {
    private int time;
    private int day;
    private static Timer timer = new Timer();

    private Timer(){
        day = 1;
        time = 720;
    }

    public static Timer getTimer(){
        return timer;
    }

    public int getTime(){
        return time;
    }

    // Reduce Time
    public void reduceTime(){
        time--;
        if (time == 0){
            changeDay();
        }
    }

    public void reduceTime(int subtractor, GameManager game){
      for (int i = 0; i < subtractor; i++){
        try{
            Thread.sleep(1000); // Tunggu 1 detik
            // Kurangi setiap aksi yang ada di setiap sim dengan 1 detik
            for (Sim sim : game.getSimList()) { 
                for (Action a: sim.getActionList()){
                    sim.decreaseActionDuration(a);
                }
            }
            reduceTime();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
      }
    }

    public void changeDay(){
        day++;
        time = 720;
    }
    

    
}
