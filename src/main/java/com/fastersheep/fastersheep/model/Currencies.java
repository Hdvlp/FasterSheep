package com.fastersheep.fastersheep.model;

public enum Currencies {
    USD(1),
    HKD(2),
    CNY(3),
    JPY(4),
    GBP(5);

    private final int value;

    Currencies(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Currencies fromValue(int value) {
        for (Currencies currency : Currencies.values()) {
            if (currency.getValue() == value) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    // Method to get the number corresponding to a string
    public static int getNumberFromString(String currencyName) {
        for (Currencies currency : Currencies.values()) {
            if (currency.name().equalsIgnoreCase(currencyName)) { // Case-insensitive comparison
                return currency.getValue();
            }
        }
        return -1; // Return -1 if the currencyName is invalid
    }
}
