package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.model.Room;
import com.hotel.constants.RoomType;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends BaseDAO  {

    public RoomDAO() throws SQLException, ClassNotFoundException, IOException {

    }


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
            throw new DataBaseException("Unable to insert room into DB", e);
        }

        return room1;
    }


    public Room getById(Integer hotel_id)throws DataBaseException{
        String query = "SELECT MAX(room_number) as room_number FROM hotel_rooms WHERE hotel_id = ?";
        Room room = null;
        try(ResultSet resultSet = executeQuery(query,hotel_id)){
            if(resultSet.next()){
                room = new Room();
                room.setRoomNumber(resultSet.getInt("room_number"));
            }
        }
        catch (SQLException e){
            throw new DataBaseException("unable to get room id from DB");
        }
        return room;
    }



    public List<Room> getAll(int hotelId) throws DataBaseException {
        String query = "SELECT * FROM hotel_rooms WHERE hotel_id = ?";
        List<Room> rooms = new ArrayList<>();
        Room room;
        try (ResultSet resultSet = executeQuery(query, hotelId)) {
            while (resultSet.next()) {
                room = new Room();
                room.setRoomId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setRoomNumber(resultSet.getInt("room_number"));
                room.setRoomType(RoomType.valueOf(resultSet.getString("type")));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setPricePerNight(resultSet.getBigDecimal("price_per_night"));
                room.setAvailable(resultSet.getBoolean("is_available"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new DataBaseException("unable to get Rooms from DB");
        }
        return rooms;
    }

    public int updateRoom(Room room) throws DataBaseException {
        String query = "UPDATE hotel_rooms SET type = COALESCE(?,type) , price_per_night = COALESCE(?,price_per_night), is_available = COALESCE(?,is_available) WHERE hotel_id = ? AND room_number = ?";
        int rows = 0;
        try{
            String type = room.getRoomType() != null ? room.getRoomType().name() : null;
            BigDecimal price = room.getPricePerNight();
            Boolean available = room.isAvailable();
            rows = executeUpdate(query,type,price,available,room.getHotelId(),String.valueOf(room.getRoomNumber()));
        }
        catch (SQLException e){
            throw new DataBaseException("unable to update hotel_rooms table in  DB",e);
        }
        return rows;
    }

    public Room getRoomDetails(int hotelId,int roomNumber) throws DataBaseException {
        String query = "SELECT * FROM hotel_rooms WHERE hotel_id = ? AND room_number = ?";

        Room room = null;

        try(ResultSet resultSet = executeQuery(query,hotelId,String.valueOf(roomNumber))){
            if(resultSet.next()){
                room = new Room();
                room.setRoomId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setRoomNumber(resultSet.getInt("room_number"));
                room.setRoomType(RoomType.valueOf(resultSet.getString("type")));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setPricePerNight(resultSet.getBigDecimal("price_per_night"));
                room.setAvailable(resultSet.getBoolean("is_available"));
            }
        }
        catch (SQLException e){
            throw new DataBaseException("unable to get room details from DB",e);
        }
        return room;
    }



}
