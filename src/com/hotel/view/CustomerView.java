package com.hotel.view;

import com.hotel.controller.CustomerController;
import com.hotel.customexception.ServiceException;
import com.hotel.model.Customer;
import com.hotel.util.MyScanner;
import com.hotel.util.Validator;
import com.hotel.view.base.BaseAuthView;


public class CustomerView extends BaseAuthView {

    private final CustomerController customerController;
    private int userId;


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
            String email = showUntilValid("Enter email (Valid email format eg:abc@gmail.com )  : ",Validator::isValidEmail,"Please enter a valid email");
            String password = showUntilValid("Enter password  (At least one uppercase, one lowercase, one digit, min 6 chars, eg :Admin123 ) : ", Validator::isValidPassword,"Please enter a valid password");
            String phone = showUntilValid("Enter phone number (10-digit Indian mobile number starting 6–9, eg:9876543210 ) : ",Validator::isValidEmail,"Invalid phone Number.please try again ");
            String address = MyScanner.getString("Enter address : ");
            Customer customer = new Customer(name, email, password, phone, address);
            try{
                userId = customerController.insert(customer);
                if(userId == 0){
                    System.out.println("Customer sign up failed or user already exists");
                }
                else{
                    System.out.println("Customer sign up successful");
                }

            }
            catch (ServiceException e){
                System.out.println("Sorry sign up failed");
            }
        }

        private void login(){
            String[] credentials = getLogInCredentials();
            try {
                userId = customerController.login(credentials[0], credentials[1]);

                if(userId == 0){
                    System.out.println("Customer login failed");
                }
                else{
                    System.out.println("Customer login successful");
                }

            }
            catch (ServiceException e){
                System.out.println("Sorry login failed");
            }
        }


    }




