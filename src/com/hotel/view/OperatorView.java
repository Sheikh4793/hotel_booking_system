package com.hotel.view;


import com.hotel.controller.OperatorController;
import com.hotel.customexception.ServiceException;
import com.hotel.util.MyScanner;
import com.hotel.view.base.BaseAuthView;

public class OperatorView extends BaseAuthView {

    private final OperatorController operatorController;

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
                    break;
                case 2:
                    return;
                default:
                    invalidOption();
            }
        }
    }

    private void logIn() {
      String[] credentials =  getLogInCredentials();
        try {
            int userId = operatorController.login(credentials[0], credentials[1]);

            if(userId == 0){
                System.out.println("Operator login failed");
            }
            else{
                System.out.println("Operator login successful");
            }

        }
        catch (ServiceException e){
            System.out.println("Sorry login failed");
        }

    }


}