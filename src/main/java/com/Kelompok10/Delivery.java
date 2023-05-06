package com.Kelompok10;

public class Delivery {
    private int originalDuration;
    private int remainingDuration;
    private String itemName;

    public Delivery(int originalDuration, int remainingDuration, String itemName) {
        this.originalDuration = originalDuration;
        this.remainingDuration = remainingDuration;
        this.itemName = itemName;
    }

    public synchronized int getOriginalDuration() {
        return this.originalDuration;
    }

    public synchronized int getRemainingDuration() {
        return this.remainingDuration;
    }

    public synchronized void setRemainingDuration(int remainingDuration) {
        this.remainingDuration = remainingDuration;
    }

    public synchronized String getItemName() {
        return this.itemName;
    }

}
