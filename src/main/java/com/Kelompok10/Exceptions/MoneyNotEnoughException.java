package com.Kelompok10.Exceptions;

public class MoneyNotEnoughException extends Exception {
    public MoneyNotEnoughException() {
        super("Uang Sim tidak cukup!");
    }
}
