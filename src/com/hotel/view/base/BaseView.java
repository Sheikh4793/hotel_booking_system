package com.hotel.view.base;


import com.hotel.util.MyScanner;
import com.hotel.util.Validator;
import com.hotel.view.contracts.IView;

import java.util.function.Predicate;

public abstract  class BaseView implements IView {

    protected void printHeader(String title){
        System.out.println("====================================");
        System.out.println(" " + title);
        System.out.println("====================================");
    }

    protected void invalidOption(){
        System.out.println("Invalid Option. please try again");
    }

    @Override
    public void showMenu(){
        System.out.println("BaseMenu");
    }

    protected void printDividerLine(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    protected String showUntilValid(String userMessage, Predicate<String> validator, String errorMessage){
        String input = MyScanner.getString(userMessage);
        while(!validator.test(input)){
            System.out.println(errorMessage);
            input = MyScanner.getString(userMessage);
        }
        return input;
    }






}
