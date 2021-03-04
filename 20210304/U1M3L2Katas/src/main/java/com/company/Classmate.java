package com.company;

import java.util.Objects;

public class Classmate {

    private String name;
    private String hairColor;

    public Classmate() {
    }

    public Classmate(String name, String hairColor) {
        this.name = name;
        this.hairColor = hairColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classmate classmate = (Classmate) o;
        return Objects.equals(name, classmate.name) && Objects.equals(hairColor, classmate.hairColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hairColor);
    }

    @Override
    public String toString() {
        return "Classmate{" +
                "name='" + name + '\'' +
                ", hairColor='" + hairColor + '\'' +
                '}';
    }
}
