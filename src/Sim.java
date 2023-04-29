package src;
import src.Thing.*;
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
    private Inventory<Item> inventory;
    private Room currentRoom;
    private House currentHouse;
    private Point currentPos;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        uang = 100;
        kekenyangan = 80; kesehatan = 80; mood = 80;
        getJob(); // Set pekerjaan Sim secara random
        inventory = new Inventory<>();
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

    public Inventory<Item> getInventory(){
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
    public void changeKekenyangan(int exp) {
        if (kekenyangan + exp > 100){
            kekenyangan = 100;
        }
        else if(kekenyangan + exp < 0){
            kekenyangan = 0;
        }
        else{
            kekenyangan += exp;
        }
    }

    public void changeKesehatan(int exp) {
        if (kesehatan + exp > 100){
            kesehatan = 100;
        }
        else if(kesehatan + exp < 0){
            kesehatan = 0;
        }
        else{
            kesehatan += exp;
        }
    }

    public void changeMood(int exp) {
        if (mood + exp > 100){
            mood = 100;
        }
        else if(mood + exp < 0){
            mood = 0;
        }
        else{
            mood += exp;
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
    
    //Aksi
    public void eating(Food food){
        if (inventory.containsItem(food.getNama())){
            changeKekenyangan(food.getKekenyangan());
            inventory.removeItem(food.getNama());
        }
    }
    
    public void sleeping(Kasur kasur){
        kasur.Sleeping(this);
    }

    public void watchingTV(TV televisi){
        televisi.WatchingTV(this);
    }

    public void pee(Toilet toilet){
        toilet.buangAir(this);
    }

    public void bath(Shower shower){
        shower.mandi(this);
    }

    public void seeTime(Jam jam){
        jam.lihatWaktu(this);
    }

    public void cooking(Kompor kompor){
        kompor.cooking(this);
    }

    public void buyItem(Item item){
        if (item instanceof Purchaseable){
            if (uang >= item.getHarga()){
                uang -= item.getHarga();
                inventory.addItem(item);
            }
        }
    }

    public void moveRuangan(Room ruangan){
        currentRoom = ruangan;
    }

    public void installBarang(Thing thing, int x, int y) throws Exception{
        currentRoom.placeItem(thing, x, y);
    }

    public void seeInventory(){
        System.out.println("Inventory Sim : ");
        inventory.printItems();
    }

    public void visit(){
        changeMood(+10);
        changeKekenyangan(-10);
    }

    public void olahraga(){
        changeKesehatan(+5);
        changeMood(+10);
        changeKekenyangan(-5);
    }

    public void kerja(){
        uang += pekerjaan.getGaji();
        changeMood(-10);
        changeKekenyangan(-10);
    }

    public void upgradeRumah(Room oldRoom, Room newRoom, String arah) {
        try {
            currentHouse.addNewRoom(oldRoom, newRoom, arah);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sellBarang(){
        inventory.sellItems();
    }

    public void ambilBarang(Room ruangan){
        ruangan.ambilBarang(this);
    }

    public void washingHand(Shower shower){
        shower.washingHand(this);
    }

    public void mirroring(Mirror mirror){
        mirror.bercermin(this);
    }

    public void lookPainting(Lukisan lukisan){
        lukisan.lihatLukisan(this);
    }
}   
