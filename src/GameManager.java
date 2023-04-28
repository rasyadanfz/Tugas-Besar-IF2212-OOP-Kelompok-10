package src;

import java.util.ArrayList;

public class GameManager {
    private World world;
    private ArrayList<Sim> simList;
    private int time;
    private int hari;
    private ArrayList<String> listOfActions;
    private Sim activeSim;
    private int houseCount;

    public GameManager(){
        world = World.getWorld();
        simList = new ArrayList<Sim>();
        hari = 0;
        listOfActions = new ArrayList<String>();
        houseCount = 0;
    }

    public World getWorld(){
        return world;
    }
    
    public ArrayList<Sim> getSimList(){
        return simList;
    }

    public int getHari(){
        return hari;
    }

    public ArrayList<String> getListOfActions(){
        return listOfActions;
    }

    public Sim getActiveSim(){
        return activeSim;
    }

    public int getTime(){
        return time;
    }

    public int getHouseCount(){
        return houseCount;
    }

    public void printSimList(){
        int i = 1;
        System.out.println("Daftar Sim dalam Game: ");
        for (Sim s : simList){
            System.out.println(i + ". " + s.getNamaLengkap());
        }
    }

    // Setter
    public void setActiveSim(Sim sim){
        activeSim = sim;
    }

    public void setHari(int hari){
        this.hari = hari;
    }

