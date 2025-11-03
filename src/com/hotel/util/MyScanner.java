package com.hotel.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class MyScanner {

    private MyScanner(){

    }

    private static Scanner scanner = new Scanner(System.in);


    public static String getString(String input){
        System.out.println(input);
        return scanner.nextLine();
    }

    public static int getInt(String input){
        System.out.println(input);
        while(!(scanner.hasNext())){
            System.out.println("Invalid input.please enter a number");
        }
        return scanner.nextInt();
    }


    public static BigDecimal getBigDecimal(String input){
        System.out.println(input);
        return scanner.nextBigDecimal();
    }


}
