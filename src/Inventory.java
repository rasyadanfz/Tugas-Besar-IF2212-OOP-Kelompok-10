package src;

import java.util.*;

import src.Exceptions.ItemNotFoundException;

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

    // TODO : Implementasi getItem
    public T getItem(String item) throws ItemNotFoundException {
        T toGet = findItemInContainer(item);
        return toGet;
    }

    public void printItems() {
        int i = 1;
        if (daftarItem.isEmpty()) {
            System.out.println("Inventory Sim Kosong!!");
        } else {
            for (String s : daftarItem.keySet()) {
                System.out.println("    " + i + ". " + s + "(" + daftarItem.get(s) + ")");
                i++;
            }
        }

    }

    public void printListIngredient() {
        System.out.println("Berikut adalah bahan makanan yang ada pada inventory: ");
        int i = 1;
        if (daftarItem.isEmpty()) {
            System.out.println("Tidak ada bahan makanan pada inventory !!");
        } else {
            try {
                for (String s : daftarItem.keySet()) {
                    if (findItemInContainer(s) instanceof Ingredient) {
                        System.out.println(i + ". " + s + "(" + daftarItem.get(s) + ")");
                        i++;
                    }
                }
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printListMakanan() {
        System.out.println("Berikut adalah makanan yang ada pada inventory: ");
        int i = 1;
        if (daftarItem.isEmpty()) {
            System.out.println("Tidak ada makanan pada inventory !!");
        } else {
            try {
                for (String s : daftarItem.keySet()) {
                    if (findItemInContainer(s) instanceof Food || findItemInContainer(s) instanceof Ingredient) {
                        System.out.println(i + ". " + s + "(" + daftarItem.get(s) + ")");
                        i++;
                    }
                }
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
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
