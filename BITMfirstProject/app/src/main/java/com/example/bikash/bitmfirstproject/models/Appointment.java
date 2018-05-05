package com.example.bikash.bitmfirstproject.models;

/**
 * Created by Bikash on 4/20/2018.
 */

public class Appointment {
    private int id;
    private String paient_name;
    private String specialist_Dr;
    private String phoneNumber;
    private String date;

    public Appointment(int id, String paient_name, String specialist_Dr, String phoneNumber, String date) {
        this.id = id;
        this.paient_name = paient_name;
        this.specialist_Dr = specialist_Dr;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public Appointment(String paient_name, String specialist_Dr, String phoneNumber,String date) {
        this.paient_name = paient_name;
        this.specialist_Dr = specialist_Dr;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public Appointment(int id, String paient_name) {
        this.id = id;
        this.paient_name = paient_name;
    }

    public Appointment() {
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getPaient_name() {
        return paient_name;
    }

    public String getSpecialist_Dr() {
        return specialist_Dr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
