package com.hotel.view;

import com.hotel.controller.HotelController;
import com.hotel.customexception.ServiceException;
import com.hotel.model.Hotel;
import com.hotel.dto.BookingDisplay;
import com.hotel.dto.HotelDisplay;
import com.hotel.util.MyScanner;
import com.hotel.util.ReferenceGenerator;
import com.hotel.util.Session;
import com.hotel.view.base.BaseView;

import java.util.List;


public class HotelView extends BaseView {


    private final HotelController hotelController;
    private  int operatorId;

    public HotelView(HotelController hotelController) throws ServiceException {
        this.hotelController = hotelController;
    }

    public void showMenu(){
        while(true) {

            printHeader("HOTEL MANAGEMENT");
            System.out.println("1.View All Booking For My Hotel\n2.View My Hotel Details\n3.Back");
            int choice = MyScanner.getInt("Enter your choice :");

            switch (choice) {
                case 1:
                    getHotelBookings();
                    break;
                case 2:
                    getHotelDetails();
                    break;
                case 3:
                    return;
                default:
                    invalidOption();
            }
        }
    }




    private void getHotelBookings(){
       try {
           List<BookingDisplay> hotelList = hotelController.getHotelList(Session.getOperatorId());

           if(hotelList.isEmpty()){
               System.out.println("No hotel bookings found");
               return;
           }

           int i =0;

           for(BookingDisplay booking : hotelList){
               System.out.printf("%d. Booking ID: %s | Room Number : %s | %s | %s to %s | %s | ₹%.2f%n", i+=1, ReferenceGenerator.generateReference(booking.getBookingId()), booking.getRoomNumber(), booking.getRoomType(), booking.getCheckIn(), booking.getCheckOut(), booking.getStatus(), booking.getTotalAmount());
               printDividerLine();
           }
       }
       catch (ServiceException e) {
           System.out.println("Failed to get hotel bookings");
       }

    }

    private void getHotelDetails(){
        try {
           List<HotelDisplay> hotelDetails = hotelController.getHotelDetails(Session.getOperatorId());

           if(hotelDetails.isEmpty()){
               System.out.println("No hotel Details found");
               return;
           }

           for(HotelDisplay hotel : hotelDetails){
               System.out.println(hotel);
               printDividerLine();
           }

        }
        catch (ServiceException e) {
            System.out.println("Failed to get hotel details");

        }
    }

    public void getAllHotels(){
        try {
            List<Hotel> hotels = hotelController.getAllHotels(Session.getOperatorId());

            if (hotels.isEmpty()) {
                System.out.println("No hotels found");
                return;
            }

            for(Hotel hotel : hotels){
                System.out.println(hotel);
                printDividerLine();
            }
        }
        catch (ServiceException e) {
            System.out.println("Failed to get hotels");
        }
    }

    public void viewHotelsByLocation(String location){
        try {
            List<HotelDisplay> hotels = hotelController.getHotelsByLocation(location);

            if(hotels.isEmpty()){
                System.out.println("No hotels found");
                return;
            }
            for(HotelDisplay hotel : hotels){
                System.out.println(hotel);
                printDividerLine();
            }
        }

        catch (ServiceException e){
            System.out.println("Failed to get hotels by location");
        }
    }

    public List<HotelDisplay> getHotelsByLocation(String location){
        List<HotelDisplay> hotels = hotelController.getHotelsByLocation(location);
        return hotels;
    }

    }










