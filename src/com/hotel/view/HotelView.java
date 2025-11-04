package com.hotel.view;

import com.hotel.controller.HotelController;
import com.hotel.customexception.ServiceException;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.service.HotelService;
import com.hotel.util.MyScanner;
import com.hotel.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;


public class HotelView extends BaseView {

    private List<BookingDisplay> hotelList;
    private HotelController hotelController;
    private int operatorId;

    public HotelView(HotelController hotelController,int operatorId) throws ServiceException {
        this.hotelController = hotelController;
        this.operatorId = operatorId;
    }

    public void showMenu(){
        while(true) {

            printHeader("HOTEL MANAGEMENT");
            System.out.println("1.View All Booking For My Hotel\n2.View My Hotel Details\n3.Back");
            int choice = MyScanner.getInt("Enter your choice :");

            switch (choice) {
                case 1:

            }
        }
    }




    private void getHotelBookings(){
       try {
           hotelList = hotelController.getHotelList(operatorId);
       }
       catch (ServiceException e) {
           System.out.println("Failed to get hotel bookings");
       }

       if(hotelList == null){
           System.out.println("No hotel bookings found");
       }
       printHeader("HOTEL BOOKINGS");
       for(BookingDisplay booking : hotelList){
           System.out.println(booking);
       }
    }












}
