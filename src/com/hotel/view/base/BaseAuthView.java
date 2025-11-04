package com.hotel.view.base;

import com.hotel.util.MyScanner;
import com.hotel.util.Validator;

public class BaseAuthView extends BaseView{

    public String[] getLogInCredentials(){
        String email = showUntilValid("Enter email (Valid email format eg:abc@gmail.com )  : ",Validator::isValidEmail,"Please enter a valid email");
        String password = showUntilValid("Enter password  (At least one uppercase, one lowercase, one digit, min 6 chars, eg :Admin123 ) : ", Validator::isValidPassword,"Please enter a valid password");
        return new String[]{email,password};
    }

}
