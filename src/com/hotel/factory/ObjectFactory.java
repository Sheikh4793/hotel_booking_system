package com.hotel.factory;


import com.hotel.controller.*;
import com.hotel.dao.*;
import com.hotel.service.*;
import com.hotel.util.DBConnection;
import com.hotel.view.*;
import com.hotel.view.contracts.IView;

import java.io.IOException;
import java.sql.SQLException;


public class ObjectFactory {


    private static IView IView;


    //homeview,operator,customer dependency injection
    public static IView getHomeVIew() throws SQLException, IOException, ClassNotFoundException {
        if (IView == null) {
            IView = new HomeView(new OperatorView(new OperatorController(new OperatorService(new OperatorDAO(DBConnection.getInstance().getConnection())))), new CustomerView(new CustomerController(new CustomerService(new CustomerDAO(DBConnection.getInstance().getConnection())))));

        }
        return IView;
    }

    //hotel dependency injection
    public static HotelView getHotelView() throws SQLException, IOException, ClassNotFoundException {
        return new HotelView(new HotelController(new HotelService(new HotelDAO(DBConnection.getInstance().getConnection()))));
    }

    //room dependency injection
    public static IView getRoomView() throws SQLException, IOException, ClassNotFoundException {
        return new RoomView(new RoomController(new RoomService(new RoomDAO(DBConnection.getInstance().getConnection()))));
    }

    //booking dependency injection
    public static IView getBookingView() throws SQLException, IOException, ClassNotFoundException {
        return new BookingView(new BookingController(new BookingService(new BookingDAO(DBConnection.getInstance().getConnection()))));
    }



    private ObjectFactory() {

    }
}
