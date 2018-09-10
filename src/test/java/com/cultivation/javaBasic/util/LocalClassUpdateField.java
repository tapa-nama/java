package com.cultivation.javaBasic.util;

public class LocalClassUpdateField {
    private int year;

    public LocalClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public void somethingHappen() {
//        int year = 2019;
        class YearIncrement {
            void increment() {
                ++year;
            }
        }
        new YearIncrement().increment();

    }
}