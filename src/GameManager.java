package src;

import java.util.*;

public class GameManager {
    private World world;
    private ArrayList<Sim> simList;
    private Timer worldTimer;
    private Sim activeSim;
    private int houseCount;
    private int kasurCount = 0;

    public GameManager(){
        world = World.getWorld();
        simList = new ArrayList<Sim>();
        worldTimer = world.getTimer();
        houseCount = 0;
    }

    public World getWorld(){
        return world;
    }
    
    public ArrayList<Sim> getSimList(){
        return simList;
    }

    public Timer getWorldTimer(){
        return worldTimer;
    }

    public Sim getSim(String namaSim){
        Iterator<Sim> simIterator = simList.iterator();
        boolean found = false;
        Sim targetSim = null;
        while (!found && simIterator.hasNext()){
            targetSim = simIterator.next();
            if (targetSim.getNamaLengkap().equals(namaSim)){
                found = true;
            }
            else{
                targetSim = null;
            }
        }
        return targetSim;
    }

    public Sim getActiveSim(){
        return activeSim;
    }

    public int getHouseCount(){
        return houseCount;
    }

    public int getKasurCount(){
        return kasurCount;
    }

    public void printSimList(){
        int i = 1;
        System.out.println("Daftar Sim dalam Game: ");
        for (Sim s : simList){
            System.out.println(i + ". " + s.getNamaLengkap());
        }
    }

    // Setter dan Adder
    public void setActiveSim(Sim sim){
        activeSim = sim;
    }

    public void addSim(String sim){
        Sim newSim = new Sim(sim);
        this.simList.add(newSim);
        setActiveSim(newSim);
    }

    public void addNewHouse(Sim sim){
        String kodeRumahBaru = "H" + houseCount;
        while(Objects.isNull(world.getHouse(kodeRumahBaru))){
            try{
                world.addHouse(new Random().nextInt(64) + 1, new Random().nextInt(64) + 1, kodeRumahBaru);
            }
            catch(Exception e){
            }
        }
    }

