package src;
import java.util.*;

public class World {
    private Matrix map;
    private ArrayList<House> daftarRumah;
    
    private static World world = new World();

    // Design Pattern Singleton : membuat konstruktur menjadi private
    private World() {
        map = new Matrix(64, 64);
        daftarRumah = new ArrayList<>();
    }
    
    public Matrix getMap() {
        return map;
    }

    public ArrayList<House> getDaftarRumah() {
        return daftarRumah;
    }

    public static World getWorld() {
        return world;
    }

    public void addHouse(int x, int y, House house) {
        map.changeItem(x, y, house.getKodeRumah());
        daftarRumah.add(house);
    }
}