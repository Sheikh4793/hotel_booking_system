package com.hotel.view;

import com.hotel.model.Hotel;
import com.hotel.util.MyScanner;
import com.hotel.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;


public class HotelView extends BaseView {

    private List<Hotel> hotelList;

    public HotelView(List<Hotel> hotelList) {
        this.hotelList = hotelList;
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


    private List<Hotel> getMyHotelList(){
        hotelList =
        for(Hotel h : )
    }

    private



    private void viewHotelDetails(int hotelId){
        for(Hotel hotel : hotelList){
            if(hotel.getId() == hotelId){
                System.out.println(hotel);
                break;
            }
        }
    }







}
