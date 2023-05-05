package src;

import src.Exceptions.DurationNotValidException;
import src.Exceptions.ItemNotFoundException;
import src.Exceptions.MoneyNotEnoughException;
import src.Thing.*;

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

    public String getPekerjaan() {
        return String.format(pekerjaan.getNamaPekerjaan() + " dengan gaji " + pekerjaan.getGaji());
    }

    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public boolean getInActiveAction() {
        return inActiveAction;
    }

    public Delivery getItemDelivery() {
        return delivery;
    }

    public void setItemDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getSisaWaktuUpgrade() {
        return sisaWaktuUpgradeRumah;
    }

    public void setSisaWaktuUpgrade(int newValue) {
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

    public void decreaseActionDuration(Action a) {
        a.decreaseDuration();
        world.getTimer().increaseTime();
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
        System.out.printf("Nama : %s\n", getNamaLengkap());
        System.out.printf("Status : %s\n", getStatus());
        System.out.printf("Pekerjaan : %s\n", getPekerjaan());
        System.out.println("Kesejahteraan Sim :");
        System.out.printf("Uang : %d\n", getUang());
        System.out.printf("Kekenyangan : %d\n", getKekenyangan());
        System.out.printf("Kesehatan : %d\n", getKesehatan());
        System.out.printf("Mood : %d\n", getMood());
        System.out.println("Inventory Sim : ");
        seeInventory();
        System.out.printf("Nomor Rumah saat ini : %s\n", getCurrentHouse().getKodeRumah());
        System.out.printf("Ruangan saat ini : %s\n", getCurrentRoom().getNamaRuangan());
        System.out.printf("Koordinat : %s\n", getCurrentPos().toString());
    }

    public void viewCurrentLocation() {
        System.out.println("Current Location : ");
        System.out.println("Rumah : " + getCurrentHouse().getKodeRumah());
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

    public void installBarang(String namaBarang, int x, int y) throws ItemNotFoundException, Exception {
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
            currentRoom.placeItem(thing, x, y);
            inventory.removeItem(thing.getNama());
            inventory.getItemContainer().remove(thing);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void seeInventory() {
        inventory.printItems();
    }

    public void visit(House destHouse) throws Exception {
        if (destHouse == currentHouse) {
            throw new Exception("Kamu sudah berada di rumah ini");
        } else {
            currentHouse = destHouse;
            currentRoom = currentHouse.getRoom("R001");
            currentPos = new Point(1, 1);
        }
    }

    public void setUang(int newValue) {
        uang = newValue;
    }

    public void olahraga(int duration) {
        System.out.println("Sim sedang melakukan olahraga..");
        try {
            int counter = 0;
            while (counter != duration) {
                counter++;
                Thread.sleep(1000);
                world.getTimer().increaseTime();
                if (counter % 20 == 0) {
                    changeKesehatan(+5);
                    changeMood(+10);
                    changeKekenyangan(-5);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void kerja(int duration) {
        System.out.println("Sim sedang melakukan kerja..");
        workTime += duration;

        if (workTime % 240 == 0) { // Dapat gaji kalau sudah bekerja selama 4 menit
            workTime = 0; // Counternya reset
            uang += pekerjaan.getGaji();
        }

        try {
            int counter = 0;
            while (counter != duration) {
                counter++;
                Thread.sleep(1000);
                world.getTimer().increaseTime();
                if (counter % 30 == 0) {
                    changeMood(-10);
                    changeKekenyangan(-10);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buyFurniture() {
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
            // TODO : Insert Method Edit Ruangan Here
            Scanner scanner = InputScanner.getInputScanner().getScanner();
            System.out.printf("Opsi Edit:\n1.Beli barang baru\n2.Pindah barang\n");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("BELI BARANG BARU")) {
                System.out.println("Pilih barang yang mau dibeli :");
                System.out.println("1. Furnitur");
                System.out.println("2. Bahan Makanan");
                input = scanner.nextLine().toUpperCase();
                if (input.equals("Furnitur")) {

                } else if (input.equals("Bahan Makanan")) {

                } else {

                }
            } else if (input.equals("PINDAH BARANG")) {

            } else {
                System.out.println("Perintah tidak dikenali! Aksi edit room dibatalkan!");
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
        } else {
            Room oldRoom = getCurrentRoom();
            String roomNumber;
            if (getCurrentHouse().getRoomCount() < 10) {
                roomNumber = "00" + getCurrentHouse().getRoomCount();
            } else if (getCurrentHouse().getRoomCount() >= 10 && getCurrentHouse().getRoomCount() < 99) {
                roomNumber = "0" + getCurrentHouse().getRoomCount();
            } else {
                roomNumber = Integer.toString(getCurrentHouse().getRoomCount());
            }
            Room newRoom = new Room("R" + roomNumber, getCurrentHouse());
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
                            int waktuMulai = Timer.getTimer().getTotalTime();
                            GameManager.getGameManager().getActiveSim().setSisaWaktuUpgrade(waktuUpgrade);
                            while (upgrade) {
                                if (waktuMulai + waktuUpgrade <= Timer.getTimer().getTotalTime()) {
                                    upgrade = false;
                                } else {
                                    GameManager.getGameManager().getActiveSim()
                                            .setSisaWaktuUpgrade(
                                                    waktuMulai + waktuUpgrade - Timer.getTimer().getTotalTime());
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
        wastafel.cuciTangan(this);
    }

    public void mirroring(Cermin cermin) {
        cermin.bercermin(this);
    }

    public void lookPainting(Lukisan lukisan, int duration) {
        lukisan.lihatLukisan(this, duration);
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
            input = actionScanner.nextLine();
            if (input.equals("Work")) {
                System.out.print("Masukkan durasi bekerja: ");
                int durasi = Integer.parseInt(actionScanner.nextLine());
                while (durasi == 0 || durasi % 120 != 0) {
                    System.out.println("Durasi kerja tidak valid! Harap lakukan input ulang dengan kelipatan 120");
                    System.out.print("Masukkan durasi bekerja: ");
                    durasi = Integer.parseInt(actionScanner.nextLine());
                }
                this.kerja(durasi);
                notSleepYet += durasi;
                if (haveEat) notPeeYet += durasi;
                getNegativeEffect();
            } else if (input.equals("Olahraga")) {
                System.out.print("Masukkan durasi olahraga: ");
                int durasi = Integer.parseInt(actionScanner.nextLine());
                while (durasi == 0 || durasi % 20 != 0) {
                    System.out.println("Durasi olahraga tidak valid! Harap lakukan input ulang dengan kelipatan 20");
                    System.out.print("Masukkan durasi olahraga: ");
                    durasi = Integer.parseInt(actionScanner.nextLine());
                }
                this.olahraga(durasi);
                notSleepYet += durasi;
                if (haveEat) notPeeYet += durasi;
                getNegativeEffect();
            } else if (input.equals("Visit")) {
                System.out.println("Masukkan rumah yang ingin dikunjungi: ");
                String destHouseCode = actionScanner.nextLine();
                House destHouse = world.getHouse(destHouseCode);

                if (destHouse != null) {
                    try {
                        int x1 = this.getCurrentHouse().getLokasi().getX();
                        int y1 = this.getCurrentHouse().getLokasi().getY();
                        int x2 = destHouse.getLokasi().getX();
                        int y2 = destHouse.getLokasi().getY();
                        double durasiPergi = Math.sqrt((x2-x1)^2 + (y2-y1)^2);
                        visitTime += durasiPergi;
                        if (visitTime >= 30) {
                            visitTime -= 30;
                            changeMood(10);
                            changeKekenyangan(-10);
                        }
                        this.visit(destHouse);
                        notSleepYet += durasiPergi;
                        if (haveEat) notPeeYet += durasiPergi;
                        getNegativeEffect();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Rumah dengan kode " + destHouseCode + " tidak ditemukan!");
                }
            } else {
                if (!Objects.isNull(objectNameNearSim)) {
                    String firstWord = getFirstWord(objectNameNearSim);
                    input = input.toUpperCase();
                    if (input.equals("Sleep") && (firstWord.equals("Kasur"))) {
                        System.out.println("Masukkan durasi (dalam detik):");
                        int duration = Integer.parseInt(actionScanner.nextLine());
                        while (duration < 180) {
                            System.out.println("Durasi tidur tidak valid! Tidur minimal 3 menit (180 detik)");
                            duration = Integer.parseInt(actionScanner.nextLine());
                        }
                        Kasur kasur = (Kasur) objectNearSim;
                        kasur.Sleeping(this, duration);
                        notSleepYet = 0;
                        if(haveEat) notPeeYet += duration;
                        getNegativeEffect();
                    } else if (input.equals("Bercermin") && (firstWord.equals("Cermin"))) {
                        Cermin currentCermin = (Cermin) objectNearSim;
                        currentCermin.bercermin(this);
                    } else if (input.equals("Lihat Jam") && (firstWord.equals("Jam"))) {
                        Jam jam = (Jam) objectNearSim;
                        jam.lihatWaktu(this);
                    } else if (input.equals("Pee") && firstWord.equals("Toilet")) {
                        Toilet toilet = (Toilet) objectNearSim;
                        System.out.print("Masukkan durasi (dalam detik): ");
                        int durasiPee = Integer.parseInt(actionScanner.nextLine());
                        toilet.buangAir(this, durasiPee);
                        notSleepYet += durasiPee;
                        if (haveEat) notPeeYet = 0; haveEat = false;
                        getNegativeEffect();
                    } else if (input.equals("Cook") && (firstWord.equals("Kompor"))) {
                        this.getInventory().printListIngredient();
                        Kompor kompor = (Kompor) objectNearSim;
                        if (kompor.checkBahanMasak(this.getInventory())) {
                            System.out.println("Masukkan nama makanan yang ingin dimasak: ");
                            String namaMakanan = actionScanner.nextLine();
                            kompor.Cooking(this, namaMakanan);
                        }
                    } else if (input.equals("Lihat Lukisan") && (firstWord.equals("Lukisan"))) {
                        System.out.println("Masukkan durasi (dalam detik):");
                        int duration = Integer.parseInt(actionScanner.nextLine());
                        Lukisan lukisan = (Lukisan) objectNearSim;
                        lukisan.lihatLukisan(this, duration);
                        notSleepYet += duration;
                        if (haveEat) notPeeYet += duration;
                        getNegativeEffect();
                    } else if (input.equals("Makan") && (firstWord.equals("Meja"))) {
                        try {
                            this.getInventory().printListMakanan();
                            System.out.println("Masukkan nama makanan yang ingin dimakan: ");
                            String namaMakanan = actionScanner.nextLine();
                            MejaKursi mejakursi = (MejaKursi) objectNearSim;
                            mejakursi.makan(this, (Food) (this.getInventory().getItem(namaMakanan)));
                            notSleepYet += 30;
                            if (!haveEat) haveEat = true;
                            getNegativeEffect();
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (input.equals("Mandi") && (firstWord.equals("Shower"))) {
                        Shower shower = (Shower) objectNearSim;
                        shower.mandi(this);
                        notSleepYet += 30;
                        if (haveEat) notPeeYet += 30;
                        getNegativeEffect();
                    } else if (input.equals("Nonton TV") && (firstWord.equals("TV"))) {
                        System.out.println("Masukkan durasi (dalam detik):");
                        int duration = Integer.parseInt(actionScanner.nextLine());
                        TV tv = (TV) objectNearSim;
                        tv.nontonTV(this, duration);
                        notSleepYet += duration;
                        if (haveEat) notPeeYet += duration;
                        getNegativeEffect();
                    } else if (input.equals("Cuci Tangan") && (firstWord.equals("Wastafel"))) {
                        Wastafel wastafel = (Wastafel) objectNearSim;
                        wastafel.cuciTangan(this);
                        notSleepYet += 5;
                        if (haveEat) notPeeYet += 5;
                        getNegativeEffect();
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
                    if (answer.equals("Y")) {
                        // Do Action
                        System.out.println("Durasi harus lebih dari 180 detik dan kelipatan 240");
                        System.out.println("Masukkan durasi (dalam detik):");
                        int duration = Integer.parseInt(actionScanner.nextLine());
                        if (duration >= 180) {
                            Kasur kasur = (Kasur) object;
                            kasur.Sleeping(this, duration);
                        } else {
                            System.out.println("Durasi minimal 3 menit (180 detik)!");
                        }
                    }
                    break;
                case ("Cermin"):
                    System.out.println("Sim bisa melakukan Mirror. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        Cermin currentCermin = (Cermin) object;
                        currentCermin.bercermin(this);
                    }
                    break;
                case ("Jam"):
                    System.out.println("Sim bisa melakukan See Time. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                        Jam jam = (Jam) object;
                        jam.lihatWaktu(this);
                    }
                    break;
                case ("Kompor"):
                    System.out.println("Sim bisa melakukan Cook. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                        if (this.getInventory().printListIngredient()) {
                            Kompor kompor = (Kompor) object;
                            if (kompor.checkBahanMasak(this.getInventory())) {
                                System.out.println("Masukkan nama makanan yang ingin dimasak: ");
                                String namaMakanan = actionScanner.nextLine();
                                kompor.Cooking(this, namaMakanan);
                            }
                        }
                        ;
                    }
                    break;
                case ("Lukisan"):
                    System.out.println("Sim bisa melakukan View. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                        System.out.println("Durasi harus kelipatan 20");
                        System.out.println("Masukkan durasi (dalam detik):");
                        int duration = Integer.parseInt(actionScanner.nextLine());
                        Lukisan lukisan = (Lukisan) object;
                        lukisan.lihatLukisan(this, duration);
                    }
                    break;
                case ("Meja"):
                    System.out.println("Sim bisa melakukan Eat. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                        try {
                            this.getInventory().printListMakanan();
                            System.out.println("Masukkan nama makanan yang ingin dimakan: ");
                            String namaMakanan = actionScanner.nextLine();
                            MejaKursi mejakursi = (MejaKursi) object;
                            mejakursi.makan(this,
                                    (Food) (this.getInventory().getItem(namaMakanan)));
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case ("Shower"):
                    System.out.println("Sim bisa melakukan Shower. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                        Shower shower = (Shower) object;
                        shower.mandi(this);
                    }
                    break;
                case ("Toilet"):
                    System.out.println("Sim bisa melakukan Pee. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        System.out.println("Durasi harus lebih dari 10 detik dan kelipatan 10");
                        System.out.print("Masukkan durasi (dalam detik): ");
                        int durasiPee = Integer.parseInt(actionScanner.nextLine());
                        if (durasiPee >= 10) {
                            Toilet toilet = (Toilet) object;
                            toilet.buangAir(this, durasiPee);
                        } else {
                            System.out.println("Durasi minimal 10 detik!");
                        }
                    }
                    break;
                case ("TV"):
                    System.out.println("Sim bisa melakukan Watch TV. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                        System.out.println("Durasi harus kelipatan 30");
                        System.out.println("Masukkan durasi (dalam detik):");
                        int duration = Integer.parseInt(actionScanner.nextLine());
                        TV tv = (TV) object;
                        tv.nontonTV(this, duration);
                    }
                    break;
                case ("Wastafel"):
                    System.out
                            .println("Sim bisa melakukan Wash Hands. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                        Wastafel wastafel = (Wastafel) object;
                        wastafel.cuciTangan(this);
                    }
                    break;
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