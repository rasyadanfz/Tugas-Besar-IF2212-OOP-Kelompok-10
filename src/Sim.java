package src;
import src.Thing.*;

import java.util.ArrayList;
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
    private static World world;
    private ArrayList<Action> actionList;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        uang = 100;
        kekenyangan = 80; kesehatan = 80; mood = 80;
        getJob(); // Set pekerjaan Sim secara random
        inventory = new Inventory<>();
        justChangedJob = false;
        status = "idle";
        actionList = new ArrayList<Action>();
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
    
    public ArrayList<Action> getActionList(){
        return actionList;
    }

    public void decreaseActionDuration(Action a){
        a.decreaseDuration();
        // Hapus aksi jika durasinya 0
        if (a.getDurationLeft() == 0){
            actionList.remove(a);
        }
    }

    public void addAction(Action a){
        if (status.equals("idle")){
            actionList.add(a);
        }
        else{
            System.out.println("Sim sedang melakukan aksi lain!");
        }
    }

    public void showSimInfo(){
        System.out.printf("Nama : %s\n", getNamaLengkap());
        System.out.printf("Status : %s\n", getStatus());
        System.out.printf("Pekerjaan : %s\n", getPekerjaan());
        System.out.println("Kesejahteraan Sim :");
        System.out.printf("Uang : %d\n", getUang());
        System.out.printf("Kekenyangan : %d\n", getKekenyangan());
        System.out.printf("Kesehatan : %d\n", getKesehatan());
        System.out.printf("Mood : %d\n", getMood());
        seeInventory();
        System.out.printf("Nomor Rumah saat ini : %s\n", getCurrentHouse().getKodeRumah());
        System.out.printf("Ruangan saat ini : %s\n", getCurrentRoom().getNamaRuangan());
        System.out.printf("Koordinat : %s\n", getCurrentPos().toString());
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

    public void changeUang(int amount) {
        if (uang + amount < 0){
            uang = 0;
        }
        else {
            uang += amount;
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

    public void setStatus(String newStatus){
        status = newStatus;
    }

    public void getJob() {
        Random random = new Random();
        Job.findJob(pekerjaan, random.nextInt(5));
    }
    
    //Aksi
    public void eating(Food food){
        if (inventory.containsItem(food)){
            changeKekenyangan(food.getKekenyangan());
            inventory.removeItem(food);
        }
    }
    
    public void sleeping(Kasur kasur){ 
        kasur.Sleeping(this);
    }

    public void watchingTV(TV televisi){
        televisi.nontonTV(this);
    }

    public void pee(Toilet toilet){
        toilet.buangAir(this);
    }

    public void bath(Shower shower){
        shower.mandi(this);
    }

    public void seeTime(Jam jam){
        jam.lihatWaktu();
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

    public void upgradeRumah(Room oldRoom, Room newRoom, String arah) throws Exception {
        if (this.getUang() < 1500) {
            System.out.println("Sim tidak dapat melakukan upgrade rumah karena uang tidak cukup");
        } else {
            boolean check = false;
            House temp = currentHouse;

            try {
                check = temp.checkSpace(oldRoom, arah);
                temp.addNewRoom(oldRoom, newRoom, arah);
            } catch (Exception e) {
                throw e;
            }

            if (check) {
                Thread t = new Thread() {
                    public void run() {
                        try {
                            boolean upgrade = true;
                            int waktuUpgrade = 1080; // 18 menit
                            int waktuMulai = world.getTimer().getTime();

                            while (upgrade) {
                                if (waktuMulai + waktuUpgrade >= world.getTimer().getTime()) {
                                    upgrade = false;
                                }
                            }

                            currentHouse.addNewRoom(oldRoom, newRoom, arah);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                };
                t.start();
            }
        }
    }

    public void sellBarang(){
        inventory.sellItems();
    }

    public void ambilBarang(Room ruangan){
        ruangan.ambilBarang(this);
    }

    public void washingHand(Wastafel wastafel){
        wastafel.cuciTangan(this);
    }

    public void mirroring(Cermin cermin){
        cermin.bercermin(this);
    }

    public void lookPainting(Lukisan lukisan){
        lukisan.lihatLukisan(this);
    }
}   