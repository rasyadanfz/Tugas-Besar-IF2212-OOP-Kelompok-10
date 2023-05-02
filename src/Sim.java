package src;

import src.Exceptions.DurationNotValidException;
import src.Exceptions.ItemNotFoundException;
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
    private boolean inActiveAction = false;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        uang = 100;
        kekenyangan = 80;
        kesehatan = 80;
        mood = 80;
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

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public int getUang() {
        return uang;
    }

    public String getStatus() {
        return status;
    }

    public Inventory<Item> getInventory() {
        return inventory;
    }

    public House getCurrentHouse() {
        return currentHouse;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Point getCurrentPos() {
        return currentPos;
    }

    public boolean getJustChangedJob() {
        return justChangedJob;
    }

    public String getPekerjaan() {
        return String.format(pekerjaan.getNamaPekerjaan() + " dengan gaji " + pekerjaan.getGaji());
    }

    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public boolean getInActiveAction() {
        return inActiveAction;
    }

    public void decreaseActionDuration(Action a) {
        a.decreaseDuration();
        // Hapus aksi jika durasinya 0
        if (a.getDurationLeft() == 0) {
            a.getActionObject().effect(this, a.getOriginalDuration());
            actionList.remove(a);
        }
    }

    public void addAction(Action a) {
        if (status.equals("idle")) {
            actionList.add(a);
        } else {
            System.out.println("Sim sedang melakukan aksi lain!");
        }
    }

    public void showSimInfo() {
        // System.out.printf("Nama : %s\n", getNamaLengkap());
        // System.out.printf("Status : %s\n", getStatus());
        // System.out.printf("Pekerjaan : %s\n", getPekerjaan());
        // System.out.println("Kesejahteraan Sim :");
        // System.out.printf("Uang : %d\n", getUang());
        // System.out.printf("Kekenyangan : %d\n", getKekenyangan());
        // System.out.printf("Kesehatan : %d\n", getKesehatan());
        // System.out.printf("Mood : %d\n", getMood());
        // seeInventory();
        // System.out.printf("Nomor Rumah saat ini : %s\n", getCurrentHouse().getKodeRumah());
        // System.out.printf("Ruangan saat ini : %s\n", getCurrentRoom().getNamaRuangan());
        // System.out.printf("Koordinat : %s\n", getCurrentPos().toString());
        System.out.println("SIM INFO:");
        System.out.println("Nama Sim: " + getNamaLengkap());
        System.out.println("Pekerjaan: " + getPekerjaan());
        System.out.println("Kesehatan: " + getKesehatan());
        System.out.println("Kekenyangan: " + getKekenyangan());
        System.out.println("Mood: " + getMood());
        System.out.println("Uang: " + getUang());
    }

    // Setter
    public void changeKekenyangan(int exp) {
        if (kekenyangan + exp > 100) {
            kekenyangan = 100;
        } else if (kekenyangan + exp < 0) {
            kekenyangan = 0;
        } else {
            kekenyangan += exp;
        }
    }

    public void setInActiveAction(boolean newValue) {
        inActiveAction = newValue;
    }

    public void changeKesehatan(int exp) {
        if (kesehatan + exp > 100) {
            kesehatan = 100;
        } else if (kesehatan + exp < 0) {
            kesehatan = 0;
        } else {
            kesehatan += exp;
        }
    }

    public void changeMood(int exp) {
        if (mood + exp > 100) {
            mood = 100;
        } else if (mood + exp < 0) {
            mood = 0;
        } else {
            mood += exp;
        }
    }

    public void changeUang(int amount) {
        if (uang + amount < 0) {
            uang = 0;
        } else {
            uang += amount;
        }
    }

    public void changeCurrentHouse(House newHouse) {
        currentHouse = newHouse;
    }

    public void changeCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    public void changeCurrentPos(Point newPos) {
        currentPos = newPos;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
    }

    public void getJob() {
        Random random = new Random();
        Job.findJob(pekerjaan, random.nextInt(5));
    }

    // Aksi
    public void eating(Food food) {
        if (inventory.containsItem(food.getNama())) {
            changeKekenyangan(food.getKekenyangan());
            inventory.removeItem(food.getNama());
        }
    }

    public void sleeping(Kasur kasur, int duration) {
        kasur.Sleeping(this, duration);
    }

    public void watchingTV(TV televisi, int duration) {
        televisi.nontonTV(this, duration);
    }

    public void pee(Toilet toilet, int duration) {
        toilet.buangAir(this, duration);
    }

    public void bath(Shower shower) {
        shower.mandi(this);
    }

    public void seeTime(Jam jam) {
        jam.lihatWaktu();
    }

    public void cooking(Kompor kompor, Food food) {
        kompor.Cooking(this, food);
    }

    public void buyItem(Item item) {
        if (item instanceof Purchaseable) {
            if (uang >= ((Thing) item).getHarga()) {
                uang -= ((Thing) item).getHarga();
                ((Thing) item).buyItem();
                inventory.addItem(item);
            } else if (uang >= ((Ingredient) item).getPrice()) {
                uang -= ((Ingredient) item).getPrice();
                inventory.addItem(item);
            }
        }
    }

    public void moveRuangan(Room ruangan) {
        currentRoom = ruangan;
    }

    public void installBarang(String namaBarang, int x, int y) throws ItemNotFoundException, Exception {
        Thing thing = (Thing) inventory.getItem(namaBarang);
        String itemName = getFirstWord(thing.getNama());
        String kode = "P000";
        if (itemName.equals("Kasur")) {
            kode = new String("KS" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Cermin")) {
            kode = new String("CR" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Jam")) {
            kode = new String("JM" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Kompor")) {
            kode = new String("KM" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Lukisan")) {
            kode = new String("LK" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Meja")) {
            kode = new String("MK" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Shower")) {
            kode = new String("SH" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Toilet")) {
            kode = new String("TO" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("TV")) {
            kode = new String("TV" + currentRoom.getKodeJumlah(thing.getNama()));
        } else if (itemName.equals("Wastafel")) {
            kode = new String("WF" + currentRoom.getKodeJumlah(thing.getNama()));
        }
        thing.setKode(kode);
        try {
            currentRoom.placeItem(thing, x, y);
            inventory.removeItem(thing.getNama());
            inventory.getItemContainer().remove(thing);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void seeInventory() {
        System.out.println("Inventory Sim : ");
        inventory.printItems();
    }

    public void visit() {
        changeMood(+10);
        changeKekenyangan(-10);
    }

    public void olahraga(int duration) {
        try {
            if (duration % 20 == 0) {
                int x = duration / 20;
                for (int i = 0; i < x; i++) {
                    changeKesehatan(+5);
                    changeMood(+10);
                    changeKekenyangan(-5);
                }
            } else {
                throw new DurationNotValidException(20);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void kerja(int duration) {
        uang += pekerjaan.getGaji();
        try {
            if (duration % 30 == 0) {
                int x = duration / 30;
                for (int i = 0; i < x; i++) {
                    changeMood(-10);
                    changeKekenyangan(-10);
                }
            } else {
                throw new DurationNotValidException(30);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void move(int x, int y) {
        if (x < 1 || y < 1 || x > 6 || y > 6) {
            System.out.println("Sim tidak bisa bergerak keluar ruangan");
        } else {
            currentPos.setX(x);
            currentPos.setY(y);
            System.out.println("Sim bergerak ke " + "<" + x + "," + y + ">");
        }

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
                            Jam.ambilSisaWaktuUpgrade(waktuMulai, waktuUpgrade);

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

    // public void sellBarang() {
    // inventory.sellItems();
    // }

    // public void ambilBarang(Room ruangan) {
    // ruangan.ambilBarang(this);
    // }

    public void washingHand(Wastafel wastafel, int duration) {
        wastafel.cuciTangan(this, duration);
    }

    public void mirroring(Cermin cermin) {
        cermin.bercermin(this);
    }

    public void lookPainting(Lukisan lukisan, int duration) {
        lukisan.lihatLukisan(this, duration);
    }

    // Helper Method
    private String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) {
            return text.substring(0, index).trim();
        } else {
            return text;
        }
    }
}