public class Sim {
    private String namaLengkap;
    private Job pekerjaan;
    private Boolean justChangedJob;
    private int uang;
    private Inventory penyimpanan;
    private int kekenyangan;
    private int kesehatan;
    private int mood;
    private String status;
    private Room currentRuangan;
    private House currentHouse;
    private Point currentPos;

    //Konstruktor
    public Sim(String namaLengkap){
        this.namaLengkap = namaLengkap;
        uang = 100;
        kekenyangan = 80;
        kesehatan = 80;
        mood = 80;
        pekerjaan = getJob();
    }

    //Getter
    public int getKekenyangan(){
        return kekenyangan;
    }

    public int getKesehatan(){
        return kesehatan;
    }

    public int getMood(){
        return mood;
    }

    public String getNamaLengkap(){
        return namaLengkap;
    }

    public int getUang(){
        return uang;
    }

    public String getStatus(){
        return status;
    }

    public House getCurrentHouse(){
        return currentHouse;
    }

    public Room getCurrentRuangan(){
        return currentRuangan;
    }

    public Point getCurrentPos(){
        return currentPos;
    }

    public boolean getJustChangedJob(){
        return justChangedJob;
    }

    //Setter
    public void changeKekenyangan(int exp) {
        kekenyangan = exp;
    }

    public void changeKesehatan (int exp) {
        kesehatan = exp;
    }

    public void changeMood(int exp) {
        mood = exp;
    }

    public void changeCurrentHouse(House newHouse) {
        currentHouse = newHouse;
    }

    public void changeCurrentRuangan(Room newRoom) {
        currentRuangan = newRoom;
    }

    public void changeCurrentPos(Point newPos) {
        currentPos = newPos;
    }


}