package com.Kelompok10;

import java.util.*;

import com.Kelompok10.Exceptions.RoomIsEmptyException;
import com.Kelompok10.Thing.*;

public class Room {
    private String namaRuangan;
    private House rumah;
    private Matrix petaRuangan;
    private Point roomPosition;
    private HashMap<String, ArrayList<Point>> placedObject;
    private HashMap<String, Integer> jumlahItem;
    private ArrayList<Thing> itemContainer;

    // Konstruktor dengan Point lokasi yang jelas
    public Room(String namaRuangan, House rumah, int x, int y) {
        this.namaRuangan = namaRuangan;
        this.rumah = rumah;
        placedObject = new HashMap<String, ArrayList<Point>>();
        jumlahItem = new HashMap<String, Integer>();
        itemContainer = new ArrayList<Thing>();
        // Default dari game
        petaRuangan = new Matrix(6, 6);
        roomPosition = new Point(x, y);
    }

    // Konstruktor tanpa Point lokasi yang fix
    public Room(String namaRuangan, House rumah) {
        this.namaRuangan = namaRuangan;
        this.rumah = rumah;
        placedObject = new HashMap<String, ArrayList<Point>>();
        jumlahItem = new HashMap<String, Integer>();
        itemContainer = new ArrayList<Thing>();
        // Set default
        petaRuangan = new Matrix(6, 6);
        roomPosition = new Point(0, 0);
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public House getHouse() {
        return rumah;
    }

    public void printPetaRuangan() {
        petaRuangan.printMatrix();
    }

    public void printPetaRuangan(Sim sim) {
        petaRuangan.printMatrix(sim);
    }

    public Point getRoomPosition() {
        return roomPosition;
    }

    public HashMap<String, ArrayList<Point>> getPlacedObject() {
        return placedObject;
    }

    public HashMap<String, Integer> getDaftarJumlahItem() {
        return jumlahItem;
    }

    public ArrayList<Thing> getItemContainer() {
        return itemContainer;
    }

    public Matrix getPetaRuangan() {
        return petaRuangan;
    }

    public int getJumlah(String itemName) {
        if (jumlahItem.containsKey(itemName)) {
            return (jumlahItem.get(itemName));
        } else {
            return 0;
        }
    }

    public String getKodeJumlah(String itemName) {
        String jumlah;
        if (getJumlah(itemName) > 10) {
            jumlah = Integer.toString(getJumlah(itemName) + 1);
        } else {
            jumlah = "0" + (getJumlah(itemName) + 1);
        }
        return jumlah;
    }

    public void increaseJumlahItem(String itemName) {
        if (!jumlahItem.containsKey(itemName)) {
            jumlahItem.put(itemName, 1);
        } else {
            jumlahItem.put(itemName, jumlahItem.get(itemName) + 1);
        }
    }

    public void reduceJumlahItem(String itemName) {
        if (jumlahItem.get(itemName) > 1) {
            jumlahItem.put(itemName, jumlahItem.get(itemName) - 1);
        } else {
            jumlahItem.remove(itemName);
        }
    }

    private Point findObjectPlacementInPlacedObject(Thing thing) {
        for (Point p : placedObject.get(thing.getNama())) {
            if (thing.getPosisi().equals(p)) {
                return p;
            }
        }
        return null;
    }

    public Thing findItemInContainer(Point itemPosition) {
        Thing item = null;
        Iterator<Thing> containerIterator = itemContainer.iterator();
        boolean found = false;
        while (!found && containerIterator.hasNext()) {
            item = containerIterator.next();
            if (item.getPosisi().equals(itemPosition)) {
                found = true;
            }
        }

        if (found) {
            return item;
        } else {
            return null;
        }
    }

    public void printPlacedObject() throws RoomIsEmptyException {
        if (!placedObject.isEmpty()) {
            System.out.println(
                    "Item yang terdapat pada " + getNamaRuangan() + " dalam rumah " + getHouse().getKodeRumah() + " :");
            for (Map.Entry<String, ArrayList<Point>> entry : placedObject.entrySet()) {
                if (entry.getValue().size() > 1) {
                    for (Point p : entry.getValue()) {
                        System.out.print("- ");
                        System.out.println(entry.getKey() + " <" + p.getX() + ", " + p.getY() + ">");
                    }
                } else if (entry.getValue().size() == 1) {
                    System.out.print("- ");
                    System.out.println(entry.getKey() + " <" + entry.getValue().get(0).getX() + ", "
                            + entry.getValue().get(0).getY() + ">");
                }
            }
        } else {
            throw new RoomIsEmptyException(getNamaRuangan());
        }
    }

    public void setRoomPosition(int x, int y) {
        roomPosition.setX(x);
        roomPosition.setY(y);
    }

    public void placeItem(Thing object, int x, int y, boolean rotate) throws Exception {
        String namaItem = object.getNama();
        String kodeItem = object.getKode();
        int lebarItem;
        int panjangItem;
        if (rotate) {
            lebarItem = object.getPanjang();
            panjangItem = object.getLebar();
        } else {
            panjangItem = object.getPanjang();
            lebarItem = object.getLebar();
        }

        if (x + panjangItem > 7 || y + lebarItem > 7) {
            throw new Exception("Tidak bisa meletakkan benda karena melebihi batas ruangan!");
        } else {
            for (int i = x; i < x + panjangItem; i++) {
                for (int j = y; j < y + lebarItem; j++) {
                    if (petaRuangan.getItem(i, j) != "----") {
                        throw new Exception(
                                "Tidak bisa meletakkan benda karena terdapat benda lain di lokasi tersebut!");
                    }
                }
            }

            for (int i = x; i < x + panjangItem; i++) {
                for (int j = y; j < y + lebarItem; j++) {
                    petaRuangan.changeItem(i, j, kodeItem);
                }
            }

            if (!Objects.isNull(placedObject.get(namaItem))) {
                ArrayList<Point> newArray = new ArrayList<Point>(placedObject.get(namaItem));
                newArray.add(new Point(x, y));
                placedObject.put(namaItem, newArray);
            } else {
                ArrayList<Point> newArray = new ArrayList<Point>();
                newArray.add(new Point(x, y));
                placedObject.put(namaItem, newArray);
            }
            object.setPosisi(x, y);
            itemContainer.add(object);
            increaseJumlahItem(namaItem);
        }
    }

    public void removeItem(int x, int y) {
        Point itemPos = new Point(x, y);
        Thing toRemove = findItemInContainer(itemPos);
        placedObject.get(toRemove.getNama()).remove(findObjectPlacementInPlacedObject(toRemove));
        reduceJumlahItem(toRemove.getNama());
        itemContainer.remove(toRemove);
        for (int i = x; i < x + toRemove.getPanjang(); i++) {
            for (int j = y; j < y + toRemove.getLebar(); j++) {
                petaRuangan.changeItem(i, j, "----");
            }
        }
    }

    // public static void main(String[] args) {
    // House rumah1 = new House("H01", 0, 0);
    // Room ruang1 = new Room("R01", rumah1);
    // ruang1.printPetaRuangan();

    // System.out.println();
    // KasurSingle kasur1 = new KasurSingle("KS1");
    // MejaKursi mejaKursi = new MejaKursi("MK1");
    // try {
    // ruang1.placeItem(kasur1, 1, 1);
    // ruang1.placeItem(mejaKursi, 1, 2);
    // ruang1.printPetaRuangan();
    // } catch (Exception e) {
    // System.out.println(e);
    // }

    // ruang1.printPlacedObject();
    // }
}