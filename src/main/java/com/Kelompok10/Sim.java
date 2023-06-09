package com.Kelompok10;

import com.Kelompok10.Exceptions.CantChangeJobException;
import com.Kelompok10.Exceptions.DurationNotValidException;
import com.Kelompok10.Exceptions.ItemNotFoundException;
import com.Kelompok10.Exceptions.MoneyNotEnoughException;
import com.Kelompok10.Exceptions.RoomIsEmptyException;
import com.Kelompok10.Exceptions.SimIsDeadException;
import com.Kelompok10.Thing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.*;

public class Sim {
    private String namaLengkap;
    private String status;
    private boolean isAlive;
    private House ownedHouse;
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
    private static World world = World.getWorld();
    private ArrayList<Action> actionList;
    private Delivery delivery;
    private int sisaWaktuUpgradeRumah;
    private boolean inActiveAction = false;
    private int workTime = 0;
    private int totalWorkTimeOnCurrentJob = 0;
    private double visitTime = 0.0;
    private int notSleepYet = 0;
    private boolean haveEat = false;
    private int notPeeYet = 0;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        isAlive = true;
        uang = 100;
        kekenyangan = 80;
        kesehatan = 80;
        mood = 80;
        getJob(); // Set pekerjaan Sim secara random
        inventory = new Inventory<>();
        justChangedJob = false;
        status = "idle";
        actionList = new ArrayList<Action>();
        sisaWaktuUpgradeRumah = 0;
        delivery = new Delivery(0, 0, null);
    }

    // Getter
    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public boolean getIsAlive() {
        return isAlive;
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

    public House getOwnedHouse() {
        return ownedHouse;
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

    public void setJustChangedJob(boolean changedJob) {
        justChangedJob = changedJob;
    }

    public Job getPekerjaan() {
        return pekerjaan;
    }

    public int getTotalWorkTimeOnCurrentJob() {
        return totalWorkTimeOnCurrentJob;
    }

    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public boolean getInActiveAction() {
        return inActiveAction;
    }

    public synchronized Delivery getItemDelivery() {
        return delivery;
    }

    public synchronized void setItemDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public synchronized int getSisaWaktuUpgrade() {
        return sisaWaktuUpgradeRumah;
    }

    public synchronized void setSisaWaktuUpgrade(int newValue) {
        sisaWaktuUpgradeRumah = newValue;
    }

    public int getWorkTime() {
        return workTime;
    }

    public double getVisitTime() {
        return visitTime;
    }

    public boolean getHaveEat() {
        return haveEat;
    }

    public int getNotSleepYet() {
        return notSleepYet;
    }

    public int getNotPeeYet() {
        return notPeeYet;
    }

    public boolean checkKondisiSimMati() {
        if (getKekenyangan() == 0 || getKesehatan() == 0 || getMood() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setNotSleepYet(int val) {
        notSleepYet = val;
    }

    public void setNotPeeYet(int newVal) {
        notPeeYet = newVal;
    }

    public void setVisitTime(double newVal) {
        visitTime = newVal;
    }

    public void setTotalWorkTimeOnCurrentJob(int newValue) {
        totalWorkTimeOnCurrentJob = newValue;
    }

    public void setHaveEat(boolean newVal) {
        haveEat = newVal;
    }

    public void decreaseActionDuration(Action a) {
        if (a.getActionObject() != null) {
            a.decreaseDuration();
            world.getTimer().increaseTime();
            GameManager.getGameManager().updateEachSim(a.getActionName());

            try {
                // CHEAT
                if (!GameManager.getGameManager().getCheat().getIsTimeSkipEnabled()) {
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Hapus aksi jika durasinya 0
            if (a.getDurationLeft() == 0) {
                // a.getActionObject().effect(this, a.getOriginalDuration());
                actionList.remove(a);
                setInActiveAction(false);
                setStatus("idle");
            }
        }
    }

    public void decreaseNonItemActionDuration(Action a) {
        if (a.getDurationLeft() > 0) {
            a.decreaseDuration();
            world.getTimer().increaseTime();
            GameManager.getGameManager().updateEachSim(a.getActionName());

            try {
                // CHEAT
                if (!GameManager.getGameManager().getCheat().getIsTimeSkipEnabled()) {
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // Hapus aksi jika durasinya 0
        else if (a.getDurationLeft() == 0) {
            // a.getActionObject().effect(this, a.getOriginalDuration());
            actionList.remove(a);
            setInActiveAction(false);
            setStatus("idle");
        }
    }

    public void addAction(Action a) {
        if (status.equals("idle")) {
            actionList.add(a);
        } else {
            System.out.println("Sim sedang melakukan aksi lain!");
        }
    }

    public void removeAction(Action a) {
        actionList.remove(a);
    }

    public void showSimInfo() {
        System.out.printf("%-60s\n", "+----------------------------------------------------------+");
        System.out.printf("| %-20s %s %-21s |\n", " ", "Informasi Sim", " ");
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Nama", capitalizeFirstLetter(getNamaLengkap()));
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Status", capitalizeFirstLetter(getStatus()));
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Pekerjaan", getPekerjaan().getNamaPekerjaan());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Gaji Pekerjaan", getPekerjaan().getGaji());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Uang", getUang());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Kekenyangan", getKekenyangan());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Kesehatan", getKesehatan());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Mood", getMood());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Current House", getCurrentHouse().getKodeRumah());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Current Room", getCurrentRoom().getNamaRuangan());
        System.out.printf("%-60s\n", "+----------------------+-----------------------------------+");
        System.out.printf("| %-20s | %-34s|\n", "Current Position", getCurrentPos().toString());
        System.out.printf("%-60s\n\n", "-----------------------+------------------------------------");
        seeInventory();

    }

    public void viewCurrentLocation() {
        System.out.println("Current Location : ");
        System.out.println("Rumah : " + getCurrentHouse().getKodeRumah() + " di Posisi "
                + getCurrentHouse().getLokasi().toString());
        System.out.println("Ruangan : " + getCurrentRoom().getNamaRuangan());
        System.out.println("Posisi : " + getCurrentPos().toString());
        System.out.println("Peta Rumah: ");
        getCurrentHouse().printPetaRumah();
        System.out.println("Peta Ruangan: ");
        getCurrentRoom().printPetaRuangan(this);
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

    public void setIsAlive(boolean newValue) {
        isAlive = newValue;
    }

    public void resetSimToOwnedHouse() {
        // Balikin sim ke rumahnya masing-masing
        changeCurrentHouse(getOwnedHouse());
        changeCurrentRoom(getCurrentHouse().getRoom("R001"));
        changeCurrentPos(new Point(1, 1));
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

    public void setOwnedHouse(House ownedHouse) {
        this.ownedHouse = ownedHouse;
    }

    public void getJob() {
        Random random = new Random();
        Job.findJob(pekerjaan, random.nextInt(5));
    }

    public void incNotSleepYet(int duration) {
        notSleepYet += duration;
    }

    public void incNotPeeYet(int duration) {
        notPeeYet += duration;
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
        jam.lihatWaktu(this);
    }

    public void cooking(Kompor kompor, Food food) {
        kompor.Cooking(this, food.getNama());
    }

    public void getNegativeEffect() {
        if (notSleepYet >= 600) {
            changeKesehatan(-5);
            changeMood(-5);
            notSleepYet -= 600;
        }

        if (haveEat) {
            if (notPeeYet >= 240) {
                changeKesehatan(-5);
                changeMood(-5);
                notPeeYet -= 240;
            }
        }
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

    public void installBarang(String namaBarang, int x, int y, boolean rotate) throws ItemNotFoundException, Exception {
        if ((inventory.getItem(capitalizeEachWord(namaBarang)) instanceof Thing)) {
            Thing thing = (Thing) inventory.getItem(capitalizeEachWord(namaBarang));
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
                currentRoom.placeItem(thing, x, y, rotate);
                inventory.removeItem(thing.getNama());
                inventory.getItemContainer().remove(thing);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Tidak bisa memasang barang yang bukan furnitur");
        }

    }

    public void seeInventory() {
        inventory.printItems();
    }

    public void visit(House destHouse, int timeToTravel) throws Exception {
        Action visitAction = new Action("visit", timeToTravel, null);
        boolean keepRunning = true;
        addAction(visitAction);
        setStatus("active");
        setInActiveAction(true);
        System.out.print("Sisa durasi: ");
        while (visitAction.getDurationLeft() > 0 && keepRunning) {
            // menampilkan durasi
            int printDuration = visitAction.getDurationLeft() - 1;
            if (printDuration < 10) {
                System.out.print("00" + printDuration);
            } else if (printDuration < 100) {
                System.out.print("0" + printDuration);
            } else {
                System.out.print(printDuration);
            }
            if (printDuration != 0) {
                System.out.print("\b\b\b");
            }
            visitTime++;
            if (visitTime == 30) {
                changeMood(+10);
                changeKekenyangan(-10);
                visitTime = 0;
            }
            decreaseNonItemActionDuration(visitAction);
            if (checkKondisiSimMati()) {
                keepRunning = false;
                throw new SimIsDeadException("Sim " + getNamaLengkap() + " mati di perjalanan! :(");
            }
        }
        setStatus("idle");
        setInActiveAction(false);
        if (checkKondisiSimMati()) {
            throw new SimIsDeadException("Sim " + getNamaLengkap() + " mati di perjalanan! :(");
        }
        System.out.println();

        currentHouse = destHouse;
        currentRoom = currentHouse.getRoom("R001");
        currentPos = new Point(1, 1);
    }

    public void setUang(int newValue) {
        uang = newValue;
    }

    public void olahraga(int duration) throws SimIsDeadException {
        System.out.println("Sim sedang melakukan olahraga..");
        Action olahragaAction = new Action("olahraga", duration, null);
        boolean keepRunning = true;
        addAction(olahragaAction);
        setStatus("active");
        setInActiveAction(true);
        System.out.print("Sisa durasi: ");
        int timeCounter = 0;
        while (olahragaAction.getDurationLeft() > 0 && keepRunning) {
            // menampilkan durasi
            int printDuration = olahragaAction.getDurationLeft() - 1;
            if (printDuration < 10) {
                System.out.print("00" + printDuration);
            } else if (printDuration < 100) {
                System.out.print("0" + printDuration);
            } else {
                System.out.print(printDuration);
            }
            if (printDuration != 0) {
                System.out.print("\b\b\b");
            }
            timeCounter++;
            decreaseNonItemActionDuration(olahragaAction);
            if (timeCounter == 20) {
                changeKesehatan(+5);
                changeMood(+10);
                changeKekenyangan(-5);
                timeCounter = 0;

            }
            if (checkKondisiSimMati()) {
                keepRunning = false;
                throw new SimIsDeadException("Sim " + getNamaLengkap() + " mati karena kelaparan saat olahraga! :(");
            }
        }
        setStatus("idle");
        setInActiveAction(false);
        if (checkKondisiSimMati()) {
            keepRunning = false;
            throw new SimIsDeadException("Sim " + getNamaLengkap() + " mati karena kelaparan saat olahraga! :(");
        }
        System.out.println();
    }

    public void kerja(int duration) throws SimIsDeadException {
        System.out.println("Sim sedang melakukan kerja..");
        Action kerjaAction = new Action("kerja", duration, null);
        addAction(kerjaAction);
        setStatus("active");
        setInActiveAction(true);
        int counter = 0;
        boolean keepRunning = true;
        System.out.print("Sisa durasi: ");
        while (kerjaAction.getDurationLeft() > 0 && keepRunning) {
            // menampilkan durasi
            int printDuration = kerjaAction.getDurationLeft() - 1;
            if (printDuration < 10) {
                System.out.print("00" + printDuration);
            } else if (printDuration < 100) {
                System.out.print("0" + printDuration);
            } else {
                System.out.print(printDuration);
            }
            if (printDuration != 0) {
                System.out.print("\b\b\b");
            }
            if (checkKondisiSimMati()) {
                keepRunning = false;
                throw new SimIsDeadException("Sim " + getNamaLengkap() + " mati ketika bekerja! :(");
            } else {
                counter++;
                decreaseNonItemActionDuration(kerjaAction);
                workTime++;
                if (counter % 30 == 0) {
                    changeMood(-10);
                    changeKekenyangan(-10);
                }
                if (workTime % 240 == 0) { // Dapat gaji kalau sudah bekerja selama 4 menit
                    workTime = 0; // Counternya reset
                    uang += pekerjaan.getGaji();
                }
                setTotalWorkTimeOnCurrentJob(getTotalWorkTimeOnCurrentJob() + 1);
            }
        }
        setStatus("idle");
        setInActiveAction(false);
        if (checkKondisiSimMati()) {
            throw new SimIsDeadException("Sim " + getNamaLengkap() + " mati ketika bekerja! :(");
        }
        System.out.println();
    }

    public void buyFurniture() {
        if (getItemDelivery().getRemainingDuration() != 0) {
            System.out.println("Pengiriman barang/bahan makanan sebelumnya masih berlangsung!");
        } else {
            System.out.println("Pilih furnitur yang ingin dibeli:");
            System.out.println("1. Toilet");
            System.out.printf("    - Harga: 50\n    - Dimensi: 1x1\n");
            System.out.println("2. Kasur Single");
            System.out.printf("    - Harga: 50\n    - Dimensi: 4x1\n");
            System.out.println("3. Kasur Queen Size");
            System.out.printf("    - Harga: 100\n    - Dimensi: 4x2\n");
            System.out.println("4. Kasur King Size");
            System.out.printf("    - Harga: 150\n    - Dimensi: 5x2\n");
            System.out.println("5. Kompor Gas");
            System.out.printf("    - Harga: 100\n    - Dimensi: 2x1\n");
            System.out.println("6. Kompor Listrik");
            System.out.printf("    - Harga: 200\n    - Dimensi: 1x1\n");
            System.out.println("7. Meja dan Kursi");
            System.out.printf("    - Harga: 50\n    - Dimensi: 3x3\n");
            System.out.println("8. Jam");
            System.out.printf("    - Harga: 10\n    - Dimensi: 1x1\n");
            System.out.println("9. Lukisan");
            System.out.printf("    - Harga: 100\n    - Dimensi: 1x1\n");
            System.out.println("10. Wastafel");
            System.out.printf("    - Harga: 40\n    - Dimensi: 1x1\n");
            System.out.println("11. Shower");
            System.out.printf("    - Harga: 50\n    - Dimensi: 1x1\n");
            System.out.println("12. Cermin");
            System.out.printf("    - Harga: 50\n    - Dimensi: 2x1\n");
            System.out.println("13. TV");
            System.out.printf("    - Harga: 200\n    - Dimensi: 2x1\n");
            System.out.printf("Pilihan (Masukkan nama barang sesuai daftar): ");
            String choice = InputScanner.getInputScanner().getScanner().nextLine();
            Thing toBuy = null;
            switch (choice.toUpperCase()) {
                case ("TOILET"):
                    toBuy = new Toilet();
                    break;
                case ("KASUR SINGLE"):
                    toBuy = new KasurSingle();
                    break;
                case ("KASUR QUEEN SIZE"):
                    toBuy = new KasurQueen();
                    break;
                case ("KASUR KING SIZE"):
                    toBuy = new KasurKing();
                    break;
                case ("KOMPOR GAS"):
                    toBuy = new KomporGas();
                    break;
                case ("KOMPOR LISTRIK"):
                    toBuy = new KomporListrik();
                    break;
                case ("MEJA DAN KURSI"):
                    toBuy = new MejaKursi();
                    break;
                case ("JAM"):
                    toBuy = new Jam();
                    break;
                case ("LUKISAN"):
                    toBuy = new Lukisan();
                    break;
                case ("WASTAFEL"):
                    toBuy = new Wastafel();
                    break;
                case ("SHOWER"):
                    toBuy = new Shower();
                    break;
                case ("CERMIN"):
                    toBuy = new Cermin();
                    break;
                case ("TV"):
                    toBuy = new TV();
                    break;
                default:
                    System.out.println("Furnitur " + choice + " tidak ada!");
            }
            if (!Objects.isNull(toBuy)) {
                final Thing item = toBuy;
                Thread newThread = new Thread() {
                    public void run() {
                        if (getUang() >= item.getHarga()) {
                            setUang(getUang() - item.getHarga());
                            item.buyItem();
                            getInventory().addItem(item);
                        } else {
                            System.out.println(new MoneyNotEnoughException().getMessage());
                        }
                    }

                };
                newThread.start();
                System.out.printf("Furnitur %s berhasil dibeli\n", item.getNama());
            }
        }

    }

    public void buyIngredient() {
        if (getItemDelivery().getRemainingDuration() != 0) {
            System.out.println("Pengiriman barang/bahan makanan sebelumnya masih berlangsung!");
        } else {
            System.out.println("Daftar bahan makanan yang bisa dibeli: ");
            System.out.println("1. Nasi");
            System.out.println("2. Kentang");
            System.out.println("3. Ayam");
            System.out.println("4. Sapi");
            System.out.println("5. Wortel");
            System.out.println("6. Bayam");
            System.out.println("7. Kacang");
            System.out.println("8. Susu");
            System.out.printf("Pilihan: ");
            String choice = InputScanner.getInputScanner().getScanner().nextLine();
            Ingredient toBuy = null;
            switch (choice.toLowerCase()) {
                case ("nasi"):
                    toBuy = new Ingredient("Nasi");
                    break;
                case ("kentang"):
                    toBuy = new Ingredient("Kentang");
                    break;
                case ("ayam"):
                    toBuy = new Ingredient("Ayam");
                    break;
                case ("sapi"):
                    toBuy = new Ingredient("Sapi");
                    break;
                case ("wortel"):
                    toBuy = new Ingredient("Wortel");
                    break;
                case ("bayam"):
                    toBuy = new Ingredient("Bayam");
                    break;
                case ("kacang"):
                    toBuy = new Ingredient("Kacang");
                    break;
                case ("susu"):
                    toBuy = new Ingredient("Susu");
                    break;
            }
            if (toBuy == null) {
                System.out.println("Bahan makanan " + choice + "tidak ada");
            } else {
                if (!Objects.isNull(toBuy)) {
                    final Ingredient item = toBuy;
                    Thread newThread = new Thread() {
                        public void run() {
                            if (getUang() >= item.getPrice()) {
                                setUang(getUang() - item.getPrice());
                                item.buyItem();
                                getInventory().addItem(item);
                            } else {
                                System.out.println(new MoneyNotEnoughException().getMessage());
                            }
                        }

                    };
                    newThread.start();
                    System.out.printf("Bahan Makanan %s berhasil dibeli\n", item.getNama());
                }
            }
        }
    }

    public void editRoom() {
        // Sim tidak sedang di rumah
        if (Objects.isNull(getCurrentHouse())) {
            System.out.println("Sim tidak dalam suatu rumah!");
        }
        // Sim tidak di dalam ruangan
        else if (Objects.isNull(getCurrentRoom())) {
            System.out.println("Sim tidak dalam suatu ruangan!");
        } else {
            System.out.println("Peta Ruangan: ");
            getCurrentRoom().printPetaRuangan();
            try {
                getCurrentRoom().printPlacedObject();
            } catch (RoomIsEmptyException riee) {
                System.out.println(riee.getMessage());
            }

            try {
                Scanner scanner = InputScanner.getInputScanner().getScanner();
                System.out.printf("Masukkan posisi x barang yang akan dipindahkan: ");
                int originalPosX = Integer.parseInt(scanner.nextLine());
                System.out.printf("Masukkan posisi y barang yang akan dipindahkan: ");
                int originalPosY = Integer.parseInt(scanner.nextLine());
                System.out.printf("Masukkan posisi x lokasi pemindahan barang: ");
                int posX = Integer.parseInt(scanner.nextLine());
                System.out.printf("Masukkan posisi y lokasi pemindahan barang: ");
                int posY = Integer.parseInt(scanner.nextLine());

                Point itemPosition = new Point(originalPosX, originalPosY);
                Point targetPosition = new Point(posX, posY);
                Thing thingToMove = currentRoom.findItemInContainer(itemPosition);
                try {
                    currentRoom.removeItem(originalPosX, originalPosY);
                    currentRoom.placeItem(thingToMove, posX, posY, false);
                    System.out.printf(
                            "Barang %s dengan kode %s berhasil dipindahkan ke " + targetPosition.toString() + " \n",
                            thingToMove.getNama(), thingToMove.getKode());
                } catch (Exception e) {
                    System.out.println("Barang tidak bisa dipindah ke lokasi " + targetPosition.toString());
                    try {
                        currentRoom.placeItem(thingToMove, originalPosX, originalPosY, false);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
            }
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

    public void moveRoom(String input) {
        if (!input.equals(getCurrentRoom().getNamaRuangan())) {
            Room targetRoom = getCurrentHouse().getRoom(input);
            if (!Objects.isNull(targetRoom)) {
                moveRuangan(targetRoom);
            } else {
                System.out.println("Ruangan dengan kode " + input + " tidak ada");
            }
        } else {
            System.out.println("Sim sudah ada di ruangan " + getCurrentRoom().getNamaRuangan() + "!");
        }
    }

    public void goToObject(int x, int y) {
        Point toCheck = new Point(x, y);
        boolean found = false;
        for (String key : getCurrentRoom().getPlacedObject().keySet()) {
            for (Point p : getCurrentRoom().getPlacedObject().get(key)) {
                if (toCheck.equals(p)) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            move(x, y);
            actions(true);
        } else {
            System.out.println("Objek dengan posisi " + toCheck.toString() + " tidak ditemukan");
        }

    }

    public void upgradeRumah(String arah) throws Exception {
        if (this.getUang() < 1500) {
            System.out.println("Sim tidak dapat melakukan upgrade rumah karena uang tidak cukup");
        } else if (!getCurrentHouse().getKodeRumah().equals(getOwnedHouse().getKodeRumah())) {
            System.out.println("Sim tidak bisa mengupgrade rumah Sim lain!");
        } else if (getSisaWaktuUpgrade() != 0) {
            System.out.println("Tidak dapat melakukan upgrade rumah karena terdapat upgrade yang masih berlangsung");
        } else {
            final Room oldRoom = getCurrentRoom();
            String roomNumber;
            if (getCurrentHouse().getRoomCount() < 10) {
                roomNumber = "00" + (getCurrentHouse().getRoomCount() + 1);
            } else if (getCurrentHouse().getRoomCount() >= 10 && getCurrentHouse().getRoomCount() < 99) {
                roomNumber = "0" + getCurrentHouse().getRoomCount() + 1;
            } else {
                roomNumber = Integer.toString(getCurrentHouse().getRoomCount() + 1);
            }
            final Room newRoom = new Room("R" + roomNumber, getCurrentHouse());
            boolean check = false;
            House temp = currentHouse;
            final String direction = arah;
            try {
                check = temp.checkSpace(oldRoom, arah);
            } catch (Exception e) {
                throw e;
            }

            if (check) {
                Thread t = new Thread() {
                    public void run() {
                        try {
                            boolean isSimAlive = true;
                            boolean done = false;
                            boolean upgrade = true;
                            int waktuUpgrade = 1080; // 18 menit
                            int waktuMulai = Timer.getTimer().getTotalTime();
                            GameManager.getGameManager().getActiveSim().setSisaWaktuUpgrade(waktuUpgrade);
                            while (upgrade) {
                                if (!isSimAlive) {
                                    upgrade = false;
                                } else {
                                    if (waktuMulai + waktuUpgrade <= Timer.getTimer().getTotalTime()) {
                                        upgrade = false;
                                        done = true;
                                        GameManager.getGameManager().getActiveSim()
                                                .setSisaWaktuUpgrade(0);
                                    } else {
                                        if (GameManager.getGameManager().getActiveSim() != null) {
                                            GameManager.getGameManager().getActiveSim()
                                                    .setSisaWaktuUpgrade(
                                                            waktuMulai + waktuUpgrade
                                                                    - Timer.getTimer().getTotalTime());
                                        }
                                    }
                                }
                            }
                            if (done) {
                                currentHouse.addNewRoom(oldRoom, newRoom, direction);
                                uang -= 1500;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                };
                t.start();
                System.out.printf("Rumah berhasil diupgrade dengan menambahkan arah ke %s\n", arah);
            }
        }
    }

    public void sellBarang(String itemName) {
        try {
            itemName = capitalizeEachWord(itemName);
            Thing toSell = (Thing) inventory.getItem(itemName);
            setUang(getUang() + toSell.getHarga());
            inventory.removeItem(itemName);
            System.out.printf("Barang %s berhasil dijual!", toSell.getNama());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ambilBarang(int xPos, int yPos) {
        try {
            Item toGet = currentRoom.findItemInContainer(new Point(xPos, yPos));
            currentRoom.removeItem(xPos, yPos);
            inventory.addItem(toGet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void washingHand(Wastafel wastafel, int duration) {
        wastafel.cuciTangan(this);
    }

    public void mirroring(Cermin cermin) {
        cermin.bercermin(this);
    }

    public void lookPainting(Lukisan lukisan, int duration) {
        lukisan.lihatLukisan(this, duration);
    }

    public void changeJob() throws CantChangeJobException {
        // Minimal kerja 12 menit
        // Cek apakah uang cukup untuk bayar biaya ganti kerja (1/2 pekerjaan baru)
        if (getTotalWorkTimeOnCurrentJob() >= 720) {
            System.out.println("Daftar Pekerjaan: ");
            System.out.println("- Badut Sulap dengan Gaji 15");
            System.out.println("- Koki dengan Gaji 30");
            System.out.println("- Polisi dengan Gaji 35");
            System.out.println("- Programmer dengan Gaji 45");
            System.out.println("- Dokter dengan Gaji 50");
            Job newJob = null;
            String pilihan = capitalizeEachWord(InputScanner.getInputScanner().getScanner().nextLine());
            while (newJob == null) {
                if (!((pilihan.equals("Badut Sulap")) || pilihan.equals("Koki") || pilihan.equals("Polisi")
                        || pilihan.equals("Programmer") || pilihan.equals("Dokter"))) {
                    System.out.println("Pilih daftar pekerjaan yang diinginkan sesuai daftar: ");
                    pilihan = InputScanner.getInputScanner().getScanner().nextLine();
                } else {
                    if (pilihan.equals(pekerjaan.getNamaPekerjaan())) {
                        System.out.println("Sim sudah bekerja sebagai " + pekerjaan.getNamaPekerjaan());
                        pilihan = InputScanner.getInputScanner().getScanner().nextLine();
                    } else {
                        newJob = new Job(pilihan);
                    }
                }
            }
            if (getUang() >= newJob.getGaji()) {
                setUang(getUang() - newJob.getGaji());
                pekerjaan = newJob;
                justChangedJob = true;
                setTotalWorkTimeOnCurrentJob(0);
            } else {
                throw new CantChangeJobException("Sim tidak memiliki cukup uang untuk membayar biaya ganti pekerjaan!");
            }
        } else {
            throw new CantChangeJobException("Sim belum bekerja selama 12 menit pada pekerjaan saat ini!");
        }
    }

    public void actions(boolean goTo) {
        Scanner actionScanner = InputScanner.getInputScanner().getScanner();
        String input = "";
        String objectNameNearSim = null;
        Thing objectNearSim = null;
        HashMap<String, ArrayList<Point>> listOfObjects = this.getCurrentRoom().getPlacedObject();
        for (String s : listOfObjects.keySet()) {
            for (Point p : listOfObjects.get(s)) {
                if (this.getCurrentPos().equals(p)) {
                    objectNearSim = this.getCurrentRoom().findItemInContainer(p);
                    objectNameNearSim = objectNearSim.getNama();
                }
            }
        }

        if (!goTo) {
            System.out.println("List Aksi: ");
            System.out.println("1. Work");
            System.out.println("2. Olahraga");
            System.out.println("3. Visit");

            if (!Objects.isNull(objectNameNearSim)) {
                String firstWord = getFirstWord(objectNameNearSim);
                switch (firstWord) {
                    case ("Kasur"):
                        System.out.println("4. Sleep");
                        break;
                    case ("Cermin"):
                        System.out.println("4. Bercermin");
                        break;
                    case ("Jam"):
                        System.out.println("4. Lihat Jam");
                        break;
                    case ("Toilet"):
                        System.out.println("4. Pee");
                        break;
                    case ("Kompor"):
                        System.out.println("4. Cook");
                        break;
                    case ("Lukisan"):
                        System.out.println("4. Lihat Lukisan");
                        break;
                    case ("Meja"):
                        System.out.println("4. Makan");
                        break;
                    case ("Shower"):
                        System.out.println("4. Mandi");
                        break;
                    case ("TV"):
                        System.out.println("4. Nonton TV");
                        break;
                    case ("Wastafel"):
                        System.out.println("4. Cuci Tangan");
                        break;
                }
            }
            System.out.println("Masukkan aksi yang ingin dilakukan:");
            input = capitalizeEachWord(actionScanner.nextLine());
            switch (input) {
                case ("Work"):
                    if (!justChangedJob) {
                        System.out.print("Masukkan durasi bekerja: ");
                        try {
                            int durasi = Integer.parseInt(actionScanner.nextLine());
                            while (durasi == 0 || durasi % 120 != 0) {
                                System.out.println(
                                        "Durasi kerja tidak valid! Harap lakukan input ulang dengan kelipatan 120");
                                System.out.print("Masukkan durasi bekerja: ");
                                durasi = Integer.parseInt(actionScanner.nextLine());
                            }
                            this.kerja(durasi);
                        } catch (NumberFormatException e) {
                            System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Sim belum bisa bekerja hari ini!");
                    }
                    break;
                case ("Olahraga"):
                    System.out.print("Masukkan durasi olahraga: ");
                    try {
                        int durasi = Integer.parseInt(actionScanner.nextLine());
                        while (durasi == 0 || durasi % 20 != 0) {
                            System.out
                                    .println(
                                            "Durasi olahraga tidak valid! Harap lakukan input ulang dengan kelipatan 20");
                            System.out.print("Masukkan durasi olahraga: ");
                            durasi = Integer.parseInt(actionScanner.nextLine());
                        }
                        this.olahraga(durasi);
                    } catch (NumberFormatException e) {
                        System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ("Visit"):
                    System.out.println("Masukkan nama sim yang rumahnya ingin dikunjungi: ");
                    GameManager.getGameManager().printSimList();
                    String namaSim = actionScanner.nextLine();
                    while (GameManager.getGameManager().getSim(namaSim) == null) {
                        System.out.println("Masukkan nama sim sesuai nama yang ada pada daftar: ");
                        namaSim = actionScanner.nextLine();
                    }
                    String destHouseCode = GameManager.getGameManager().getSim(namaSim).getOwnedHouse().getKodeRumah();
                    House destHouse = world.getHouse(destHouseCode);

                    if (destHouse != null) {
                        try {
                            int x1 = this.getCurrentHouse().getLokasi().getX();
                            int y1 = this.getCurrentHouse().getLokasi().getY();
                            int x2 = destHouse.getLokasi().getX();
                            int y2 = destHouse.getLokasi().getY();
                            if (destHouse.equals(currentHouse)) {
                                throw new Exception("Sim sudah berada di rumah tersebut!");
                            } else {
                                double durasiPergi = Math
                                        .sqrt(Math.pow((x2 * 1.0) - (x1 * 1.0), 2)
                                                + Math.pow((y2 * 1.0) - (y1 * 1.0), 2));
                                int durasiRounded = (int) Math.round(durasiPergi);
                                System.out.println("Berkunjung ke rumah " + destHouse.getKodeRumah()
                                        + "di " + destHouse.getLokasi().toString() + " dengan lama perjalanan : "
                                        + durasiRounded);
                                this.visit(destHouse, durasiRounded);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Rumah dengan kode " + destHouseCode + " tidak ditemukan!");
                    }
                    break;
                default:
                    if (!Objects.isNull(objectNameNearSim)) {
                        String firstWord = getFirstWord(objectNameNearSim);
                        if (input.equals("Sleep") && (firstWord.equals("Kasur"))) {
                            System.out.println("Masukkan durasi (dalam detik):");
                            try {
                                int duration = Integer.parseInt(actionScanner.nextLine());
                                while (duration < 180) {
                                    System.out.println("Durasi tidur tidak valid! Tidur minimal 3 menit (180 detik)");
                                    duration = Integer.parseInt(actionScanner.nextLine());
                                }
                                Kasur kasur = (Kasur) objectNearSim;
                                kasur.Sleeping(this, duration);
                            } catch (NumberFormatException e) {
                                System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                            }
                        } else if (input.equals("Bercermin") && (firstWord.equals("Cermin"))) {
                            Cermin currentCermin = (Cermin) objectNearSim;
                            currentCermin.bercermin(this);
                        } else if (input.equals("Lihat Jam") && (firstWord.equals("Jam"))) {
                            Jam jam = (Jam) objectNearSim;
                            jam.lihatWaktu(this);
                        } else if (input.equals("Pee") && firstWord.equals("Toilet")) {
                            Toilet toilet = (Toilet) objectNearSim;
                            System.out.print("Masukkan durasi (dalam detik): ");
                            try {
                                int durasiPee = Integer.parseInt(actionScanner.nextLine());
                                toilet.buangAir(this, durasiPee);
                            } catch (NumberFormatException e) {
                                System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                            }
                        } else if (input.equals("Cook") && (firstWord.equals("Kompor"))) {
                            this.getInventory().printListIngredient();
                            Kompor kompor = (Kompor) objectNearSim;
                            if (kompor.checkBahanMasak(getInventory())) {
                                System.out.println("Masukkan nama makanan yang ingin dimasak: ");
                                String namaMakanan = capitalizeEachWord(actionScanner.nextLine());
                                kompor.Cooking(this, (namaMakanan));
                            }
                        } else if (input.equals("Lihat Lukisan") && (firstWord.equals("Lukisan"))) {
                            System.out.println("Masukkan durasi (dalam detik):");
                            try {
                                int duration = Integer.parseInt(actionScanner.nextLine());
                                Lukisan lukisan = (Lukisan) objectNearSim;
                                lukisan.lihatLukisan(this, duration);
                            } catch (NumberFormatException e) {
                                System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                            }
                        } else if (input.equals("Makan") && (firstWord.equals("Meja"))) {
                            try {
                                this.getInventory().printListMakanan();
                                System.out.println("Masukkan nama makanan yang ingin dimakan: ");
                                String namaMakanan = capitalizeEachWord(actionScanner.nextLine());
                                Item makanan = getInventory().getItem(namaMakanan);
                                if (makanan instanceof Ingredient) {
                                    makanan = (Ingredient) makanan;
                                } else if (makanan instanceof Food) {
                                    makanan = (Food) makanan;
                                }
                                MejaKursi mejakursi = (MejaKursi) objectNearSim;
                                mejakursi.makan(this, makanan);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (input.equals("Mandi") && (firstWord.equals("Shower"))) {
                            Shower shower = (Shower) objectNearSim;
                            shower.mandi(this);
                        } else if (input.equals("Nonton TV") && (firstWord.equals("TV"))) {
                            System.out.println("Masukkan durasi (dalam detik):");
                            try {
                                int duration = Integer.parseInt(actionScanner.nextLine());
                                TV tv = (TV) objectNearSim;
                                tv.nontonTV(this, duration);
                            } catch (NumberFormatException e) {
                                System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                            }
                        } else if (input.equals("Cuci Tangan") && (firstWord.equals("Wastafel"))) {
                            Wastafel wastafel = (Wastafel) objectNearSim;
                            wastafel.cuciTangan(this);
                        }
                    }
            }
        } else {
            String firstWord = getFirstWord(objectNameNearSim);
            String answer;
            Thing object = objectNearSim;
            switch (firstWord) {
                case ("Kasur"):
                    System.out.println("Sim bisa melakukan Sleep. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        // Do Action
                        System.out.println("Durasi harus lebih dari 180 detik dan kelipatan 240");
                        System.out.println("Masukkan durasi (dalam detik):");
                        int duration = Integer.parseInt(actionScanner.nextLine());
                        try {
                            if (duration >= 180) {
                                Kasur kasur = (Kasur) object;
                                kasur.Sleeping(this, duration);
                            } else {
                                System.out.println("Durasi minimal 3 menit (180 detik)!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                        }
                    }
                    break;
                case ("Cermin"):
                    System.out.println("Sim bisa melakukan Mirror. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        Cermin currentCermin = (Cermin) object;
                        currentCermin.bercermin(this);
                    }
                    break;
                case ("Jam"):
                    System.out.println("Sim bisa melakukan See Time. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        // Do Action
                        Jam jam = (Jam) object;
                        jam.lihatWaktu(this);
                    }
                    break;
                case ("Kompor"):
                    System.out.println("Sim bisa melakukan Cook. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        // Do Action
                        if (this.getInventory().printListIngredient()) {
                            Kompor kompor = (Kompor) object;
                            if (kompor.checkBahanMasak(this.getInventory())) {
                                System.out.println("Masukkan nama makanan yang ingin dimasak: ");
                                String namaMakanan = actionScanner.nextLine();
                                kompor.Cooking(this, namaMakanan);
                            }
                        }
                    }
                    break;
                case ("Lukisan"):
                    System.out.println("Sim bisa melakukan View. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        // Do Action
                        System.out.println("Durasi harus kelipatan 20");
                        System.out.println("Masukkan durasi (dalam detik):");
                        try {
                            int duration = Integer.parseInt(actionScanner.nextLine());
                            Lukisan lukisan = (Lukisan) object;
                            lukisan.lihatLukisan(this, duration);
                        } catch (NumberFormatException e) {
                            System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                        }
                    }
                    break;
                case ("Meja"):
                    System.out.println("Sim bisa melakukan Eat. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        try {
                            this.getInventory().printListMakanan();
                            System.out.println("Masukkan nama makanan yang ingin dimakan: ");
                            String namaMakanan = capitalizeEachWord(actionScanner.nextLine());
                            Item makanan = getInventory().getItem(namaMakanan);
                            if (makanan instanceof Ingredient) {
                                makanan = (Ingredient) makanan;
                            } else if (makanan instanceof Food) {
                                makanan = (Food) makanan;
                            }
                            MejaKursi mejakursi = (MejaKursi) objectNearSim;
                            mejakursi.makan(this, makanan);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case ("Shower"):
                    System.out.println("Sim bisa melakukan Shower. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        // Do Action
                        Shower shower = (Shower) object;
                        shower.mandi(this);
                    }
                    break;
                case ("Toilet"):
                    System.out.println("Sim bisa melakukan Pee. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        System.out.println("Durasi harus lebih dari 10 detik dan kelipatan 10");
                        System.out.print("Masukkan durasi (dalam detik): ");
                        try {
                            int durasiPee = Integer.parseInt(actionScanner.nextLine());
                            if (durasiPee >= 10) {
                                Toilet toilet = (Toilet) object;
                                toilet.buangAir(this, durasiPee);
                            } else {
                                System.out.println("Durasi minimal 10 detik!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                        }
                    }
                    break;
                case ("TV"):
                    System.out.println("Sim bisa melakukan Watch TV. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        // Do Action
                        System.out.println("Durasi harus kelipatan 30");
                        System.out.println("Masukkan durasi (dalam detik):");
                        try {
                            int duration = Integer.parseInt(actionScanner.nextLine());
                            TV tv = (TV) object;
                            tv.nontonTV(this, duration);
                        } catch (NumberFormatException e) {
                            System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                        }
                    }
                    break;
                case ("Wastafel"):
                    System.out
                            .println("Sim bisa melakukan Wash Hands. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    while (!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N")) {
                        System.out.printf("Input tidak valid! (Y/N) : ");
                        answer = actionScanner.next();
                    }
                    if (answer.toUpperCase().equals("Y")) {
                        // Do Action
                        Wastafel wastafel = (Wastafel) object;
                        wastafel.cuciTangan(this);
                    }
                    break;
                case (""):
                    System.out.println();
            }
        }
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

    private String capitalizeFirstLetter(String str) {
        String capitalized = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        return capitalized;
    }

    private String capitalizeEachWord(String str) {
        String[] strArray = str.split("\\W+");
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = capitalizeFirstLetter(strArray[i]);
        }
        String finalString = String.join(" ", strArray);
        return finalString;
    }
}