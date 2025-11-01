package com.hotel.model;

public class HotelCustomer extends User {
    private int customerId;
    private String phoneNumber;
    private String address;

    public HotelCustomer() {}

    public HotelCustomer(int customerId, String name, String email, String password, String phoneNumber, String address) {
        super(name, email, password);
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "HotelCustomer{" +
                "customerId=" + customerId +
                super.toString() +
                ",phoneNumber=" + phoneNumber +
                ", address=" + address +
                '}';
    }
}
