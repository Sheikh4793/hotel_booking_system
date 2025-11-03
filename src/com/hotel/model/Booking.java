package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDateTime;

import com.hotel.model.constants.BookingStatus;

public class Booking {
    private int bookingId;
    private int customerId;
    private int roomId;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private BookingStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public Booking() {}

    public Booking(int bookingId, int customerId, int roomId, LocalTime checkInTime, LocalTime checkOutTime, BookingStatus status, BigDecimal totalAmount, LocalDateTime createdAt) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "HotelBooking{" +
                "bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", roomId=" + roomId +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                '}';
    }
}
