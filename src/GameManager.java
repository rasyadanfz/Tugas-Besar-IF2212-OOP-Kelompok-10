package src;

import java.util.*;

import src.Exceptions.ItemNotFoundException;
import src.Thing.Cermin;

public class GameManager {
    private World world;
    private ArrayList<Sim> simList;
    private Timer worldTimer;
    private Sim activeSim;
    private int houseCount;
    private int kasurCount = 0;

    public GameManager() {
        world = World.getWorld();
        simList = new ArrayList<Sim>();
        worldTimer = world.getTimer();
        houseCount = 0;
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Sim> getSimList() {
        return simList;
    }

    public Timer getWorldTimer() {
        return worldTimer;
    }

    public Sim getSim(String namaSim) {
        Iterator<Sim> simIterator = simList.iterator();
        boolean found = false;
        Sim targetSim = null;
        while (!found && simIterator.hasNext()) {
            targetSim = simIterator.next();
            if (targetSim.getNamaLengkap().equals(namaSim)) {
                found = true;
            } else {
                targetSim = null;
            }
        }
        return targetSim;
    }

    public Sim getActiveSim() {
        return activeSim;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getKasurCount() {
        return kasurCount;
    }

    public void printSimList() {
        int i = 1;
        System.out.println("Daftar Sim dalam Game: ");
        for (Sim s : simList) {
            System.out.println(i + ". " + s.getNamaLengkap());
        }
    }

    // Setter dan Adder
    public void setActiveSim(Sim sim) {
        activeSim = sim;
    }

    public void addSim(String sim) {
        Sim newSim = new Sim(sim);
        this.simList.add(newSim);
        setActiveSim(newSim);
    }

    public void addNewHouse(Sim sim) {
        String kodeRumahBaru = "H" + (houseCount + 1);
        while (Objects.isNull(world.getHouse(kodeRumahBaru))) {
            try {
                world.addHouse(new Random().nextInt(64) + 1, new Random().nextInt(64) + 1, kodeRumahBaru);
            } catch (Exception e) {
            } finally {
                houseCount++;
            }
        }
    }

    public void addNewHouse(Sim sim, int x, int y) {
        String kodeRumahBaru = "H" + (houseCount + 1);
        try {
            world.addHouse(x, y, kodeRumahBaru);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            houseCount++;
        }
    }

    // Menu
    public void help() {
        System.out.println("\033[1;32mThis is the help information for the Sim-Plicity.");

        System.out.println(
                "\033[1;91m====================================================================================================================================================================");

        System.out.println(
                "\033[1;91m ______   _______  _______  ___   _  ______   ___   _______  _______  ___     _______  _______  ______   __   __  _______  ___   __    _  _______  __    _ ");
        System.out.println(
                "\033[1;91m|      | |       ||       ||   | | ||    _ | |   | |       ||       ||   |   |       ||       ||    _ | |  |_|  ||   _   ||   | |  |  | ||   _   ||  |  | |");
        System.out.println(
                "\033[1;91m|  _    ||    ___||  _____||   |_| ||   | || |   | |    _  ||  _____||   |   |    _  ||    ___||   | || |       ||  |_|  ||   | |   |_| ||  |_|  ||   |_| |");
        System.out.println(
                "\033[1;91m| | |   ||   |___ | |_____ |      _||   |_|| |   | |   |_| || |_____ |   |   |   |_| ||   |___ |   |_|| |       ||       ||   | |       ||       ||       |");
        System.out.println(
                "\033[1;91m| |_|   ||    ___||_____  ||     |_ |    __ ||   | |    ___||_____  ||   |   |    ___||    ___||    __ ||       ||       ||   | |  _    ||       ||  _    |");
        System.out.println(
                "\033[1;91m|       ||   |___  _____| ||    _  ||   |  |||   | |   |     _____| ||   |   |   |    |   |___ |   |  ||| ||_|| ||   _   ||   | | | |   ||   _   || | |   |");
        System.out.println(
                "\033[1;91m|______| |_______||_______||___| |_||___|  |||___| |___|    |_______||___|   |___|    |_______||___|  |||_|   |_||__| |__||___| |_|  |__||__| |__||_|  |__|\n");

        System.out.println(
                "\033[1;31mSim-plicity adalah permainan yang ingin dimiliki oleh Indra dan Doni. Game ini adalah permainan tentang kehidupan sehari-hari sims, karakter orang dalam game ini. \nSelain sim, game ini memiliki entitas world sebagai dunia virtual, rumah sebagai tempat tinggal sim, ruangan, dan objek-objek. \nSim juga memiliki kesejahteraan yang berparameter mood, kekenyangan, dan kesehatan yang bisa mempengaruhi umur sim. \nSim juga memiliki pekerjaan dan dapat melakukan aksi yang bisa mempengaruhi parameter kesejahteraannya.");
        System.out.println(
                "\033[1;31mGame ini diprogram menggunakan bahasa Java dengan berbasis Command Line Interface. \nPertama, pemain dapat menciptakan sim. Setiap pembuatan satu sim, user akan mendapatkan satu rumah baru berukuran 6x6.  \nUser dapat berpindah-pindah sim untuk memainkannya. Untuk cara bermain, akan dijelaskan lebih rinci pada bagian cara bermain. \n");
        System.out.println(
                "\033[1;91m====================================================================================================================================================================\n\n\n\n");

        System.out.println(
                "\033[1;93m====================================================================================================================================================================");
        System.out.println(
                "\033[1;93m _______  _______  ______   _______    _______  _______  ______   __   __  _______  ___   __    _ ");
        System.out.println(
                "\033[1;93m|       ||   _   ||    _ | |   _   |  |  _    ||       ||    _ | |  |_|  ||   _   ||   | |  |  | |");
        System.out.println(
                "\033[1;93m|       ||  |_|  ||   | || |  |_|  |  | |_|   ||    ___||   | || |       ||  |_|  ||   | |   |_| |");
        System.out.println(
                "\033[1;93m|       ||       ||   |_|| |       |  |       ||   |___ |   |_|| |       ||       ||   | |       |");
        System.out.println(
                "\033[1;93m|      _||       ||    __ ||       |  |  _   | |    ___||    __ ||       ||       ||   | |  _    |");
        System.out.println(
                "\033[1;93m|     |_ |   _   ||   |  |||   _   |  | |_|   ||   |___ |   |  ||| ||_|| ||   _   ||   | | | |   |");
        System.out.println(
                "\033[1;93m|_______||__| |__||___|  |||__| |__|  |_______||_______||___|  |||_|   |_||__| |__||___| |_|  |__|"
                        + "\n");

        System.out.println(
                "\033[1;33m1. Buatlah Sim baru! Nantinya, kamu juga bisa memilih sim yang ingin kamu mainkan.");
        System.out.println(
                "\033[1;33m2. Setiap kamu membuat Sim baru, kondisi kesejahteraan kamu (kekenyangan, mood, dan kesehatan) akan otomatis di-set 80 dan kamu juga akan diberi uang awal sebesar 100.");
        System.out.println(
                "\033[1;33m3. Selain itu, kamu juga akan mendapatkan pekerjaan secara random, rumah yang berisi ruangan berdimensi 6x6, dan barang-barang default yang otomatis ada di inventory kamu, seperti kasur, toilet, kompor, kursi, meja, dan jam.");
        System.out.println(
                "\033[1;33m4. Namun, berhati-hatilah karena jika salah satu saja kondisi kesejahteraanmu bernilai 0, kamu bisa mati X_X");
        System.out.println("\033[1;33m5. Sim juga akan otomatis memiliki pekerjaan secara random");
        System.out.println(
                "\033[1;33m6. Kamu dapat menjalankan aksi dengan Sim kamu, loh! Berikut rincian dan penjelasannya :");
        System.out.println(
                "      \033[1;33ma) Aksi Aktif: Aksi yang memerlukan waktu dan keterlibatan Sim secara langsung");
        System.out.println(
                "      \033[1;33mb) ksi Upgrade: Aksi yang memerlukan waktu tetapi tidak memerlukan keterlibatan Sim secara langsung");
        System.out.println("      \033[1;33mc) Aksi Non-aktif: yang tidak memerlukan waktu");
        System.out.println(
                "      \033[1;33md) ksi Penambahan Sim: Aksi untuk menciptakan Sim baru beserta house yang akan didapatkannya.\n");

        System.out.println("\033[1;34mAksi Aktif : ");
        System.out.println("   \033[1;34ma) Kerja");
        System.out.println("   \033[1;34mb) Olahraga");
        System.out.println("   \033[1;34mc) Sleep");
        System.out.println("   \033[1;34md) Eating");
        System.out.println("   \033[1;34me) Cook");
        System.out.println("   \033[1;34mf) Visit");
        System.out.println("   \033[1;34mg) Pee");
        System.out.println("   \033[1;34mh) Watching TV");
        System.out.println("   \033[1;34mi) Bath");
        System.out.println("   \033[1;34mj) Washing Hand\n\n");

        System.out.println("\033[1;35mAksi Upgrade : ");
        System.out.println("   \033[1;35ma) Upgrade Rumah");
        System.out.println("   \033[1;35mb) Buy Item");
        System.out.println("   \033[1;35mc) Sell Barang\n\n");

        System.out.println("\033[1;36mAksi Non-Aktif : ");
        System.out.println("   \033[1;36ma) lookPainting");
        System.out.println("   \033[1;36mb) Mirror");
        System.out.println("   \033[1;36mc) moveRuangan");
        System.out.println("   \033[1;36md) seeInventory");
        System.out.println("   \033[1;36me) installBarang");
        System.out.println("   \033[1;36mf) ambilBarang");
        System.out.println("   \033[1;36mg) seeTime\n\n");

        System.out.println(
                "\033[1;33m7. Sim dapat membeli beberapa barang yang disediakan di dalam game. Barang-barang yang dapat dibeli adalah : ");
        System.out.println("      \033[1;30ma) Makanan");
        System.out.println("      \033[1;30mb)Kasur Single");
        System.out.println("      \033[1;30mc)Kasur Queen Size");
        System.out.println("      \033[1;30md)Kasur King Size");
        System.out.println("      \033[1;30me)Toilet");
        System.out.println("      \033[1;30mf)Kompor Gas");
        System.out.println("      \033[1;30mg)Kompor Listrik");
        System.out.println("      \033[1;30mh)Meja dan Kursi");
        System.out.println("      \033[1;30mi)Jam");
        System.out.println("      \033[1;30mj)TV");
        System.out.println("      \033[1;30mk)Shower");
        System.out.println("      \033[1;30ml)Lukisan ");
        System.out.println("      \033[1;30mm)Toilet ");
        System.out.println("      \033[1;30mn)Wastafel ");
        System.out.println("      \033[1;30mo)Cermin ");

        System.out.println(
                "\033[1;93m====================================================================================================================================================================\033[0;39m");
    }

    public void exit(String answer) {
        if (answer.equals("Y")) {
            System.out.println("Terima kasih telah bermain! \n Sampai jumpa lagi!");
            System.exit(0);
        } else {
            System.out.println("Kembali ke menu utama...");
        }
    }

    public void viewSimInfo() {
        System.out.println("SIM INFO:");
        System.out.println("Nama Sim: " + getActiveSim().getNamaLengkap());
        System.out.println("Pekerjaan: " + getActiveSim().getPekerjaan());
        System.out.println("Kesehatan: " + getActiveSim().getKesehatan());
        System.out.println("Kekenyangan: " + getActiveSim().getKekenyangan());
        System.out.println("Mood: " + getActiveSim().getMood());
        System.out.println("Uang: " + getActiveSim().getUang());
    }

    public void viewCurrentLocation() {
        System.out.println("Current Location : ");
        System.out.println("Rumah : " + getActiveSim().getCurrentHouse().getKodeRumah());
        System.out.println("Ruangan : " + getActiveSim().getCurrentRoom().getNamaRuangan());
        System.out.println("Posisi : " + getActiveSim().getCurrentPos().toString());
        System.out.println("Peta Rumah: ");
        getActiveSim().getCurrentHouse().printPetaRumah();
        System.out.println("Peta Ruangan: ");
        getActiveSim().getCurrentRoom().printPetaRuangan(getActiveSim());
    }

    public void viewInventory() {
        getActiveSim().seeInventory();
    }

    public void upgradeRumah(String arah) {
        // Sim tidak sedang di rumah
        if (Objects.isNull(getActiveSim().getCurrentHouse())) {
            System.out.println("Sim tidak dalam suatu rumah");
        } else {
            try {
                Room newRoom = new Room("R0" + getActiveSim().getCurrentHouse().getRoomCount(),
                        getActiveSim().getCurrentHouse());
                getActiveSim().upgradeRumah(getActiveSim().getCurrentRoom(), newRoom, arah);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void moveRoom(String input) {
        // Sim tidak sedang di rumah
        if (Objects.isNull(getActiveSim().getCurrentHouse())) {
            System.out.println("Sim tidak dalam suatu rumah");
        } else {

            if (!input.equals(getActiveSim().getCurrentRoom().getNamaRuangan())) {
                Room targetRoom = getActiveSim().getCurrentHouse().getRoom(input);
                if (!Objects.isNull(targetRoom)) {
                    getActiveSim().moveRuangan(targetRoom);
                } else {
                    System.out.println("Ruangan dengan kode " + input + " tidak ada");
                }
            } else {
                System.out.println("Sim sudah ada di ruangan "
                        + getActiveSim().getCurrentRoom().getNamaRuangan() + "!");
            }
        }
    }

    public void editRoom() {
        // Sim tidak sedang di rumah
        if (Objects.isNull(getActiveSim().getCurrentHouse())) {
            System.out.println("Sim tidak dalam suatu rumah!");
        }
        // Sim tidak di dalam ruangan
        else if (Objects.isNull(getActiveSim().getCurrentRoom())) {
            System.out.println("Sim tidak dalam suatu ruangan!");
        } else {
            // TODO : Insert Method Edit Ruangan Here
            // System.out.printf("Opsi Edit:\n1.Beli barang baru\n2.Pemindahan barang");
        }
    }

    public void changeSim(String input, Scanner inputScanner) {
        printSimList();
        System.out.print("Mau ganti ke Sim mana? Ketik namanya : ");
        input = inputScanner.nextLine();
        // Validasi nama Sim
        Sim currentSim = getSim(input);
        while (currentSim == null) {
            System.out.print("Nama Sim salah! Coba input ulang : ");
            input = inputScanner.nextLine();
            currentSim = getSim(input);
        }
        setActiveSim(currentSim);
    }

    public void listObject() {
        getActiveSim().getCurrentRoom().printPlacedObject();
    }

    public void goToObject(int x, int y) {
        Point toCheck = new Point(x, y);
        boolean found = false;
        for (String key : getActiveSim().getCurrentRoom().getPlacedObject().keySet()) {
            for (Point p : getActiveSim().getCurrentRoom().getPlacedObject().get(key)) {
                if (toCheck.equals(p)) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            getActiveSim().move(x, y);
            actions();
        } else {
            System.out.println("Objek dengan posisi " + toCheck.toString() + " tidak ditemukan");
        }

    }

    public void pasangBarang(String itemName, int x, int y) throws ItemNotFoundException, Exception {
        getActiveSim().installBarang(capitalizeEachWord(itemName), x, y);
    }

    public void actions() {
        Scanner actionScanner = new Scanner(System.in);
        // Get list of objects di lokasi sim dan di 1 posisi sekitar sim
        ArrayList<String> objectsNearSim = new ArrayList<String>();
        HashMap<String, ArrayList<Point>> listOfObjects = getActiveSim().getCurrentRoom().getPlacedObject();
        for (String key : listOfObjects.keySet()) {
            for (Point p : listOfObjects.get(key))
                if (getActiveSim().getCurrentPos().equals(p)) {
                    if (!objectsNearSim.contains(key)) {
                        objectsNearSim.add(key);
                    }
                }
        }

        for (String obj : objectsNearSim) {
            String firstWord = getFirstWord(obj);
            String answer;
            switch (firstWord) {
                case ("Kasur"):
                    System.out.println("Sim bisa melakukan Sleep. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("Cermin"):
                    System.out.println("Sim bisa melakukan Mirror. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        Cermin.bercermin(activeSim);
                    }
                    break;
                case ("Jam"):
                    System.out.println("Sim bisa melakukan See Time. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("Kompor"):
                    System.out.println("Sim bisa melakukan Cook. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("Lukisan"):
                    System.out.println("Sim bisa melakukan View. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("Meja"):
                    System.out.println("Sim bisa melakukan Eat. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("Shower"):
                    System.out.println("Sim bisa melakukan Shower. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("Toilet"):
                    System.out.println("Sim bisa melakukan Pee. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("TV"):
                    System.out.println("Sim bisa melakukan Watch TV. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
                case ("Wastafel"):
                    System.out
                            .println("Sim bisa melakukan Wash Hands. Apakah anda ingin melakukan aksi tersebut? (Y/N)");
                    answer = actionScanner.nextLine();
                    if (answer.equals("Y")) {
                        // Do Action
                    }
                    break;
            }
        }
    }

    public void runTime() {
        Sim firstActiveSim = null;
        Iterator<Sim> simIterator = simList.iterator();
        boolean stop = false;
        while (!stop && simIterator.hasNext()) {
            firstActiveSim = simIterator.next();
            if (firstActiveSim.getStatus().equals("active")) {
                stop = true;
            } else if (!simIterator.hasNext()) {
                firstActiveSim = null;
            }
        }

        if (!Objects.isNull(firstActiveSim)) {
            worldTimer.reduceTime(1, this);
        }
    }

    // Helper Method
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

    private String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) {
            return text.substring(0, index).trim();
        } else {
            return text;
        }
    }

}
