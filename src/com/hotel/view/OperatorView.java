package com.hotel.view;


import com.hotel.controller.OperatorController;
import com.hotel.customexception.ServiceException;
import com.hotel.factory.ObjectFactory;
import com.hotel.model.Operator;
import com.hotel.util.MyScanner;
import com.hotel.util.Session;
import com.hotel.view.base.BaseAuthView;
import com.hotel.view.contracts.IView;



public class OperatorView extends BaseAuthView {

    private final OperatorController operatorController;
    private IView  hotelView ;
    private IView  roomView ;

    public OperatorView(OperatorController operatorController) {
        this.operatorController = operatorController;
    }

    @Override
    public void showMenu() {
        while (true) {

            printHeader("OPERATOR MENU");
            System.out.println("1.Operator LogIn\n2.Back");
            int choice = MyScanner.getInt("Enter your choice : ");

            switch (choice) {
                case 1:
                    logIn();
                    showSwitch();
                    break;
                case 2:
                    return;
                default:
                    invalidOption();
            }
        }
    }

    private void showSwitch(){
        while (true) {

            printHeader("MANAGEMENT ");
            System.out.println("1.Hotel management\n2.Room management\n3.Back");
            int choice = MyScanner.getInt("Enter your choice : ");

            switch (choice) {
                case 1:
                    hotelView.showMenu();
                    break;
                case 2:
                    roomView.showMenu();
                    break;
                case 3:
                    return;
                default:
                    invalidOption();
            }
        }

    }

    private void logIn() {
      String[] credentials =  getLogInCredentials();
        try {
            Operator operator = operatorController.login(credentials[0], credentials[1]);

            if(operator == null){
                System.out.println("Operator login failed");
            }
            else{
                System.out.println("Operator login successful");
                try {
                    hotelView = ObjectFactory.getHotelView();
                    roomView = ObjectFactory.getRoomView();
                }
                catch (Exception e){
                    System.out.println("Hotel view could not be created");
                }
            }
        }
        catch (ServiceException e){
            System.out.println("Sorry login failed");
        }

    }






}