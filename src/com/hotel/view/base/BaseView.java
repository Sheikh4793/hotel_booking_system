package com.hotel.view.base;


import com.hotel.view.contracts.IView;

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

   




}
