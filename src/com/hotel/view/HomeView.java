package com.hotel.view;

import com.hotel.model.Operator;
import com.hotel.model.Customer;
import com.hotel.util.MyScanner;

public class HomeView  {

    private final Operator operator;
    private final Customer customer;

    public HomeView(Operator operator,Customer customer){
        this.operator = operator;
        this.customer = customer;
    }

    public void showMenu() {
        System.out.println("====================================");
        System.out.println(" " + "WELCOME TO HOTEL BOOKING SYSTEM");
        System.out.println("====================================");
        
        while(true){
            System.out.println("1.Operator\n2.Customer\n3.Exit");
            int choice = MyScanner.getInt("Enter choice");

            switch(choice){
                case 1:
                  
                case 2:

                case 3:
                  System.out.println("Thank you for visiting!");
                  return;

                default:
                  System.out.println("Invalid choice, please try again.");
                  
            }
        }
    }

}
