package src;

import java.util.ArrayList;

public class GameManager {
    private World world;
    private ArrayList<Sim> simList;
    private int time;
    private int hari;
    private ArrayList<String> listOfActions;
    private Sim activeSim;

    public GameManager(){
        world = World.getWorld();
        simList = new ArrayList<Sim>();
        hari = 0;
        listOfActions = new ArrayList<String>();
    }

    public World getWorld(){
        return world;
    }
    
    public ArrayList<Sim> getSimList(){
        return simList;
    }

    public int getHari(){
        return hari;
    }

    public ArrayList<String> getListOfActions(){
        return listOfActions;
    }

    public Sim getActiveSim(){
        return activeSim;
    }

    public int getTime(){
        return time;
    }

    public void printSimList(){
        int i = 1;
        System.out.println("Daftar Sim dalam Game: ");
        for (Sim s : simList){
            System.out.println(i + ". " + s.getNamaLengkap());
        }
    }

    // Setter
    public void setActiveSim(Sim sim){
        activeSim = sim;
    }

    public void setHari(int hari){
        this.hari = hari;
    }

}
