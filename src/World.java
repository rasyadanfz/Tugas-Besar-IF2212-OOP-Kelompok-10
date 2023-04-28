package src;
import java.util.*;

import src.Exceptions.HouseNotFoundException;

public class World {
    private Matrix map;
    private ArrayList<House> daftarRumah;
    
    private static World world = new World();

    // Design Pattern Singleton : membuat konstruktur menjadi private
    private World() {
        map = new Matrix(64, 64);
        daftarRumah = new ArrayList<House>();
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

    public void addHouse(int x, int y, String kodeRumah) throws Exception {
        if (map.getWorldItem(x, y).equals("--")) {
            House rumah = new House(kodeRumah, x, y);
            map.changeWorldItem(x, y, kodeRumah);
            daftarRumah.add(rumah);
        } else {
            throw new Exception("Gagal menambahkan rumah! Lokasi (" + x + ", " + y + ") sudah diisi oleh rumah lain!");
        }
    }

    public House getHouse(String kodeRumah) throws HouseNotFoundException{
        Iterator<House> iterator = daftarRumah.iterator();
        // Kalau ada rumah dengan kode yang ditentukan, return reference to Rumah itu
        // else, throw HouseNotFoundException
        while (iterator.hasNext()){
            if (iterator.next().getKodeRumah().equals(kodeRumah)){
                return iterator.next();
            }
        }
        throw new HouseNotFoundException("Rumah dengan kode " + kodeRumah + " tidak ada!");
        
    }
}