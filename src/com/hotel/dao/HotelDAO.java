package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.dao.contracts.IHotelDAO;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends BaseDAO implements IHotelDAO {

    public HotelDAO(Connection connection){
        super(connection);
    }

    @Override
    public List<BookingDisplay> getHotelBookings(int operatorId) throws DataBaseException{
        List<BookingDisplay> bookings = new ArrayList<>();
        BookingDisplay booking = new BookingDisplay();
        String query = "SELECT b.id AS booking_id, c.name AS customer_name, c.email AS customer_email, r.room_number, r.type AS room_type, b.check_in, b.check_out, b.status, b.total_amount "
                + "FROM hotel_bookings b JOIN hotel_customers c ON b.customer_id = c.id "
                + "JOIN hotel_rooms r ON b.room_id = r.id "
                + "JOIN hotels h ON r.hotel_id = h.id "
                + "JOIN hotel_operators o ON o.hotel_id = h.id "
                + "WHERE o.id = ? AND b.check_in >= CURRENT_DATE "
                + "ORDER BY b.check_in;";

      try{
          try(ResultSet rs=executeQuery(query,operatorId)){
              while(rs.next()){
                  booking.setBookingId(rs.getInt("booking_id"));
                  booking.setCustomerName(rs.getString("customer_name"));
                  booking.setCustomerEmail(rs.getString("customer_email"));
                  booking.setRoomNumber(rs.getString("room_number"));
                  booking.setRoomType(rs.getString("room_type"));
                  booking.setCheckIn(rs.getDate("check_in").toLocalDate());
                  booking.setCheckOut(rs.getDate("check_out").toLocalDate());
                  booking.setStatus(rs.getString("status"));
                  booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                  bookings.add(booking);
              }
          }

      } catch (SQLException e) {
          throw new DataBaseException("unable to get hotel bookings",e);
      }
      return bookings;
    }

    @Override
    public int insert(Hotel obj) throws DataBaseException {
        return 0;
    }

    @Override
    public boolean update(Hotel object) {
        return false;
    }

    @Override
    public boolean delete(Hotel object) {
        return false;
    }

    @Override
    public Hotel getById(Integer id) {
        return null;
    }

    @Override
    public List<Hotel> getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Hotel object) {
        return false;
    }
}
