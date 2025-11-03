package com.hotel.view;

import com.hotel.controller.CustomerController;
import com.hotel.model.Customer;
import com.hotel.util.MyScanner;
import com.hotel.util.Validator;
import com.hotel.view.base.BaseAuthView;


public class CustomerView extends BaseAuthView {

    private Customer customer;
    private CustomerController customerController;

    public CustomerView(CustomerController customerController){
        this.customerController = customerController;
    }

    @Override
    public void showMenu() {
        while(true) {
            printHeader("CUSTOMER MENU");
            System.out.println("1.SignUp\n2.LogIn\n3.Back");
            int choice = MyScanner.getInt("Enter choice");

            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    return;
                default:
                    invalidOption();
            }
        }
    }
        private void signUp(){
            String name = MyScanner.getString("Enter name : ");
            String email = MyScanner.getString("Enter email : ");
            Validator.isValidEmail(email);
            String password = MyScanner.getString("Enter password : ");
            Validator.isValidPassword(password);
            String phone = MyScanner.getString("Enter phone number : ");
            Validator.isValidPhone(phone);
            String address = MyScanner.getString("Enter address : ");
            customer = new Customer(name, email, password, phone, address);
        }

        private void login(){
            String[] credentials = getLogInCredentials();
            (credentials[0],credentials[1]);
        }


    }