    public void addNewHouse(Sim sim, int x, int y){
        String kodeRumahBaru = "H" + houseCount;
        try{
            world.addHouse(x, y, kodeRumahBaru);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Menu
    // TODO : Implementasi Help
    public void help(){

        System.out.println("\033[1;92m█░█ █▀▀ █░░ █▀█");
        System.out.println("\033[1;92m█▀█ ██▄ █▄▄ █▀▀\n\n");
        System.out.println("\033[1;92mThis is the help information for the Sim-Plicity.\n\n");

        System.out.println("\033[1;34m1. GAME DESCRIPTION\n");
        System.out.println("\033[1;34m2. HOW TO PLAY?\n");
        System.out.println("\033[1;34m3. COMMAND LIST\n");

        
        System.out.println("\033[1;91m===================================================================================");
        
        System.out.println("\033[1;91m█▀▀ ▄▀█ █▀▄▀█ █▀▀   █▀▄ █▀▀ █▀ █▀▀ █▀█ █ █▀█ ▀█▀ █ █▀█ █▄░█");
        System.out.println("\033[1;91m█▄█ █▀█ █░▀░█ ██▄   █▄▀ ██▄ ▄█ █▄▄ █▀▄ █ █▀▀ ░█░ █ █▄█ █░▀█\n\n");
    

        System.out.println("\033[1;31mSim-plicity adalah permainan yang ingin dimiliki oleh Indra dan Doni. Game ini adalah permainan tentang kehidupan sehari-hari sims, karakter orang dalam game ini. \nSelain sim, game ini memiliki entitas world sebagai dunia virtual, rumah sebagai tempat tinggal sim, ruangan, dan objek-objek. \nSim juga memiliki kesejahteraan yang berparameter mood, kekenyangan, dan kesehatan yang bisa mempengaruhi umur sim. \nSim juga memiliki pekerjaan dan dapat melakukan aksi yang bisa mempengaruhi parameter kesejahteraannya.");
        System.out.println("\033[1;31mGame ini diprogram menggunakan bahasa Java dengan berbasis Command Line Interface. \nPertama, pemain dapat menciptakan sim. Setiap pembuatan satu sim, user akan mendapatkan satu rumah baru berukuran 6x6.  \nUser dapat berpindah-pindah sim untuk memainkannya. Untuk cara bermain, akan dijelaskan lebih rinci pada bagian cara bermain. \n");
        System.out.println("\033[1;91m===================================================================================\n\n\n\n");
    

        System.out.println("\033[1;93m========================================================"); 
        System.out.println("\033[1;93m█░█ █▀█ █░█░█   ▀█▀ █▀█   █▀█ █░░ ▄▀█ █▄█ ▀█");
        System.out.println("\033[1;93m█▀█ █▄█ ▀▄▀▄▀   ░█░ █▄█   █▀▀ █▄▄ █▀█ ░█░ ░▄\n");

        System.out.println("\033[1;33m1. Buatlah Sim baru! Nantinya, kamu juga bisa memilih sim yang ingin kamu mainkan.");
        System.out.println("\033[1;33m2. Setiap kamu membuat Sim baru, kondisi kesejahteraan kamu (kekenyangan, mood, dan kesehatan) akan otomatis di-set 80 dan kamu juga akan diberi uang awal sebesar 100.");
        System.out.println("\033[1;33m3. Selain itu, kamu juga akan mendapatkan pekerjaan secara random, rumah yang berisi ruangan berdimensi 6x6, dan barang-barang default yang otomatis ada di inventory kamu, seperti kasur, toilet, kompor, kursi, meja, dan jam.");
        System.out.println("\033[1;33m4. Namun, berhati-hatilah karena jika salah satu saja kondisi kesejahteraanmu bernilai 0, kamu bisa mati X_X");
        System.out.println("\033[1;33m5. Sim juga akan otomatis memiliki pekerjaan secara random");
        System.out.println("\033[1;33m6. Kamu dapat menjalankan aksi dengan Sim kamu, loh! Berikut rincian dan penjelasannya :");
        System.out.println("      \033[1;33ma) Aksi Aktif: Aksi yang memerlukan waktu dan keterlibatan Sim secara langsung");
        System.out.println("      \033[1;33mb) ksi Upgrade: Aksi yang memerlukan waktu tetapi tidak memerlukan keterlibatan Sim secara langsung");
        System.out.println("      \033[1;33mc) Aksi Non-aktif: yang tidak memerlukan waktu");
        System.out.println("      \033[1;33md) ksi Penambahan Sim: Aksi untuk menciptakan Sim baru beserta house yang akan didapatkannya.\n");
        
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


        System.out.println("\033[1;33m7. Sim dapat membeli beberapa barang yang disediakan di dalam game. Barang-barang yang dapat dibeli adalah : ");
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
    
        System.out.println("\033[1;96m==============================================================");    
        System.out.println("\033[1;96m█▀▀ █▀█ █▀▄▀█ █▀▄▀█ ▄▀█ █▄░█ █▀▄   █░░ █ █▀ ▀█▀"); 
        System.out.println("\033[1;96m█▄▄ █▄█ █░▀░█ █░▀░█ █▀█ █░▀█ █▄▀   █▄▄ █ ▄█ ░█░\n"); 

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



    // TODO : Implementasi Exit
    public void exit(){
        Scanner input = new Scanner(System.in);
        System.out.println("Apakah anda yakin ingin keluar dari game? (Y/N)");
        String answer = input.nextLine();
        if (answer.equals("Y")){
            System.out.println("Terima kasih telah bermain! \n Sampai jumpa lagi!");
            System.exit(0);
        }
        else{
            System.out.println("Kembali ke menu utama...");
            // TODO : Implementasi menu utama

        }

    }

    // TODO : Implementasi viewSimInfo
    public void viewSimInfo(){
        getActiveSim().showSimInfo();
    }

    // TODO : Implementasi viewCurrentLocation
    public void viewCurrentLocation(){
        System.out.println("Lokasi saat ini : " + getActiveSim().getCurrentPos().toString());
        //Ini koordinat aja atau sampe ke rumah dan ruangan juga?
    }

    public void viewInventory(){
        activeSim.getInventory().printItems();
    }

    public void runTime(){
        Sim firstActiveSim = null;
        Iterator<Sim> simIterator = simList.iterator();
        boolean stop = false;
        while (!stop && simIterator.hasNext()){
            firstActiveSim = simIterator.next();
            if (firstActiveSim.getStatus().equals("active")){
                stop = true;
            }
            else if (!simIterator.hasNext()){
                firstActiveSim = null;
            }
        }
        
        if (!Objects.isNull(firstActiveSim)){
            worldTimer.reduceTime(1, this);
        }
    }

}
