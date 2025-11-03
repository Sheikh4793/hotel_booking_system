package com.hotel.model;

public class Operator extends User {
    private int OperatorId;
    private int hotelId;
    private String role;

    public Operator() {
    }

    public Operator(int operatorId, int hotelId, String name, String email, String password, String role) {
        super( name, email, password);
        this.OperatorId = operatorId;
        this.hotelId = hotelId;
        this.role = role;
    }

    public int getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(int operatorId) {
        OperatorId = operatorId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "HotelOperator{" +
                "OperatorId=" + OperatorId +
                super.toString() +
                ",hotelId=" + hotelId +
                ", role=" + role +
                '}';
    }
}
