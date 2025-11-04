package com.hotel.view;

import com.hotel.controller.BookingController;
import com.hotel.factory.ObjectFactory;
import com.hotel.util.MyScanner;
import com.hotel.view.base.BaseView;
import com.hotel.view.contracts.IView;

public class BookingView extends BaseView {


    private HotelView hotelView ;
    private final BookingController bookingController;

    public BookingView(BookingController bookingController) {
        this.bookingController = bookingController;
    }





    public void showMenu(){
        while(true){
            printHeader("BOOKING MENU");
            System.out.println("1.View all hotels\n2.View hotels by location\n3.View available rooms\n4.Book a room\n5.view my bookings\n6.cancel booking\n7.Logout");
            int choice = MyScanner.getInt("Enter your choice: ");

            switch (choice){
                case 1:
                    hotelView.getAllHotels();
                    break;
                case 2:
                    System.out.println("Please enter the location you would like to view:");
                    String location = MyScanner.getString("Enter the location: ");
                    hotelView.viewHotelsByLocation(location);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    return;
                default:
                    invalidOption();
            }
        }

    }
    {
        try {
            hotelView = ObjectFactory.getHotelView();
        }
        catch (Exception e){
            System.out.println("Hotel view exception");
        }
    }
}
