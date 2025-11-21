package com.hotel.controller;

import com.hotel.customexception.ServiceException;
import com.hotel.model.Room;
import com.hotel.service.RoomService;

import java.util.List;

public class RoomController {

    private final RoomService roomService ;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room insert(Room room)throws ServiceException {
        return roomService.insert(room);
    }

    public List<Room> getAllRooms(int hotelId)throws ServiceException {
       return roomService.getAll(hotelId);
    }

    public Room getMaxRoomNum(int hotelId) throws ServiceException {
        return roomService.getById(hotelId);
    }

    public Room getRoomDetails(int hotelId,int roomNumber) throws ServiceException {
        return roomService.getRoomDetails(hotelId,roomNumber);
    }

    public int updateRoom(Room room) throws ServiceException {
        return roomService.update(room);
    }
}
