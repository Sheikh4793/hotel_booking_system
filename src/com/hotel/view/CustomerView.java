package com.hotel.view;

import com.hotel.controller.CustomerController;
import com.hotel.customexception.ServiceException;
import com.hotel.factory.ObjectFactory;
import com.hotel.util.MyScanner;
import com.hotel.util.Validator;
import com.hotel.view.base.BaseAuthView;
import com.hotel.view.contracts.IView;

public class CustomerView extends BaseAuthView {
    private final CustomerController customerController;
    private IView bookingView;


    public CustomerView(CustomerController customerController) {
        this.customerController = customerController;
    }

    @Override
    public void showMenu() {

        while (true) {
            printHeader("CUSTOMER MENU");
            System.out.println("1.SignUp\n2.LogIn\n3.Back");
            int choice = MyScanner.getInt("Enter choice");


            switch (choice) {
                case 1:
                    signUp();
                    userSide();
                    break;
                case 2:
                    logIn();
                    userSide();
                    break;
                case 3:
                    return;
                default:
                    invalidOption();
            }
        }
    }

    private void signUp() {
        String name = MyScanner.getString("Enter name : ");
        String email = showUntilValid("Enter email (Valid email format eg:abc@gmail.com )  : ", Validator::isValidEmail, "Please enter a valid email");
        String password = showUntilValid("Enter password  (At least one uppercase, one lowercase, one digit, min 6 chars, eg :Admin123 ) : ", Validator::isValidPassword, "Please enter a valid password");
        String phone = showUntilValid("Enter phone number (10-digit Indian mobile number starting 6–9, eg:9876543210 ) : ", Validator::isValidPhone, "Invalid phone Number.please try again ");
        String address = MyScanner.getString("Enter address : ");
        com.hotel.model.Customer customer = new com.hotel.model.Customer(name, email, password, phone, address);

        try {
            com.hotel.model.Customer savedCustomer = customerController.insert(customer);

            if (savedCustomer == null) {
                System.out.println("Customer sign-up failed or user already exists");
            } else {
                System.out.println("Customer sign-up successful");
            }

        } catch (ServiceException e) {
            System.out.println("Sorry, sign-up failed");
        }
    }


    private void logIn() {
        String[] credentials = getLogInCredentials();
        try {
            com.hotel.model.Customer customer = customerController.login(credentials[0], credentials[1]);

            if (customer == null) {
                System.out.println("Customer login failed");
            } else {
                System.out.println("Customer login successful");
                try {
                    bookingView = ObjectFactory.getBookingView();
                }
                catch (Exception e) {
                    System.out.println("Booking view failed");
                }
            }

        } catch (ServiceException e) {
            System.out.println("Sorry, login failed");
        }
    }

    private void userSide(){
        bookingView.showMenu();
    }
}
