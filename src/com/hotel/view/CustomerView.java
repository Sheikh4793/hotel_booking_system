package com.hotel.view;

import com.hotel.controller.CustomerController;
import com.hotel.customexception.ServiceException;
import com.hotel.factory.ObjectFactory;
import com.hotel.model.Customer;
import com.hotel.util.MyScanner;
import com.hotel.util.Validator;
import com.hotel.view.base.BaseAuthView;
import com.hotel.view.contracts.IView;

public class CustomerView<T> extends BaseAuthView<T>{
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
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    return;
                default:
                    invalidOption();
            }
        }
    }

    private void signUp() {
        String name = showUntilValid(()->MyScanner.getString("Enter user name : "),Validator::isValidName,"Please enter a valid name");
        String email = showUntilValid(()->MyScanner.getString("Enter email (Valid email format eg:abc@gmail.com )  : "), Validator::isValidEmail, "Please enter a valid email");
        String password = showUntilValid(()->MyScanner.getString("Enter password  (At least one uppercase, one lowercase, one digit, min 6 chars, eg :Admin123 ) : "), Validator::isValidPassword, "Please enter a valid password");
        String phone = showUntilValid(()->MyScanner.getString("Enter phone number (10-digit Indian mobile number starting 6–9, eg:9876543210 ) : "), Validator::isValidPhone,"Invalid phone Number.please try again ");
        String address = MyScanner.getString("Enter address : ");
        Customer customer = new Customer(name, email, password, phone, address);

        try {
            Customer savedCustomer = customerController.insert(customer);

            if (savedCustomer == null) {
                System.out.println("Customer sign-up failed or user already exists");
                return;
            } else {
                System.out.println("Customer sign-up successful");
            }
            userSide();

        }
        catch (ServiceException e) {
            System.out.println("unable to insert customer");
        }
    }


    private void logIn() {
        String[] credentials = getLogInCredentials();
        try {
            Customer customer = customerController.login(credentials[0], credentials[1]);

            if (customer == null) {
                System.out.println("Invalid password or  UserName ");
                return;
            } else {
                System.out.println("Customer login successful");
                bookingView = ObjectFactory.getBookingView();
            }
            userSide();
        }
        catch (ServiceException e) {
            System.out.println("Sorry, login failed. user doesn't exist");
        }
         catch (Exception e) {
            System.out.println("Booking view failed");
        }
    }

    private void userSide(){
        bookingView.showMenu();
    }

    {
        try {
            bookingView = ObjectFactory.getBookingView();
        }
        catch (Exception e) {
            System.out.println("Booking view failed");
        }
    }
}
