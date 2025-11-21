package com.hotel.util;

public class ReferenceGenerator {


    private ReferenceGenerator() {
    }


    public static String generateReference(int bookingId) {

        String input = "-" + bookingId + "-SECRET_KEY";
        int hash = Math.abs(input.hashCode());
        int shortCode = hash % 1000000;
        return "HB-" + "-" + String.format("%06d", shortCode);
    }

}


