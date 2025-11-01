package com.hotel.view;


public abstract  class BaseView implements view<T>{

    public void printHeader(String title){
        System.out.println("====================================");
        System.out.println(" " + title);
        System.out.println("====================================");
    }




}
