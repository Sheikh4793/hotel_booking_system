package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.RoomDAO;
import com.hotel.model.Room;

import java.util.List;

public class RoomService {

    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public Room insert(Room room)throws ServiceException {
        Room room1;
        try {
            room1 = roomDAO.insert(room);

            if (room1 == null) {
                return null;
            }
        } catch (DataBaseException e) {
            throw new ServiceException("unable to add room", e);
        }
        return room1;

    }

    public Room getById(Integer hotelId) throws ServiceException {
        Room room = null;

        try{
            room = roomDAO.getById(hotelId);
        }
        catch (DataBaseException e){
            throw new ServiceException("unable to get room by hotelId",e);
        }
        return room;
    }

    public List<Room> getAll(int hotelId)throws  ServiceException {
       try {
          return roomDAO.getAll(hotelId);
       }
       catch (DataBaseException e) {
           throw new ServiceException("unable to get all rooms", e);
       }
    }

    public int update(Room room) throws ServiceException {
        try{
            return roomDAO.updateRoom(room);
        }
        catch (DataBaseException e) {
            throw new ServiceException("unable to update room",e);
        }
    }

    public Room getRoomDetails(int hotelId,int roomNumber) throws ServiceException {

        Room room = null;

        try{
            room = roomDAO.getRoomDetails(hotelId,roomNumber);
        }
        catch (DataBaseException e){
            throw new ServiceException("unable to get room by hotelId and roomNumber",e);
        }
        return room;
    }


}
