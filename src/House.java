package src;
import java.util.*;

public class House {
    private String kodeRumah;
    private ArrayList<Room> daftarRuangan;
    private Point lokasi;
    private Matrix houseSpace;

    public House(String kodeRumah) {
        this.kodeRumah = kodeRumah;
        daftarRuangan = new ArrayList<>();

        //SetDefault setiap kali beli rumah
        houseSpace = new Matrix(9, 9); 
        houseSpace.changeItem(4, 4, "Ruang Utama");
    }

    public String getKodeRumah() {
        return kodeRumah;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public ArrayList<Room> getSeluruhRuangan() {
        return daftarRuangan;
    }

    public Matrix getHouseSpace(){
        return houseSpace;
    }

    public void viewDenahRuang(){
        houseSpace.printMatrix();
    }

    public void addRuang(Room ruang, String arah){
        //Masih bingung
    }

    public boolean checkSpace(Room ruang, String arah){
            boolean avail = false;
            int x = ruang.getRoomPosition().getX();
            int y = ruang.getRoomPosition().getY();
            //Cek dalam empat arah
            if (arah == "atas") {
                //Jika ruangan tersebut mentok di atas
                if ((y != 0) && (ruang.getHouse().getHouseSpace().getItem(x, y-1) == "-")){
                    avail = true;
                }
            }
            else if (arah == "bawah") {
                //Jika ruangan tersebut mentok di bawah
                if ((y != 8) && (ruang.getHouse().getHouseSpace().getItem(x, y-1) == "-")){
                    avail = true;
                }
            }
            else if (arah == "kanan"){
                //Jika ruangan tersebut mentok di kanan
                if ((x != 8) && (ruang.getHouse().getHouseSpace().getItem(x, y-1) == "-")){
                    avail = true;
                }
            }
            else if (arah == "kiri"){
                //Jika ruangan tersebut mentok di kiri
                if ((x != 0) && (ruang.getHouse().getHouseSpace().getItem(x, y-1) == "-")){
                    avail = true;
                }
            }
            return avail;
    }
}
