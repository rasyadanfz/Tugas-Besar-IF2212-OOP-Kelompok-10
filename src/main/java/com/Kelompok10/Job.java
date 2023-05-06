package src.main.java.com.Kelompok10;

public class Job {
    private String namaPekerjaan;
    private int gaji;

    public Job() {

    }

    public Job(String namaPekerjaan, int gaji) {
        this.namaPekerjaan = namaPekerjaan;
        this.gaji = gaji;
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public int getGaji() {
        return gaji;
    }

    public void setNama(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public static void findJob(Job pekerjaan, int i) {
        String namaPekerjaan = new String();
        int gaji = 0;

        switch (i) {
            case 0:
                namaPekerjaan = "Badut Sulap";
                gaji = 15;
                break;
            case 1:
                namaPekerjaan = "Koki";
                gaji = 30;
                break;
            case 2:
                namaPekerjaan = "Polisi";
                gaji = 35;
                break;
            case 3:
                namaPekerjaan = "Programmer";
                gaji = 45;
                break;
            case 4:
                namaPekerjaan = "Dokter";
                gaji = 50;
                break;
        }

        pekerjaan.setNama(namaPekerjaan);
        pekerjaan.setGaji(gaji);

    }

    // TODO : Implementasi Work Sesuai Konsep Timer
    public void work(Sim sim) {

    }
}
