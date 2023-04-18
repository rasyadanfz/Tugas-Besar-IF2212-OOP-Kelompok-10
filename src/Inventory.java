package src;
import java.util.*;

public class Inventory {
    private HashMap<String, Integer> daftarItem;

    public Inventory(){
        daftarItem = new HashMap<String, Integer>();
    }

    public boolean containsItem(String itemName){
        if (daftarItem.containsKey(itemName)){
            return true;
        }
        else{
            return false;
        }
    }

    public void removeItem(String itemName){
        // Jumlah Item >= 1
        if (daftarItem.containsKey(itemName)){
            if (daftarItem.get(itemName) > 1){
                daftarItem.put(itemName, daftarItem.get(itemName) - 1);
            }
            else{
                daftarItem.remove(itemName);
            }
        }
        else{
            System.out.println("Item " + itemName + " tidak ada di dalam inventory!");
        }
    }

    public void addItem(String itemName){
        if (daftarItem.containsKey(itemName)){
            daftarItem.put(itemName, daftarItem.get(itemName) + 1);
        }
        else{
            daftarItem.put(itemName, 1);
        }
    }

    //TODO : Implementasi getItem
    public void getItem(String itemName){

    }

    public void printItems(){
        int i = 1;
        for (String s : daftarItem.keySet()){
            System.out.println(i + ". " + s);
            i++;
        }
    }
}
