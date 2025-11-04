package com.hotel.model;

import com.hotel.model.constants.HotelType;

public class Hotel {
    private int hotelId;
    private String name;
    private int locationId;
    private HotelType hotelType;
    private String contactNumber;
    private String email;
    private String address;

    public Hotel() {}

    public Hotel(int hotelId, String name, int locationId, HotelType hotelType, String contactNumber, String email, String address) {
        this.hotelId = hotelId;
        this.name = name;
        this.locationId = locationId;
        this.hotelType = hotelType;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return hotelId;
    }

    public void setId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationIdId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
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

    @Override
    public String toString() {
        return
//               "Hotel ID=" + hotelId +
                "Hotel name = " + name +
//               ", locationId = " + locationId +
                ", hotelType = " + hotelType +
                ", contactNumber = " + contactNumber  +
                ", email = " + email  +
                ", address = " + address  ;
    }
}
