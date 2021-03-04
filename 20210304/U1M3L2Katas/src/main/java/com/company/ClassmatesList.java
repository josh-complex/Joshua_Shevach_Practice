package com.company;

import java.util.ArrayList;

public class ClassmatesList {

    private ArrayList<Classmate> classmates = new ArrayList<>();

    public void add(Classmate classmate){
        classmates.add(classmate);
    }

    public Classmate get(int index){
        return classmates.get(index);
    }

    public ArrayList<Classmate> getAll(){
        return classmates;
    }
}
