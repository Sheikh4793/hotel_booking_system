package com.hotel.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class MyScanner {

    private MyScanner(){

    }

    private static Scanner scanner = new Scanner(System.in);


    public String getString(String input){
        System.out.println(input);
        return scanner.nextLine();
    }

    public int getInt(String input){
        System.out.println(input);
        return scanner.nextInt();
    }


    public BigDecimal getDouble(String input){
        System.out.println(input);
        return scanner.nextBigDecimal();
    }


}