    // Menu
    // TODO : Implementasi Help
    public void help(){
        System.out.println("This is the help information for the Sim-Plicity.");
        System.out.println("====================================================================================================================================================================");
        System.out.println("______   _______  _______  ___   _  ______   ___   _______  _______  ___     _______  _______  ______   __   __  _______  ___   __    _  _______  __    _ ");
        System.out.println("|      | |       ||       ||   | | ||    _ | |   | |       ||       ||   |   |       ||       ||    _ | |  |_|  ||   _   ||   | |  |  | ||   _   ||  |  | |");
        System.out.println("|  _    ||    ___||  _____||   |_| ||   | || |   | |    _  ||  _____||   |   |    _  ||    ___||   | || |       ||  |_|  ||   | |   |_| ||  |_|  ||   |_| |");
        System.out.println("| | |   ||   |___ | |_____ |      _||   |_|| |   | |   |_| || |_____ |   |   |   |_| ||   |___ |   |_|| |       ||       ||   | |       ||       ||       |");
        System.out.println("| |_|   ||    ___||_____  ||     |_ |    __ ||   | |    ___||_____  ||   |   |    ___||    ___||    __ ||       ||       ||   | |  _    ||       ||  _    |");
        System.out.println("|       ||   |___  _____| ||    _  ||   |  |||   | |   |     _____| ||   |   |   |    |   |___ |   |  ||| ||_|| ||   _   ||   | | | |   ||   _   || | |   |");
        System.out.println("|______| |_______||_______||___| |_||___|  |||___| |___|    |_______||___|   |___|    |_______||___|  |||_|   |_||__| |__||___| |_|  |__||__| |__||_|  |__|\n");
    

        System.out.println("Sim-plicity adalah permainan yang ingin dimiliki oleh Indra dan Doni. Game ini adalah permainan tentang kehidupan sehari-hari sims, karakter orang dalam game ini. \nSelain sim, game ini memiliki entitas world sebagai dunia virtual, rumah sebagai tempat tinggal sim, ruangan, dan objek-objek. \nSim juga memiliki kesejahteraan yang berparameter mood, kekenyangan, dan kesehatan yang bisa mempengaruhi umur sim. \nSim juga memiliki pekerjaan dan dapat melakukan aksi yang bisa mempengaruhi parameter kesejahteraannya.");
        System.out.println("Game ini diprogram menggunakan bahasa Java dengan berbasis Command Line Interface. \nPertama, pemain dapat menciptakan sim. Setiap pembuatan satu sim, user akan mendapatkan satu rumah baru berukuran 6x6.  \nUser dapat berpindah-pindah sim untuk memainkannya. Untuk cara bermain, akan dijelaskan lebih rinci pada bagian cara bermain. \n");
        System.out.println("====================================================================================================================================================================\n\n\n\n");


        System.out.println("====================================================================================================================================================================");
        System.out.println("_______  _______  ______   _______    _______  _______  ______   __   __  _______  ___   __    _ ");
        System.out.println("|       ||   _   ||    _ | |   _   |  |  _    ||       ||    _ | |  |_|  ||   _   ||   | |  |  | |");
        System.out.println("|       ||  |_|  ||   | || |  |_|  |  | |_|   ||    ___||   | || |       ||  |_|  ||   | |   |_| |");
        System.out.println("|       ||       ||   |_|| |       |  |       ||   |___ |   |_|| |       ||       ||   | |       |");
        System.out.println("|      _||       ||    __ ||       |  |  _   | |    ___||    __ ||       ||       ||   | |  _    |");
        System.out.println("|     |_ |   _   ||   |  |||   _   |  | |_|   ||   |___ |   |  ||| ||_|| ||   _   ||   | | | |   |");
        System.out.println("|_______||__| |__||___|  |||__| |__|  |_______||_______||___|  |||_|   |_||__| |__||___| |_|  |__|" + "\n");

        System.out.println("1. User meng-create sim.");
        System.out.println("2. User dapat memilih sim yang ingin dimainkan");
        System.out.println("3. Setiap peng-create-an sim, user akan mendapatkan dimensi rumah yang berisi ruangan berukuran 6x6 dan barang-barang default yang akan otomatis \ntersimpan di inventory seperti kasur, toilet, kompor, kursi, meja, dan jam");
        System.out.println("4. Setiap peng-create-an sim, kondisi kesejahteraan awal sim (kekenyangan, mood, dan kesehatan) akan di-generate pada angka 80 sedangkan untuk \nuang yang dimiliki sim pada angka 100. Apabila salah satu parameter kesejahteraan sim mencapai angka 0, sim dapat mati.");
        System.out.println("5. Sim juga akan otomatis memiliki pekerjaan secara random");
        System.out.println("6. User dapat menjalankan sebuah aksi yang akan dilakukan oleh sim. Jenis-jenis aksi terbagi menjadi 3 yaitu : ");
        System.out.println("      a) Aksi aktif yang memerlukan waktu dan keterlibatan sim secara langsung");
        System.out.println("      b) Aksi upgrade yang memerlukan waktu tetapi tidak memerlukan keterlibatan sim secara langsung");
        System.out.println("      c) Aksi non-aktif yang tidak memerlukan waktu");
        System.out.println("      d) Aksi menambah sim yaitu menciptakan sim baru beserta house yang akan didapatkannya. \n Berikut adalah daftar aksi yang dapat dilakukan sim pada game ini.\n");
        System.out.println(" Aksi Aktif : ");
        System.out.println("   a) Kerja");
        System.out.println("   b) Olahraga");
        System.out.println("   c) Sleep");
        System.out.println("   d) Eating");
        System.out.println("   e) Cook");
        System.out.println("   f) Visit");
        System.out.println("   g) Pee");
        System.out.println("   h) Watching TV");
        System.out.println("   i) Bath");
        System.out.println("   j) Washing Hand\n\n");
        
        System.out.println(" Aksi Upgrade : ");
        System.out.println("   a) Upgrade Rumah");
        System.out.println("   b) Buy Item");
        System.out.println("   c) Sell Barang");
        System.out.println("   d) ambilBarang\n\n");

        System.out.println(" Aksi Non-Aktif : ");
        System.out.println("   a) lookPainting");
        System.out.println("   b) Mirror");
        System.out.println("   c) moveRuangan");
        System.out.println("   d) seeInventory");
        System.out.println("   e) installBarang");
        System.out.println("   f) seeTime\n\n");


        System.out.println("7. Sim dapat membeli beberapa barang yang disediakan di dalam game. Barang-barang yang dapat dibeli adalah : ");
        System.out.println("      a) Makanan");
        System.out.println("      b)Kasur Single");
        System.out.println("      c)Kasur Queen Size");
        System.out.println("      d)Kasur King Size");
        System.out.println("      e)Toilet");
        System.out.println("      f)Kompor Gas");
        System.out.println("      g)Kompor Listrik");
        System.out.println("      h)Meja dan Kursi");
        System.out.println("      i)Jam");
        System.out.println("      j)TV");
        System.out.println("      k)Shower"); 
        System.out.println("      l)Lukisan ");
        System.out.println("      m)Toilet ");
        System.out.println("      n)Wastafel ");
        System.out.println("      o)Cermin ");

        System.out.println("====================================================================================================================================================================");
    }

    // TODO : Implementasi Exit
    public void exit(){

    }

    // TODO : Implementasi viewSimInfo
    public void viewSimInfo(){

    }

    // TODO : Implementasi viewCurrentLocation
    public void viewCurrentLocation(){

    }

    public void viewInventory(){
        activeSim.getInventory().printItems();
    }

}
