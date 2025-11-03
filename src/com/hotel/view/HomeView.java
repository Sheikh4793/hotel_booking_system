package com.hotel.view;

import com.hotel.util.MyScanner;
import com.hotel.view.base.BaseView;
import com.hotel.view.contracts.IView;

public class HomeView extends BaseView {

    private final IView operatorView;
    private final IView customerView;

    public HomeView(IView operatorView, IView customerView){
        this.operatorView = operatorView;
        this.customerView = customerView;
    }

    public void showMenu() {

        printHeader("WELCOME TO HOTEL BOOKING SYSTEM");
        
        while(true){
            System.out.println("1.Operator\n2.Customer\n3.Exit");
            int choice = MyScanner.getInt("Enter choice");

            switch(choice){
                case 1:
                    operatorView.showMenu();
                case 2:
                    customerView.showMenu();
                case 3:
                  System.out.println("Thank you for visiting!");
                  return;

                default:
                  invalidOption();
                  
            }
        }
    }

}
