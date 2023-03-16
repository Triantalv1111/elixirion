package com.example.myapplication1;

public class DataClass {
    private String fullName;
    private String id1;
    private String country1;
    private String address1;
    private String city1;
    private String phoneNo1;

    public String getPhoneNo() {
        return phoneNo1;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId1() {
        return id1;
    }

    public String getCountry1() {
        return country1;
    }

    public String getAddress1() {
        return address1;
    }

    public String getCity1() {
        return city1;
    }

    public String getPostal() {
        return postal;
    }

    private String postal;

    public DataClass(String fullName, String id, String country,
                String address, String city, String postal,String phoneNo1) {
        this.id1 = id;
        this.phoneNo1 = phoneNo1;
        this.fullName = fullName;
        this.country1 = country;
        this.address1 = address;
        this.city1 = city;
        this.postal = postal;

    }
}
