package com.hotel.model.dto;

import com.hotel.model.constants.HotelType;

public class HotelDisplay {

    private int hotelId;
    private String hotelName;
    private HotelType type;
    private String contactNumber;
    private String email;
    private String address;
    private String cityName;
    private String state;
    private String country;

    public HotelDisplay(int hotelId, String hotelName,HotelType type, String contactNumber, String email, String address, String cityName, String state, String country) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.type = type;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.cityName = cityName;
        this.state = state;
        this.country = country;
    }

    public HotelDisplay() {
    }


    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public HotelType getType() {
        return type;
    }

    public void setType(HotelType type) {
        this.type = type;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
        return String.format(
                " Name: %s | Type: %s | Contact: %s | Email: %s | Address: %s | Location: %s, %s, %s",
                  hotelName, type, contactNumber, email, address, cityName, state, country
        );
    }
}

