package com.hotel.view;

import com.hotel.model.Hotel;
import com.hotel.view.base.BaseView;

import java.util.List;


public class HotelView extends BaseView {

    private final List<Hotel> hotelList;

    public HotelView(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public void showMenu(){
        printHeader("");
    }

    public List<Hotel> getAll(){
        return hotelList;
    }

    private void viewHotelDetails(int hotelId){
        for(Hotel hotel : hotelList){
            if(hotel.getId() == hotelId){
                System.out.println(hotel);
                break;
            }
        }
    }







}
