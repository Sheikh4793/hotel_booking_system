package com.hotel.model;

public class HotelLocation {
    private int locationId;
    private String city;
    private String state;
    private String country;

    public HotelLocation(){}

    public HotelLocation(int locationId, String city, String state, String country) {
        this.locationId = locationId;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "HotelLocation{" +
                "locationId=" + locationId +
                ", city=" + city +
                ", state=" + state +
                ", country=" + country +
                '}';
    }
}
