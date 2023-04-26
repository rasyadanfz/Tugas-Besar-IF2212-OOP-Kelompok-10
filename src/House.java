package src;
import java.util.*;

public class House {
    private String kodeRumah;
    private Matrix petaRumah = new Matrix(9, 9);
    private ArrayList<Room> daftarRuangan;
    private Point lokasi;
    // private Matrix houseSpace;

    public House(String kodeRumah, int x, int y) {
        this.kodeRumah = kodeRumah;
        daftarRuangan = new ArrayList<>();
        lokasi = new Point(x, y);

        //SetDefault setiap kali beli rumah 
        Room ruang1 = new Room("R1", this);       
        petaRumah.changeItem(5, 5, ruang1.getNamaRuangan());
    }

    public String getKodeRumah() {
        return kodeRumah;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public Matrix getPetaRumah() {
        return petaRumah;
    }

    public void printPetaRumah() {
        petaRumah.printMatrix();
    }

    public ArrayList<Room> getSeluruhRuangan() {
        return daftarRuangan;
    }

    // public Matrix getHouseSpace(){
    //     return houseSpace;
    // }

    // public void viewDenahRuang(){
    //     houseSpace.printMatrix();
    // }

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
            if ((y != 0) && (ruang.getHouse().getPetaRumah().getItem(x, y-1) == "-")){
                avail = true;
            }
        }
        else if (arah == "bawah") {
            //Jika ruangan tersebut mentok di bawah
            if ((y != 8) && (ruang.getHouse().getPetaRumah().getItem(x, y-1) == "-")){
                avail = true;
            }
        }
        else if (arah == "kanan"){
            //Jika ruangan tersebut mentok di kanan
            if ((x != 8) && (ruang.getHouse().getPetaRumah().getItem(x, y-1) == "-")){
                avail = true;
            }
        }
        else if (arah == "kiri"){
            //Jika ruangan tersebut mentok di kiri
            if ((x != 0) && (ruang.getHouse().getPetaRumah().getItem(x, y-1) == "-")){
                avail = true;
            }
        }
        return avail;
    }

    public static void main(String[] args) {
        House rumah1 = new House("H1", 1, 1);
        rumah1.printPetaRumah();

        // Room ruang2 = new Room("R2", rumah1);

        // System.out.println(rumah1.checkSpace(ruang1, "kiri"));
    }
}
