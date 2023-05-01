package src;
import java.util.*;

public class Inventory<T extends Item> {
    private HashMap<T, Integer> daftarItem;

    public Inventory(){
        daftarItem = new HashMap<T, Integer>();
    }

    public boolean containsItem(T item){
        if (daftarItem.containsKey(item)){
            return true;
        }
        else{
            return false;
        }
    }

    public void removeItem(T item){
        // Jumlah Item >= 1
        if (daftarItem.containsKey(item)){
            if (daftarItem.get(item) > 1){
                daftarItem.put(item, daftarItem.get(item) - 1);
            }
            else{
                daftarItem.remove(item);
            }
        }
        else{
            System.out.println("Item " + item + " tidak ada di dalam inventory!");
        }
    }

    public void addItem(T item){
        if (daftarItem.containsKey(item)){
            daftarItem.put(item, daftarItem.get(item) + 1);
        }
        else{
            daftarItem.put(item, 1);
        }
    }

    //TODO : Implementasi getItem
    public T getItem(T item){
        removeItem(item);
        
    }

    public void printItems(){
        int i = 1;
        if (daftarItem.isEmpty()){
            System.out.println("Inventory Sim Kosong!!");
        }
        else{
            for (T s : daftarItem.keySet()){
                System.out.println(i + ". " + s.getNama() + "(" + daftarItem.get(s) + ")");
                i++;
            }
        }
        
    }
}
