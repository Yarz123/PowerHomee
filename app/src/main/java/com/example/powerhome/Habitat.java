package com.example.powerhome;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Habitat {

    private int id;
    private String residentName;
    private int floor;
    private double area;
    private List<Appliances> appliances;

    public Habitat(int id, String name, int floor){
        this.id = id;
        this.residentName = name;
        this.floor = floor;
        this.appliances = new ArrayList<>();
    }


    public String getResidentName() {
        return residentName;
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }
    public List<Appliances> getAppliances() {
        return appliances;
    }

    public void addAppliance(Appliances appliance) {
        this.appliances.add(appliance);
    }

    @NonNull
    @Override
    public String toString() {
        return "Habitat{id=" + id + ", residentName='" + residentName + "', floor=" + floor + ", area=" + area + ", appliances=" + appliances.toString() + "}";
    }


}
