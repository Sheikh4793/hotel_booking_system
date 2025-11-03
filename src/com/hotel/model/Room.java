package com.hotel.model;

import com.hotel.model.constants.RoomType;

public class Room {
    private int roomId;
    private int hotelId;
    private int roomNumber;
    private RoomType roomType;
    private int capacity;
    private int pricePerNight;
    private boolean available;

    public Room() {}

    public Room(int roomId, int hotelId, int roomNumber, RoomType roomType, int capacity, int pricePerNight, boolean available) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
                "roomId=" + roomId +
                ", hotelId=" + hotelId +
                ", roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                ", capacity=" + capacity +
                ", pricePerNight=" + pricePerNight +
                ", available=" + available +
                '}';
    }
}
