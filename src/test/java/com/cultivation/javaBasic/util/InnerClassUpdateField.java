package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public InnerClassUpdateField() {
        year = 2018;
    }

    public InnerClassUpdateField(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int add() {
//        class InnerClass{
//            static final int year = 2;
//        }
        InnerClass innerClass = new InnerClass();
        innerClass.add();
        return this.year;
    }

    public class InnerClass {
        int year = 2;

        public InnerClass() {
            year = 2;
        }

        public int increment() {
            return InnerClassUpdateField.this.year += 2;
        }

        public int increment(int year) {
            return InnerClassUpdateField.this.year += year;
        }

        public int add() {
            return InnerClassUpdateField.this.year += InnerClass.this.year;
        }


    }

}
