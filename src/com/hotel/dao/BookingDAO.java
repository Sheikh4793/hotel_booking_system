package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.model.Room;
import com.hotel.constants.RoomType;
import com.hotel.dto.BookingDisplay;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO {

    public BookingDAO() throws SQLException, ClassNotFoundException, IOException {

    }


    public List<Room> getAvailableRooms(int hotelId, LocalDate checkIn, LocalDate checkOut) throws DataBaseException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT r.id AS room_id, h.hotel_name, r.room_number, r.type, r.price_per_night FROM hotel_rooms r JOIN hotels h ON r.hotel_id = h.id WHERE r.hotel_id = ? AND NOT EXISTS (SELECT 1 FROM hotel_bookings b WHERE b.room_id = r.id AND b.status <> 'CANCELLED' AND NOT (b.check_out <= ? OR b.check_in >= ?)) ORDER BY r.type, r.price_per_night, r.room_number";
        try (ResultSet rs = executeQuery(sql, hotelId, checkIn, checkOut)) {
            while (rs.next()) {
                Room d = new Room();
                d.setRoomId(rs.getInt("room_id"));
                d.setRoomNumber(Integer.parseInt(rs.getString("room_number")));
                d.setRoomType(RoomType.valueOf(rs.getString("type")));
                d.setPricePerNight(BigDecimal.valueOf(rs.getDouble("price_per_night")));
                d.setAvailable(true);
                list.add(d);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error fetching available rooms", e);
        }
        return list;
    }


    public boolean bookRooms(int customerId, List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut, BigDecimal total) throws DataBaseException {
        String sql = "INSERT INTO hotel_bookings (customer_id, room_id, check_in, check_out,total_amount,status) VALUES (?, ?, ?, ?, ?, 'BOOKED')";
        int total1 = 0;
        try {
            for (Integer roomId : roomIds) {
                total1 += executeUpdate(sql, customerId, roomId, checkIn, checkOut, total);
            }
        } catch (SQLException e) {
            throw new DataBaseException("unable to insert bookings in to DB", e);
        }
        return total1 == roomIds.size();
    }

    public List<BookingDisplay> getBookingsByCustomer(int customerId) throws DataBaseException {
        List<BookingDisplay> list = new ArrayList<>();
        String sql = "SELECT b.id AS booking_id,c.name AS customer_name, c.email AS customer_email, r.room_number, type, b.check_in, b.check_out, b.status, (r.price_per_night * (b.check_out - b.check_in)) AS total_amount FROM hotel_bookings b JOIN hotel_rooms r ON b.room_id = r.id JOIN hotel_customers c ON b.customer_id = c.id WHERE b.customer_id = ? AND b.status = 'BOOKED' AND CURRENT_DATE <= b.check_out ORDER BY b.id DESC";
        try (ResultSet rs = executeQuery(sql, customerId)) {
            while (rs.next()) {
                BookingDisplay b = new BookingDisplay();
                b.setBookingId(rs.getInt("booking_id"));
                b.setCustomerName(rs.getString("customer_name"));
                b.setCustomerEmail(rs.getString("customer_email"));
                b.setRoomNumber(String.valueOf(rs.getInt("room_number")));
                b.setRoomType(rs.getString("type"));
                b.setCheckIn(rs.getDate("check_in").toLocalDate());
                b.setCheckOut(rs.getDate("check_out").toLocalDate());
                b.setStatus(rs.getString("status"));
                b.setTotalAmount(rs.getBigDecimal("total_amount"));
                list.add(b);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error fetching bookings", e);
        }
        return list;
    }

    public boolean cancelBooking(int bookingId) throws DataBaseException {
        int rows = 0;
        String sql = "UPDATE hotel_bookings SET status = 'CANCELLED' WHERE id = ?";
        try {
            rows = executeUpdate(sql, bookingId);
        } catch (SQLException e) {
            throw new DataBaseException("unable to cancel booking from DB", e);
        }
        return rows > 0;
    }

    public List<BookingDisplay> getBookingHistory(int customerId) throws DataBaseException {
        List<BookingDisplay> list = new ArrayList<>();
        String sql = "SELECT b.id AS booking_id,c.name AS customer_name, c.email AS customer_email, r.room_number, type, b.check_in, b.check_out, b.status, (r.price_per_night * (b.check_out - b.check_in)) AS total_amount FROM hotel_bookings b JOIN hotel_rooms r ON b.room_id = r.id JOIN hotel_customers c ON b.customer_id = c.id WHERE b.customer_id = ?  ORDER BY b.id DESC";
        try (ResultSet rs = executeQuery(sql, customerId)) {
            while (rs.next()) {
                BookingDisplay b = new BookingDisplay();
                b.setBookingId(rs.getInt("booking_id"));
                b.setCustomerName(rs.getString("customer_name"));
                b.setCustomerEmail(rs.getString("customer_email"));
                b.setRoomNumber(String.valueOf(rs.getInt("room_number")));
                b.setRoomType(rs.getString("type"));
                b.setCheckIn(rs.getDate("check_in").toLocalDate());
                b.setCheckOut(rs.getDate("check_out").toLocalDate());
                b.setStatus(rs.getString("status"));
                b.setTotalAmount(rs.getBigDecimal("total_amount"));
                list.add(b);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error fetching bookings", e);
        }
        return list;
    }
}


