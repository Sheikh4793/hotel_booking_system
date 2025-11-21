package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.constants.HotelType;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.dto.HotelDisplay;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends BaseDAO  {


    public HotelDAO() throws SQLException, ClassNotFoundException, IOException {

    }

    public List<BookingDisplay> getHotelBookings(int operatorId) throws DataBaseException {
        List<BookingDisplay> bookings = new ArrayList<>();
        String query = "SELECT b.id AS booking_id, c.name AS customer_name, c.email AS customer_email, r.room_number, r.type AS room_type, b.check_in, b.check_out, b.status, b.total_amount FROM hotel_bookings b JOIN hotel_customers c ON b.customer_id = c.id JOIN hotel_rooms r ON b.room_id = r.id JOIN hotels h ON r.hotel_id = h.id JOIN hotel_operators o ON o.hotel_id = h.id WHERE o.id = ? AND b.check_in >= CURRENT_DATE ORDER BY b.check_in";

        try (ResultSet rs = executeQuery(query, operatorId)) {
            while (rs.next()) {
                BookingDisplay booking = new BookingDisplay();
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

        } catch (SQLException e) {
            throw new DataBaseException("unable to get hotel bookings", e);
        }
        return bookings;
    }

    public List<HotelDisplay> getHotelDetails(int operatorId) throws DataBaseException {
        List<HotelDisplay> hotels = new ArrayList<>();
        String query = "SELECT h.id, h.hotel_name, h.type, h.contact_number, h.email, h.address,l.city_name, l.state, l.country FROM hotels h JOIN hotel_locations l ON h.location_id = l.id JOIN hotel_operators o ON o.hotel_id = h.id WHERE o.id = ?";
        try (ResultSet rs = executeQuery(query, operatorId)) {
            while (rs.next()) {
                HotelDisplay hotel = new HotelDisplay();
                hotel.setHotelId(rs.getInt("id"));
                hotel.setHotelName(rs.getString("hotel_name"));
                hotel.setType(HotelType.valueOf(rs.getString("type").toUpperCase()));
                hotel.setContactNumber(rs.getString("contact_number"));
                hotel.setEmail(rs.getString("email"));
                hotel.setAddress(rs.getString("address"));
                hotel.setCityName(rs.getString("city_name"));
                hotel.setState(rs.getString("state"));
                hotel.setCountry(rs.getString("country"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            throw new DataBaseException("unable to get hotel details from db", e);
        }
        return hotels;
    }


    public List<Hotel> getAll(int id) throws DataBaseException {
        Hotel hotel = null;
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotels ";
        try (ResultSet resultSet = executeQuery(query)) {
            while (resultSet.next()) {
                hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setName(resultSet.getString("hotel_name"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setHotelType(HotelType.valueOf(resultSet.getString("type").toUpperCase()));
                hotel.setContactNumber(resultSet.getString("contact_number"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            throw new DataBaseException("unable to get hotels from db", e);
        }
        return hotels;
    }

    public List<HotelDisplay> getHotelByLocation(String location) throws DataBaseException {
        List<HotelDisplay> hotels = new ArrayList<>();
        String query = "SELECT h.*, l.city_name, l.state, l.country FROM hotels h JOIN hotel_locations l ON h.location_id = l.id WHERE LOWER(l.city_name) LIKE LOWER(?)";

        try (ResultSet resultSet = executeQuery(query, location)) {
            while (resultSet.next()) {
                HotelDisplay hotel = new HotelDisplay();
                hotel.setHotelId(resultSet.getInt("id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setType(HotelType.valueOf(resultSet.getString("type").toUpperCase()));
                hotel.setContactNumber(resultSet.getString("contact_number"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setCityName(resultSet.getString("city_name"));
                hotel.setState(resultSet.getString("state"));
                hotel.setCountry(resultSet.getString("country"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Unable to get hotels from DB", e);
        }

        return hotels;
    }



}
