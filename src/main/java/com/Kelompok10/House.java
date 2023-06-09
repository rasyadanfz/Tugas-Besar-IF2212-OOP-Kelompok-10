package com.Kelompok10;

import java.util.*;

public class House {
    private String kodeRumah;
    private Matrix petaRumah = new Matrix(9, 9); // Asumsi awal aja biar mudah
    private ArrayList<Room> daftarRuangan;
    private Point lokasi;
    private int roomCount;
    private ArrayList<Sim> simInHouse;

    public House(String kodeRumah, int x, int y) {
        this.kodeRumah = kodeRumah;
        daftarRuangan = new ArrayList<>();
        lokasi = new Point(x, y);
        roomCount = 0;
        simInHouse = new ArrayList<Sim>();

        // SetDefault setiap kali beli rumah
        Room ruang1 = new Room("R001", this, 5, 5);
        daftarRuangan.add(ruang1);
        petaRumah.changeItem(5, 5, ruang1.getNamaRuangan());
        roomCount++;
    }

    public String getKodeRumah() {
        return kodeRumah;
    }

    public Matrix getPetaRumah() {
        return petaRumah;
    }

    public ArrayList<Room> getDaftarRuangan() {
        return daftarRuangan;
    }

    public ArrayList<Sim> getSimInHouse() {
        return simInHouse;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public void printPetaRumah() {
        petaRumah.printMatrix();
    }

    public void addSimToHouse(Sim sim) {
        simInHouse.add(sim);
    }

    public void removeSimFromHouse(Sim sim) {
        simInHouse.remove(sim);
    }

    public Room getRoom(String namaRuangan) {
        boolean found = false;
        Iterator<Room> roomIterator = daftarRuangan.iterator();
        Room targetRoom = null;
        while (!found && roomIterator.hasNext()) {
            targetRoom = roomIterator.next();
            if (targetRoom.getNamaRuangan().equals(namaRuangan)) {
                found = true;
            } else {
                targetRoom = null;
            }
        }
        return targetRoom;
    }

    public void addNewRoom(Room referenceRoom, Room newRoom, String arah) throws Exception {
        try {
            int x = referenceRoom.getRoomPosition().getX();
            int y = referenceRoom.getRoomPosition().getY();
            if (checkSpace(referenceRoom, arah.toLowerCase())) {
                // System.out.println(x);
                // System.out.println(y);
                switch (arah.toLowerCase()) {
                    case ("kiri"):
                        x -= 1;
                        break;
                    case ("kanan"):
                        x += 1;
                        break;
                    case ("atas"):
                        y += 1;
                        break;
                    case ("bawah"):
                        y -= 1;
                        break;
                }

                // System.out.println(x);
                // System.out.println(y);
                newRoom.setRoomPosition(x, y);
                petaRumah.changeItem(x, y, newRoom.getNamaRuangan());
                daftarRuangan.add(newRoom);
                roomCount++;
            } else {
                throw new Exception("Ruangan gagal ditambahkan! Terdapat ruangan lain pada space tersebut");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkSpace(Room ruang, String arah) throws Exception {
        boolean avail = false;
        int x = ruang.getRoomPosition().getX();
        int y = ruang.getRoomPosition().getY();
        // System.out.println(x);
        // System.out.println(y);

        // Cek dalam empat arah
        if (arah.toLowerCase().equals("atas")) {
            // Jika ruangan tersebut mentok di atas
            if ((y != 9) && (ruang.getHouse().getPetaRumah().getItem(x, y + 1).equals("----"))) {
                avail = true;
            }
        } else if (arah.toLowerCase().equals("bawah")) {
            // Jika ruangan tersebut mentok di bawah
            if ((y != 1) && (ruang.getHouse().getPetaRumah().getItem(x, y - 1).equals("----"))) {
                avail = true;
            }
        } else if (arah.toLowerCase().equals("kanan")) {
            // Jika ruangan tersebut mentok di kanan
            if ((x != 9) && (ruang.getHouse().getPetaRumah().getItem(x + 1, y).equals("----"))) {
                avail = true;
            }
        } else if (arah.toLowerCase().equals("kiri")) {
            // Jika ruangan tersebut mentok di kiri
            if ((x != 1) && (ruang.getHouse().getPetaRumah().getItem(x - 1, y).equals("----"))) {
                avail = true;
            }
        } else {
            if (x == 1 || x == 9 || y == 1 || y == 9) {
                throw new Exception("Gagal menambahkan ruangan! Acuan ruangan sudah mentok di pinggir rumah");
            } else {
                throw new Exception("Gagal memeriksa ketersediaan ruang! Arah tidak valid!");
            }
        }

        return avail;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        House other = (House) obj;
        if (kodeRumah != other.kodeRumah) {
            return false;
        }
        return true;
    }
}
