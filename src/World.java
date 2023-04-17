package src;
import java.util.*;
import java.awt.Point;

public class World {
    private Matrix map;
    private ArrayList<House> daftarRumah;
    
    private static World world = new World();

    private World() {
        map = new Matrix(64, 64);
        daftarRumah = new ArrayList<>();
    }
    
    public static World getWorld() {
        return world;
    }
}