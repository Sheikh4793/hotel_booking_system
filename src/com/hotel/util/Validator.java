package com.hotel.util;

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
}

//Method	What it checks	Example
//isValidEmail	Valid email format	abc@gmail.com ✅
//isValidPhone	10-digit Indian mobile number starting 6–9	9876543210 ✅
//isValidPassword	At least one uppercase, one lowercase, one digit, min 6 chars	Admin123 ✅
//isNotEmpty	Non-null and not blank	"hello" ✅
//isPositiveNumber	Number > 0	5 ✅
