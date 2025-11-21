package com.hotel.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MyScanner {

    private MyScanner(){

    }

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getString(String input){
        System.out.println(input);
        String value = scanner.nextLine().trim();
        while(value.isEmpty()){
            System.out.println("Invalid input. Please try again.");
            value = scanner.nextLine().trim();
        }
        return value;
    }

    public static int getInt(String input){
        System.out.println(input);
        while(!scanner.hasNextInt()){
            System.out.println("Invalid input.please enter a number");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }


    public static BigDecimal getBigDecimal(String input){
        System.out.println(input);
        while(!scanner.hasNextBigDecimal()){
            System.out.println("Invalid input.please enter a number");
            scanner.next();
        }
        BigDecimal value = scanner.nextBigDecimal();
        scanner.nextLine();
        return value;
    }

    public static LocalDate getDate(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Date cannot be empty. Please enter a date in YYYY-MM-DD format.");
                continue;
            }

            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD (e.g., 2025-11-06).");
            }
        }
    }


}
