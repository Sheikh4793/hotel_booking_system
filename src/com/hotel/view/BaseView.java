package com.hotel.view;

import com.hotel.view.contracts.ViewI;



public abstract  class BaseView implements ViewI{

    public void printHeader(String title){
        System.out.println("====================================");
        System.out.println(" " + title);
        System.out.println("====================================");
    }

    @Override
    public void showMenu(){
        System.out.println("BaseMenu");
    }

   




}
