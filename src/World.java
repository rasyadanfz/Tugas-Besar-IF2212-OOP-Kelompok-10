package src;
import java.util.*;

import src.Exceptions.HouseNotFoundException;

public class World {
    private Matrix map;
    private ArrayList<House> daftarRumah;
    private int day;
    private int time; //detik
    
    private static World world = new World();

    // Design Pattern Singleton : membuat konstruktor menjadi private
    private World() {
        map = new Matrix(64, 64);
        daftarRumah = new ArrayList<House>();
        day = 0;
        time = 720;
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

    public int getDay(){
        return day;
    }

    public int getTime(){
        return time;
    }

    public void addDay(int inc){
        day += inc;
    }

    public void decreaseTime(int dec){
        if (time - dec < 0){
            dec -= time;
            addDay(1);
            time = 720;
        }
        time -= dec;
    }

    public void addHouse(int x, int y, String kodeRumah) throws Exception {
        if (map.getWorldItem(x, y).equals("---")) {
            House rumah = new House(kodeRumah, x, y);
            map.changeWorldItem(x, y, kodeRumah);
            daftarRumah.add(rumah);
        } else {
            throw new Exception("Gagal menambahkan rumah! Lokasi (" + x + ", " + y + ") sudah diisi oleh rumah lain!");
        }
    }

    public House getHouse(String kodeRumah) throws HouseNotFoundException{
        Iterator<House> iterator = daftarRumah.iterator();
        House targetHouse;
        // Kalau ada rumah dengan kode yang ditentukan, return reference to Rumah itu
        // else, throw HouseNotFoundException
        while (iterator.hasNext()){
            targetHouse = iterator.next();
            if (targetHouse.getKodeRumah().equals(kodeRumah)){
                return targetHouse;
            }
        }
        throw new HouseNotFoundException("Rumah dengan kode " + kodeRumah + " tidak ada!");
        
    }
}