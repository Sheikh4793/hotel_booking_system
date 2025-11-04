package com.hotel.view;


import com.hotel.model.Room;
import com.hotel.view.base.BaseView;

import java.util.List;

public class RoomView extends BaseView {

    private List<Room> roomList;

    public List<Room> getAll(){
        return null;
    }

    public void viewRoomDetails(int roomId){
        for(Room room : roomList){
            if(room.getRoomId() == roomId){
                System.out.println(room);
                break;
            }
        }
    }
}
