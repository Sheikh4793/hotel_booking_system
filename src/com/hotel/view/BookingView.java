package com.hotel.view;

import com.hotel.controller.BookingController;
import com.hotel.customexception.ServiceException;
import com.hotel.factory.ObjectFactory;
import com.hotel.model.Room;
import com.hotel.dto.BookingDisplay;
import com.hotel.dto.HotelDisplay;
import com.hotel.util.MyScanner;
import com.hotel.util.ReferenceGenerator;
import com.hotel.util.Session;
import com.hotel.util.Validator;
import com.hotel.view.base.BaseView;
import com.hotel.view.contracts.IView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BookingView extends BaseView<Void> {


    private HotelView hotelView;
    private IView roomView;
    private final BookingController bookingController;

    public BookingView(BookingController bookingController) {
        this.bookingController = bookingController;
    }


    public void showMenu() {
        while (true) {
            printHeader("BOOKING MENU");
            System.out.println("1.View all hotels\n2.View hotels by location\n3.Book a room\n4.view my bookings\n5.cancel booking\n6.view all booking history\n7.Logout");
            int choice = MyScanner.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    hotelView.getAllHotels();
                    break;
                case 2:
                    System.out.println("Please enter the location you would like to view:");
                    String location = MyScanner.getString("Enter the location: ");
                    hotelView.viewHotelsByLocation(location);
                    break;
                case 3:
                    bookRooms();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    cancelBooking();
                    break;
                case 6:
                    viewMyBookingHistory();
                    break;
                case 7:
                    return;
                default:
                    invalidOption();
            }
        }

    }

    private void bookRooms() {

        try {
            printHeader("BOOKING ROOM");
            List<Integer> selectedRoomIds = new ArrayList<>();
            String location = showUntilValid(() -> MyScanner.getString("Enter city or location: "), Validator::isValidString, "Invalid type. Try again.");
            List<HotelDisplay> hotels = hotelView.getHotelsByLocation(location);

            if (hotels.isEmpty()) {
                System.out.println("No hotels found in this location.");
                return;
            }

            for (int i = 0; i < hotels.size(); i++) {
                HotelDisplay h = hotels.get(i);
                System.out.printf("%d. %s (%s, %s)%n", i + 1, h.getHotelName(), h.getCityName(), h.getAddress());
            }

            int hotelChoice = showUntilValid(() -> MyScanner.getInt("select hotel number: "), input -> Validator.isValidChoice(input, 1, hotels.size()), "Invalid hotel number. please try again ");

            HotelDisplay selectedHotel = hotels.get(hotelChoice - 1);

            LocalDate checkIn = showUntilValid(() -> MyScanner.getDate("Enter check-in date (YYYY-MM-DD): "), Validator::isValidDate, "check-in is only possible for coming dates. please try again.");
            LocalDate checkOut;
            while (true) {
                checkOut = showUntilValid(() -> MyScanner.getDate("Enter check-out date (YYYY-MM-DD): "), Validator::isValidDate, "check-out is only possible for coming dates. please try again.");
                if (!Validator.isValidDateRange(checkIn, checkOut)) {
                    System.out.println("check-out date must be after check-in. please try again.");
                    continue;
                }
                break;
            }

            String repeat;
            BigDecimal total = BigDecimal.valueOf(0.0);
            List<Room> availableRooms = bookingController.getAvailableRooms(selectedHotel.getHotelId(), checkIn, checkOut);
            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for these dates.");
                return;
            }


            Map<String, BigDecimal> typeToPrice = new HashMap<>();
            Map<String, Long> typeToCount = new HashMap<>();

            for (Room r : availableRooms) {
                String type = r.getRoomType().toString();
                typeToPrice.putIfAbsent(type, r.getPricePerNight());
                typeToCount.put(type, typeToCount.getOrDefault(type, 0L) + 1);
            }

            while (true) {
            printHeader("AVAILABLE ROOM TYPES");
            List<String> types = new ArrayList<>(typeToPrice.keySet());

            for (int i = 0; i < types.size(); i++){
                String t = types.get(i);
                System.out.printf("%d. %s | ₹%.2f/night | %d available%n",
                        i + 1, t, typeToPrice.get(t).doubleValue(), typeToCount.get(t));
            }

            int typeChoice = showUntilValid(() -> MyScanner.getInt("select room type number"), input -> Validator.isValidChoice(input, 1, types.size()), "Invalid room type. please try again");
            String chosenType = types.get(typeChoice - 1);

            int qty;
            while (true) {
                qty = showUntilValid(() -> MyScanner.getInt("How many rooms do you want to book? "), Validator::isPositiveNumber, "Invalid room number,it should be greater than 0. please try again");
                long availableCount = typeToCount.get(chosenType);
                if (qty > availableCount) {
                    System.out.println("Requested quantity not available.please try again.");
                    continue;
                }
                typeToCount.put(chosenType, typeToCount.get(chosenType) - qty);
                break;
            }

            long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
            BigDecimal unitPrice = typeToPrice.get(chosenType);
            total = unitPrice.multiply(BigDecimal.valueOf(nights)).multiply(BigDecimal.valueOf(qty));

            System.out.printf("Total: %d night(s) × %d room(s) × ₹%.2f = ₹%.2f%n", nights, qty, unitPrice, total);

            for (Room r : availableRooms) {
                if (r.getRoomType().toString().equals(chosenType)) {
                    selectedRoomIds.add(r.getRoomId());
                    if (selectedRoomIds.size() == qty) break;
                }
            }

            System.out.println("Total amount is : " + "₹" + total);
            String confirm = showUntilValid(() -> MyScanner.getString("Confirm payment (yes/No) : "), Validator::isValidConfirmation, "Invalid input type. Try again.");

            if (!confirm.trim().equalsIgnoreCase("yes")) {
                System.out.println("Booking cancelled by user.");
                return;
            }

            boolean success = bookingController.bookRooms(Session.getCustomerId(), selectedRoomIds, checkIn, checkOut, total);

            System.out.println(success ? "Booking successful!" : "Booking failed. Please try again.");

            repeat = showUntilValid(() -> MyScanner.getString("Do you want to add rooms again (Yes/No)"), Validator::isValidConfirmation, "Invalid input type.");
            if (repeat.equalsIgnoreCase("No")) {
                break;
            }
        }
            } catch(ServiceException e){
                System.out.println("booking issue occurred.");
            }
    }


    private void viewBookings() {
        List<BookingDisplay> bookings = List.of();
        try {
            bookings = bookingController.getBookingsByCustomer(Session.getCustomerId());
            if (bookings.isEmpty()) {
                System.out.println("No bookings found.");
                return;
            }
            printHeader("MY BOOKINGS");

            for (int i = 0; i < bookings.size(); i++) {
                BookingDisplay b = bookings.get(i);
                long nights = ChronoUnit.DAYS.between(b.getCheckIn(), b.getCheckOut());
                System.out.printf(
                        "%d. Booking ID: %s | Room Number : %s | %s | %s to %s | %s | ₹%.2f%n",
                        i + 1, ReferenceGenerator.generateReference(b.getBookingId()), b.getRoomNumber(), b.getRoomType(),
                        b.getCheckIn(), b.getCheckOut(), b.getStatus(), b.getTotalAmount()
                );
            }
        } catch (ServiceException e) {
            System.out.println("booking issue occurred.");
        }
    }

    private void cancelBooking() {

        try {

            List<BookingDisplay> bookings = bookingController.getBookingsByCustomer(Session.getCustomerId());

            if (bookings.isEmpty()) {
                System.out.println("You have no bookings to cancel.");
                return;
            }
            printHeader("CANCEL BOOKING");

            for (int i = 0; i < bookings.size(); i++) {
                BookingDisplay b = bookings.get(i);
                System.out.printf("%d. Booking ID : %s | Room %s | %s to %s | %s%n",
                        i + 1, ReferenceGenerator.generateReference(b.getBookingId()), b.getRoomNumber(), b.getCheckIn(), b.getCheckOut(), b.getStatus());
            }

            int choice = showUntilValid(() -> MyScanner.getInt("Enter booking number to cancel: "), input -> Validator.isValidChoice(input, 1, bookings.size()), "Invalid choice. please try again");
            int bookingId = bookings.get(choice - 1).getBookingId();
            String confirm = showUntilValid(() -> MyScanner.getString("Confirm cancellation (yes/No) : "), Validator::isValidConfirmation, "Invalid input type. Try again.");
            if (!confirm.trim().equalsIgnoreCase("yes")) {
                return;
            }
            boolean ok = bookingController.cancelBooking(bookingId);
            System.out.println(ok ? "Booking cancelled successfully." : "Cancellation failed.");
        } catch (ServiceException e) {
            System.out.println("problem occurred with cancellation");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void viewMyBookingHistory() {
        List<BookingDisplay> bookings = List.of();
        try {
            bookings = bookingController.getBookingHistory(Session.getCustomerId());
            if (bookings.isEmpty()) {
                System.out.println("No bookings found.");
                return;
            }
            printHeader("BOOKING HISTORY");

            for (int i = 0; i < bookings.size(); i++) {
                BookingDisplay b = bookings.get(i);
                long nights = ChronoUnit.DAYS.between(b.getCheckIn(), b.getCheckOut());
                System.out.printf(
                        "%d. Booking ID: %s | Room %s | %s | %s to %s | %s | ₹%.2f%n",
                        i + 1, ReferenceGenerator.generateReference(b.getBookingId()), b.getRoomNumber(), b.getRoomType(),
                        b.getCheckIn(), b.getCheckOut(), b.getStatus(), b.getTotalAmount()
                );
            }
        } catch (ServiceException e) {
            System.out.println("booking issue occurred.");
        }
    }


    {
        try {
            hotelView = ObjectFactory.getHotelView();
            roomView = ObjectFactory.getRoomView();
        } catch (Exception e) {
            System.out.println("Hotel view exception");
        }
    }


}
