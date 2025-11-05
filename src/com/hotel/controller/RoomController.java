package com.hotel.controller;

import com.hotel.customexception.ServiceException;
import com.hotel.model.Room;
import com.hotel.service.contracts.IBaseService;

import java.util.List;

public class RoomController {

    private final IBaseService<Room> roomService ;

    public RoomController(IBaseService<Room> roomService) {
        this.roomService = roomService;
    }

    public Room insert(Room room)throws ServiceException {
        return roomService.insert(room);
    }

    public List<Room> getAllRooms(int hotelId)throws ServiceException {
       return roomService.getAll(hotelId);
    }
}
