package src.main.java.com.Kelompok10;

import java.util.*;

import src.main.java.com.Kelompok10.Exceptions.ItemNotFoundException;

public class Inventory<T extends Item> {
    private HashMap<String, Integer> daftarItem;
    private ArrayList<T> itemContainer;

    public Inventory() {
        daftarItem = new HashMap<String, Integer>();
        itemContainer = new ArrayList<T>();
    }

    public boolean containsItem(String item) {
        if (daftarItem.containsKey(item)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<T> getItemContainer() {
        return itemContainer;
    }

    public void removeItem(String item) {
        // Jumlah Item >= 1
        if (daftarItem.containsKey(item)) {
            if (daftarItem.get(item) > 1) {
                daftarItem.put(item, daftarItem.get(item) - 1);
            } else {
                daftarItem.remove(item);
            }
        } else {
            System.out.println("Item " + item + " tidak ada di dalam inventory!");
        }
    }

    public void addItem(T item) {
        if (daftarItem.containsKey(item.getNama())) {
            daftarItem.put(item.getNama(), daftarItem.get(item.getNama()) + 1);
        } else {
            daftarItem.put(item.getNama(), 1);
        }
        itemContainer.add(item);
    }

    public T getItem(String item) throws ItemNotFoundException {
        T toGet = findItemInContainer(item);
        return toGet;
    }

    public void printItems() {
        System.out.printf("%-60s\n", "+----------------------------+-----------------------------+");
        System.out.printf("| %-20s %s %-21s |\n", " ", "Inventory Sim", " ");
        System.out.printf("%-60s\n", "+----------------------------+-----------------------------+");
        if (daftarItem.isEmpty()) {
            System.out.printf("| %-20s %s %-21s |\n", " ", "Inventory Sim Kosong!!", " ");
        } else {
            for (String s : daftarItem.keySet()) {
                System.out.printf("| %-26s | %-28d|\n", s, daftarItem.get(s));
                System.out.printf("%-60s\n", "+----------------------------+-----------------------------+");
            }
        }
        System.out.println();

    }

    public boolean printListIngredient() {
        System.out.println("Berikut adalah bahan makanan yang ada pada inventory: ");
        int i = 1;
        boolean adaBahan = false;
        if (daftarItem.isEmpty()) {
            System.out.println("Inventory Sim Kosong!!");
        } else {
            try {
                for (String s : daftarItem.keySet()) {
                    if (findItemInContainer(s) instanceof Ingredient) {
                        System.out.println(i + ". " + s + "(" + daftarItem.get(s) + ")");
                        i++;
                        adaBahan = true;
                    }
                }
                if (!adaBahan) {
                    System.out.println("Tidak ada bahan makanan pada inventory!!");
                }
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return adaBahan;
    }

    public boolean printListMakanan() {
        System.out.println("Berikut adalah makanan yang ada pada inventory: ");
        int i = 1;
        boolean adaMakanan = false;
        if (daftarItem.isEmpty()) {
            System.out.println("Inventory Sim Kosong!!");
        } else {
            try {
                for (String s : daftarItem.keySet()) {
                    if (findItemInContainer(s) instanceof Food || findItemInContainer(s) instanceof Ingredient) {
                        System.out.println(i + ". " + s + "(" + daftarItem.get(s) + ")");
                        i++;
                        adaMakanan = true;
                    }
                }
                if (!adaMakanan) {
                    System.out.println("Tidak ada makanan pada inventory!!");
                }
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return adaMakanan;
    }

    public T findItemInContainer(String itemName) throws ItemNotFoundException {
        T item = null;
        Iterator<T> containerIterator = itemContainer.iterator();
        boolean found = false;
        while (!found && containerIterator.hasNext()) {
            item = containerIterator.next();
            if (item.getNama().equals(itemName)) {
                found = true;
            }
        }

        if (found) {
            return item;
        } else {
            throw new ItemNotFoundException("Item " + itemName + " tidak ada!");
        }
    }
}
