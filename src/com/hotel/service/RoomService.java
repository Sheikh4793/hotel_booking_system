package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.contracts.IBaseDAO;
import com.hotel.model.Room;
import com.hotel.service.contracts.IBaseService;

import java.util.List;

public class RoomService implements IBaseService<Room>{

    private final IBaseDAO<Room> roomDAO;

    public RoomService(IBaseDAO<Room> roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
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

    @Override
    public boolean update(Room object) {
        return false;
    }

    @Override
    public boolean delete(Room object) {
        return false;
    }

    @Override
    public Room getById(Integer id) {
        return null;
    }

    @Override
    public List<Room> getAll(int hotelId)throws  ServiceException {
       try {
          return roomDAO.getAll(hotelId);
       }
       catch (DataBaseException e) {
           throw new ServiceException("unable to get all rooms", e);
       }
    }

    @Override
    public boolean isExists(Room object) {
        return false;
    }
}
