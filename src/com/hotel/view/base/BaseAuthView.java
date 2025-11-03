package com.hotel.view.base;

import com.hotel.util.MyScanner;
import com.hotel.util.Validator;

public class BaseAuthView extends BaseView{

    public String[] getLogInCredentials(){
        String email = MyScanner.getString("Enter email : ");
        Validator.isValidEmail(email);
        String password = MyScanner.getString("Enter password : ");
        Validator.isValidPassword(password);
        return new String[]{email,password};
    }

}
