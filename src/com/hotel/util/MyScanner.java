package com.hotel.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class MyScanner {

    private MyScanner(){

    }

    private static final Scanner scanner = new Scanner(System.in);


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
        return scanner.nextBigDecimal();
    }


}
