package com.hotel.view.base;


import com.hotel.view.contracts.IView;

import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract  class BaseView<T> implements IView {

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

    protected <T> T showUntilValid(Supplier<T> supplier, Predicate<T> validator, String errorMessage){

        T input =  supplier.get();
        while(!validator.test(input)){
            System.out.println(errorMessage);
            input = supplier.get();
        }
        return input;
    }










}
