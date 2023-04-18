package src;

import java.util.Random;

public class Sim {
    private String namaLengkap;
    private String status;
    private Job pekerjaan = new Job();
    private boolean justChangedJob;
    private int uang;
    private int kekenyangan;
    private int kesehatan;
    private int mood;
    private Inventory inventory;
    private Room currentRoom;
    private House currentHouse;
    private Point currentPos;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        uang = 100;
        kekenyangan = 80; kesehatan = 80; mood = 80;
        getJob(); // Set pekerjaan Sim secara random
        inventory = new Inventory();
        justChangedJob = false;
    }
    
    // Getter
    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public int getMood() {
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

    public Inventory getInventory(){
        return inventory;
    }

    public House getCurrentHouse(){
        return currentHouse;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public Point getCurrentPos(){
        return currentPos;
    }

    public boolean getJustChangedJob(){
        return justChangedJob;
    }

    public String getPekerjaan() {
        return String.format(pekerjaan.getNamaPekerjaan() + " dengan gaji " + pekerjaan.getGaji());
    }

    // Setter
    public void changeKekenyangan(int num) {
        if (kekenyangan + num > 100){
            kekenyangan = 100;
        }
        else if(kekenyangan + num < 0){
            kekenyangan = 0;
        }
        else{
            kekenyangan += num;
        }
    }

    public void changeKesehatan(int num) {
        if (kesehatan + num > 100){
            kesehatan = 100;
        }
        else if(kesehatan + num < 0){
            kesehatan = 0;
        }
        else{
            kesehatan += num;
        }
    }

    public void changeMood(int num) {
        if (mood + num > 100){
            mood = 100;
        }
        else if(mood + num < 0){
            mood = 0;
        }
        else{
            mood += num;
        }
    }

    public void changeCurrentHouse(House newHouse){
        currentHouse = newHouse;
    }

    public void changeCurrentRoom(Room newRoom){
        currentRoom = newRoom;
    }

    public void changeCurrentPos(Point newPos){
        currentPos = newPos;
    }

    public void getJob() {
        Random random = new Random();
        Job.findJob(pekerjaan, random.nextInt(5));
    }

    // Actions
    // public void sleep(Kasur kasur){
    //     kasur.sleep(this);
    // }

    // public void eating(Makanan Food, MejaKursi mejaKursi){

    // }

    // public void watchingTV(TV televisi){
    //     televisi.nontonTV(this);
    // }

    // public void pee(Toilet toilet){
    //     toilet.buangAir(this);
    // }

    // public void bath(Shower shower){
    //     shower.mandi(this);
    // }
}
