package com.example.bikash.bitmfirstproject.recyclerviews;

/**
 * Created by Bikash on 4/20/2018.
 */

public class AppointInfo {

    private String paient_name;
    private String contact;
    private String specialist;

    public AppointInfo(String paient_name, String contact, String specialist) {
        this.paient_name = paient_name;
        this.contact = contact;
        this.specialist = specialist;
    }

    //TODO:Single Patient Info
    public AppointInfo(String paient_name) {
        this.paient_name = paient_name;
    }

    public String getPaient_name() {
        return paient_name;
    }

    public void setPaient_name(String paient_name) {
        this.paient_name = paient_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }
}
