package com.hotel.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validator {


    private Validator() {
    }


    //Patterns

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[6-9]\\d{9}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$");

    //validator methods

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isPositiveNumber(int value) {
        return value > 0;
    }

    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidChoice(int choice, int min, int max) {
        return choice >= min && choice <= max;
    }

    public static boolean isValidPositiveNumber(int value) {
        return value > 0;
    }

    public static boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) return false;
        return checkOut.isAfter(checkIn);
    }

    public static boolean isValidDate(LocalDate checkIn) {
        return checkIn != null && !checkIn.isBefore(LocalDate.now());
    }



    public static boolean isValidConfirmation(String input) {
        if (!isValidString(input)) return false;
        String lower = input.trim().toLowerCase();
        return lower.equals("yes") || lower.equals("no");
    }

    public static boolean isValidRoomNumber(int value,int maxNumber) {
        return value <= maxNumber && value >= 0;
    }

    public static boolean isValidPrice(BigDecimal value) {
        return BigDecimal.valueOf(800).compareTo(value) < 0;
    }

    public static boolean isValidName(String input) {
        return input != null && !input.trim().isEmpty();
    }

}

