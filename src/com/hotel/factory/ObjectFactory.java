package com.hotel.factory;


import com.hotel.controller.*;
import com.hotel.dao.*;
import com.hotel.service.*;
import com.hotel.view.*;
import com.hotel.view.contracts.IView;

import java.io.IOException;
import java.sql.SQLException;

public class ObjectFactory {


    private static IView homeViewInstance;

    //operator,customer dependency injection
    public static IView getHomeView() throws SQLException, IOException, ClassNotFoundException {
        if (homeViewInstance == null) {

            OperatorDAO operatorDAO = new OperatorDAO();
            OperatorService operatorService = new OperatorService(operatorDAO);
            OperatorController operatorController = new OperatorController(operatorService);
            OperatorView operatorView = new OperatorView(operatorController);

            CustomerDAO customerDAO = new CustomerDAO();
            CustomerService customerService = new CustomerService(customerDAO);
            CustomerController customerController = new CustomerController(customerService);
            CustomerView customerView = new CustomerView(customerController);

            homeViewInstance = new HomeView(operatorView, customerView);
        }

        return homeViewInstance;
    }

    //hotel layers dependency injection
    public static HotelView getHotelView() throws SQLException, IOException, ClassNotFoundException {
        HotelDAO hotelDAO = new HotelDAO();
        HotelService hotelService = new HotelService(hotelDAO);
        HotelController hotelController = new HotelController(hotelService);
        return new HotelView(hotelController);
    }

    //room layers dependency injection
    public static RoomView getRoomView() throws SQLException, IOException, ClassNotFoundException {
        RoomDAO roomDAO = new RoomDAO();
        RoomService roomService = new RoomService(roomDAO);
        RoomController roomController = new RoomController(roomService);
        return new RoomView(roomController);
    }


    //booking layers dependency injection
    public static BookingView getBookingView() throws SQLException, IOException, ClassNotFoundException {
        BookingDAO bookingDAO = new BookingDAO();
        BookingService bookingService = new BookingService(bookingDAO);
        BookingController bookingController = new BookingController(bookingService);
        return new BookingView(bookingController);
    }


    private ObjectFactory() {

    }
}
