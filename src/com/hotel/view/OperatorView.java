package com.hotel.view;


import com.hotel.controller.OperatorController;
import com.hotel.util.MyScanner;
import com.hotel.util.Validator;
import com.hotel.view.base.BaseAuthView;
import com.hotel.view.base.BaseView;

public class OperatorView extends BaseAuthView {

    private final OperatorController operatorController;

    public OperatorView(OperatorController operatorController) {
        this.operatorController = operatorController;
    }

    @Override
    public void showMenu() {
        printHeader("OPERATOR MENU");
        System.out.println("1.Operator LogIn\n2.Back");
        int choice = MyScanner.getInt("Enter your choice : ");

        while (true) {
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    return;
                default:
                    invalidOption();
            }
        }
    }

    private void login() {
      String[] credentials =  getLogInCredentials();

    }


}