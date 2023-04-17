package src;

import java.util.*;
import java.util.Random;

public class Sim {
    private String namaLengkap;
    private Job pekerjaan = new Job();
    private int uang;
    private Inventory inventory;
    private int kekenyangan;
    private int kesehatan;
    private int mood;
    private String status;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        uang = 100;
        kekenyangan = 80; kesehatan = 80; mood = 80;
        pekerjaan = getJob();
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public int getMood() {
        return mood;
    }

    public String getPekerjaan() {
        return String.format(pekerjaan.getNama() + " dengan gaji " + pekerjaan.getGaji());
    }

    public void setKekenyangan(int num) {
        kekenyangan = num;
    }

    public void setKesehatan(int num) {
        kesehatan = num;
    }

    public void setMood(int num) {
        mood = num;
    }

    public Job getJob() {
        Random random = new Random();
        return (Job.findJob(pekerjaan, random.nextInt(5)));
    }

    // public static void main(String[] args) {
    //     Sim person1 = new Sim("Cathleen Lauretta");
    //     System.out.println(person1.getKekenyangan());
    //     System.out.println(person1.getKesehatan());
    //     System.out.println(person1.getMood());
    //     System.out.println(person1.getPekerjaan());
    // }
}
