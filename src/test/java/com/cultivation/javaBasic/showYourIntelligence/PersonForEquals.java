package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals implements Comparable {
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        PersonForEquals other = (PersonForEquals) obj;
        return this.name.equals(other.name) && this.yearOfBirth == other.yearOfBirth;

    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return Objects.hash(name, yearOfBirth);
        // --end-->
    }

    @Override
    public int compareTo(Object obj) {
        PersonForEquals other = (PersonForEquals) obj;
        if (other == null) {
            throw new NullPointerException();
        }
        int nameCompare = name.compareTo(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        if (yearOfBirth == other.yearOfBirth) {
            return 0;
        } else if (yearOfBirth > other.yearOfBirth) {
            return 1;
        }
        return -1;

    }
}
