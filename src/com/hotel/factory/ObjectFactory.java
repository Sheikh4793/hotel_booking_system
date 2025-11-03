package com.hotel.factory;


import com.hotel.controller.CustomerController;
import com.hotel.controller.OperatorController;
import com.hotel.dao.CustomerDAO;
import com.hotel.dao.OperatorDAO;
import com.hotel.service.CustomerService;
import com.hotel.service.OperatorService;
import com.hotel.util.DBConnection;
import com.hotel.view.OperatorView;
import com.hotel.view.CustomerView;
import com.hotel.view.HomeView;
import com.hotel.view.contracts.IView;

import java.io.IOException;
import java.sql.SQLException;


public class ObjectFactory {


   private static IView IView;


   //view layer
    public static IView getHomeVIew() throws SQLException, IOException, ClassNotFoundException {
        if(IView == null){
            IView = new HomeView(new OperatorView(new OperatorController(new OperatorService(new OperatorDAO()))),new CustomerView(new CustomerController(new CustomerService(new CustomerDAO(DBConnection.getInstance().getConnection())))));

        }
       return IView;
    }



    private  ObjectFactory(){}
}
