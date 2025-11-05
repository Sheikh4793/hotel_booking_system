package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.dao.contracts.IBaseDAO;
import com.hotel.model.Room;
import com.hotel.model.constants.RoomType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends BaseDAO implements IBaseDAO<Room> {

    private Connection connection;

    public RoomDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Room insert(Room room) throws DataBaseException {
        Room room1 = null;
        String query = "INSERT INTO hotel_rooms (hotel_id, room_number, type, capacity, price_per_night, is_available) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        try (ResultSet resultSet = executeQuery(query, room.getHotelId(), room.getRoomNumber(), room.getRoomType().name(), room.getCapacity(), room.getPricePerNight(), room.isAvailable())) {

            if (resultSet.next()) {
                room1 = new Room();
                int id = resultSet.getInt("id");
                room1.setRoomId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataBaseException("Unable to insert room into DB", e);
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
    public List<Room> getAll(int hotelId)throws DataBaseException {
        String  query = "SELECT * FROM hotel_rooms WHERE hotel_id = ?";
        List<Room> rooms = new ArrayList<>();
        Room room ;
      try(ResultSet resultSet = executeQuery(query,hotelId)){
          while (resultSet.next()) {
              room = new Room();
              room.setRoomId(resultSet.getInt("id"));
              room.setHotelId(resultSet.getInt("hotel_id"));
              room.setRoomNumber(resultSet.getInt("room_number"));
              room.setRoomType(RoomType.valueOf(resultSet.getString("type")));
              room.setCapacity(resultSet.getInt("capacity"));
              room.setPricePerNight(resultSet.getInt("price_per_night"));
              room.setAvailable(resultSet.getBoolean("is_available"));
              rooms.add(room);
          }
      }
      catch (SQLException e){
          throw new DataBaseException("unable to get Rooms from DB");
      }
      return rooms;
    }

    @Override
    public boolean isExists(Room object) {
        return false;
    }
}
