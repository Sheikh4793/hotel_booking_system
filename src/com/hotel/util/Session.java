package com.hotel.util;

public class Session {

    private static int operatorId;
    private static int customerId;
    private static int bookingId;
    private static int hotelId;

    public static int getOperatorId() {
        return operatorId;
    }

    public static void setOperatorId(int operatorId) {
        Session.operatorId = operatorId;
    }

    public static int getCustomerId() {
        return customerId;
    }

    public static void setCustomerId(int customerId) {
        Session.customerId = customerId;
    }

    public static int getBookingId() {
        return bookingId;
    }

    public static void setBookingId(int bookingId) {
        Session.bookingId = bookingId;
    }

    public static int getHotelId() {
        return hotelId;
    }
    public static void setHotelId(int hotelId) {
        Session.hotelId = hotelId;
    }

}
