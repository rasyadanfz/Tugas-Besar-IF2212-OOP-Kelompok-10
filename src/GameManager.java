package src;

import java.util.*;

import src.Exceptions.*;
import src.Thing.*;

public class GameManager {
    private World world;
    private ArrayList<Sim> simList;
    private Timer worldTimer;
    private Sim activeSim;
    private int houseCount;
    private static boolean isCheatEnabled = false;
    private Scanner scanner = InputScanner.getInputScanner().getScanner();
    private static GameManager gameManager = new GameManager();
    private Cheat cheats;

    private GameManager() {
        world = World.getWorld();
        simList = new ArrayList<Sim>();
        worldTimer = world.getTimer();
        houseCount = 0;
        cheats = new Cheat();
    }

    public static GameManager getGameManager() {
        return gameManager;
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
        System.out.println("\033[1;92m█░█ █▀▀ █░░ █▀█");
        System.out.println("\033[1;92m█▀█ ██▄ █▄▄ █▀▀\n\n");

        System.out.println("\033[1;92mThis is the help information for the Sim-Plicity.\n\n");

        System.out.println("\033[1;34m1. GAME DESCRIPTION\n");
        System.out.println("\033[1;34m2. HOW TO PLAY?\n");
        System.out.println("\033[1;34m3. COMMAND LIST\n");
    }

    public void gameDescription() {
        System.out.println(
                "\033[1;91m===================================================================================");

        System.out.println("\033[1;91m█▀▀ ▄▀█ █▀▄▀█ █▀▀   █▀▄ █▀▀ █▀ █▀▀ █▀█ █ █▀█ ▀█▀ █ █▀█ █▄░█");
        System.out.println("\033[1;91m█▄█ █▀█ █░▀░█ ██▄   █▄▀ ██▄ ▄█ █▄▄ █▀▄ █ █▀▀ ░█░ █ █▄█ █░▀█\n\n");

        System.out.println(
                "\033[1;31mSim-plicity adalah permainan yang ingin dimiliki oleh Indra dan Doni. Game ini adalah permainan tentang kehidupan sehari-hari sims, karakter orang dalam game ini. \nSelain sim, game ini memiliki entitas world sebagai dunia virtual, rumah sebagai tempat tinggal sim, ruangan, dan objek-objek. \nSim juga memiliki kesejahteraan yang berparameter mood, kekenyangan, dan kesehatan yang bisa mempengaruhi umur sim. \nSim juga memiliki pekerjaan dan dapat melakukan aksi yang bisa mempengaruhi parameter kesejahteraannya.");
        System.out.println(
                "\033[1;31mGame ini diprogram menggunakan bahasa Java dengan berbasis Command Line Interface. \nPertama, pemain dapat menciptakan sim. Setiap pembuatan satu sim, user akan mendapatkan satu rumah baru berukuran 6x6.  \nUser dapat berpindah-pindah sim untuk memainkannya. Untuk cara bermain, akan dijelaskan lebih rinci pada bagian cara bermain. \n");
        System.out.println(
                "\033[1;91m===================================================================================\n\n\n\n");
    }

    public void howToPlay() {

        System.out.println("\033[1;93m========================================================");
        System.out.println("\033[1;93m█░█ █▀█ █░█░█   ▀█▀ █▀█   █▀█ █░░ ▄▀█ █▄█ ▀█");
        System.out.println("\033[1;93m█▀█ █▄█ ▀▄▀▄▀   ░█░ █▄█   █▀▀ █▄▄ █▀█ ░█░ ░▄\n");

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
        System.out.println("      \033[1;30mo)Cermin \n");

        System.out.println("\033[1;93m========================================================\n\n\n\n");
    }

    public void commandList() {
        System.out.println("\033[1;96m==============================================================");
        System.out.println("\033[1;96m█▀▀ █▀█ █▀▄▀█ █▀▄▀█ ▄▀█ █▄░█ █▀▄   █░░ █ █▀ ▀█▀");
        System.out.println("\033[1;96m█▄▄ █▄█ █░▀░█ █░▀░█ █▀█ █░▀█ █▄▀   █▄▄ █ ▄█ ░█░\n");

        System.out.println("\033[1;36mBerikut adalah daftar command yang dapat digunakan : \n");
        System.out.println("\033[1;36m1. START GAME");
        System.out.println("\033[1;36m2. HELP");
        System.out.println("\033[1;36m3. EXIT");
        System.out.println("\033[1;36m4. VIEW SIM INFO");
        System.out.println("\033[1;36m5. VIEW CURRENT LOCATION");
        System.out.println("\033[1;36m6. VIEW INVENTORY");
        System.out.println("\033[1;36m7. UPGRADE RUMAH");
        System.out.println("\033[1;36m8. MOVE ROOM");
        System.out.println("\033[1;36m9. EDIT ROOM");
        System.out.println("\033[1;36m10. ADD SIM");
        System.out.println("\033[1;36m11. CHANGE SIM");
        System.out.println("\033[1;36m12. LIST OBJECT");
        System.out.println("\033[1;36m13. GO TO OBJECT");
        System.out.println("\033[1;36m14. ACTION\n");

        System.out.println("\033[1;96m==============================================================");
    }

    public boolean getIsCheatEnabled() {
        return isCheatEnabled;
    }

    public void setIsCheatEnabled(String passCode, boolean cheatStatus) {
        if (passCode.equals("nyalaincheatsim123")) {
            isCheatEnabled = cheatStatus;
        } else {
            System.out.println("Passcode menyalakan cheat salah!");
        }
    }

    public void exit() {
        Scanner input = scanner;
        System.out.println("Apakah anda yakin ingin keluar dari game? (Y/N)");
        String answer = input.nextLine();

        if (answer.equals("Y")) {
            System.out.println("Terima kasih telah bermain! \n Sampai jumpa lagi!");
            System.exit(0);
        } else {
            System.out.println("Kembali ke menu utama...");
        }
        input.close();
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

    public void simsAliveCheck() {
        Iterator<Sim> simIterator = simList.iterator();
        Sim currentSim;
        ArrayList<Sim> simsToDelete = new ArrayList<Sim>();
        while (simIterator.hasNext()) {
            currentSim = simIterator.next();
            if (!currentSim.getIsAlive()) {
                simsToDelete.add(currentSim);
            }
        }
        for (Sim s : simsToDelete) {
            // balikin setiap sim di rumahnya sim yang dihapus ke rumah masing-masing
            for (Sim sim : s.getOwnedHouse().getSimInHouse()) {
                sim.resetSimToOwnedHouse();
            }
            // Hapus rumah sim yang dihapus
            world.getDaftarRumah().remove(s.getOwnedHouse());
            simList.remove(s);
        }
    }

    public void removeSimHouse(Sim sim) {
        House houseToRemove = sim.getOwnedHouse();
        for (Sim s : houseToRemove.getSimInHouse()) {

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

    public class Cheat {
        private boolean isTimeSkipEnabled;

        Cheat() {
            isTimeSkipEnabled = false;
        }

        public void killSim(Sim sim) {
            sim.setIsAlive(false);
        }

        public boolean getIsTimeSkipEnabled() {
            return isTimeSkipEnabled;
        }

        public void setIsTimeSkipEnabled(boolean timeSkip) {
            isTimeSkipEnabled = timeSkip;
        }
    }

    public Cheat getCheat() {
        return cheats;
    }
}
