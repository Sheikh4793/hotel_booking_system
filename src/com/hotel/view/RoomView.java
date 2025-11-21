package com.hotel.view;


import com.hotel.controller.RoomController;
import com.hotel.customexception.ServiceException;
import com.hotel.model.Room;
import com.hotel.constants.RoomType;
import com.hotel.util.MyScanner;
import com.hotel.util.Session;
import com.hotel.util.Validator;
import com.hotel.view.base.BaseView;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoomView extends BaseView<Void>  {

    private final RoomController roomController;

    public RoomView(RoomController roomController) {
        this.roomController = roomController;
    }


    public void showMenu() {

        while (true) {
            printHeader("ROOM MANAGEMENT");
            System.out.println("1.Add Room\n2.View All Rooms\n3.Update Rooms\n4.Back");
            int choice = MyScanner.getInt("Enter your choice : ");

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    getAllRooms(Session.getHotelId());
                    break;
                case 3:
                    updateRoom();
                    break;
                case 4:
                    return;
                default:
                    invalidOption();
            }
        }
    }

    private void addRoom() {
        try {
            Room room1 = null;
            int count = showUntilValid(() -> MyScanner.getInt("Enter number of rooms you want to add: "), (Validator::isValidPositiveNumber), "Entered room number is invalid.please try again");
            int hotelId = Session.getHotelId();
            int startingNumber = 0;

            if (roomController.getMaxRoomNum(hotelId) == null) {
                startingNumber = 1;
            } else {
                startingNumber = roomController.getMaxRoomNum(hotelId).getRoomNumber() + 1;
            }

            RoomType roomType = getRoomType();
            int capacity = showUntilValid(() -> MyScanner.getInt("Enter room capacity you want to add: "), (Validator::isValidPositiveNumber), "Entered capacity is wrong.please try again");
            BigDecimal price = showUntilValid(() -> MyScanner.getBigDecimal("Enter room price per night you want to add: "),input->Validator.isValidPrice(input), "Price per night should always be greater than 800. try again");

            for (int i = startingNumber; i < startingNumber + count; i++) {
                Room room = new Room();
                room.setHotelId(hotelId);
                room.setCapacity(capacity);
                room.setPricePerNight(price);
                room.setRoomType(roomType);
                room.setRoomNumber(i);
                room.setAvailable(true);
                room1 = roomController.insert(room);

                if (room1 == null) {
                    System.out.println("Room addition failed");
                    return;
                }

            }
            System.out.println("Room added successfully");
        } catch (ServiceException e) {
            e.printStackTrace();
            System.out.println("error occurred in room addition");
        }

    }

    private void getAllRooms(int hotelId) {
        try {
            List<Room> rooms = roomController.getAllRooms(hotelId);
            rooms.sort(comparator);

            if (rooms.isEmpty()) {
                System.out.println("No rooms are found");
                return;
            }

            for (Room room : rooms) {
                System.out.println(room);
                printDividerLine();
            }

        } catch (ServiceException e) {
            System.out.println("unable to view hotel rooms");
        }
    }


    private RoomType getRoomType() {
        System.out.println("Select Room Type:");
        RoomType[] types = RoomType.values();

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        int choice;
        do {
            choice = showUntilValid(() -> MyScanner.getInt("Enter your choice (1-" + types.length + "): "), input -> Validator.isValidChoice(input, 1, types.length), "Invalid choice. Please try again");
            if (choice < 1 || choice > types.length) {
                System.out.println("Invalid choice. Try again.");
            }
        } while (choice < 1 || choice > types.length);

        return types[choice - 1];
    }

    private void updateRoom() {

        try {
            printHeader("ROOM UPDATING MENU");
            Room maxRoom = roomController.getMaxRoomNum(Session.getHotelId());

            if (maxRoom == null) {
                System.out.println("No rooms found  for this hotel");
                return;
            }

            int totalRooms = maxRoom.getRoomNumber();

            if (totalRooms <= 0) {
                System.out.println("No rooms found for updating...");
                return;
            }

            int roomNumber = showUntilValid(() -> MyScanner.getInt("Enter room number you want to update (Rooms available - " + totalRooms + "):"), input -> Validator.isValidRoomNumber(input, totalRooms), "Invalid room number.please enter number from available range");


            Room currentRoom = roomController.getRoomDetails(Session.getHotelId(), roomNumber);

            if (currentRoom == null) {
                System.out.println("room number doesnt exist");
                return;
            }

            RoomType currentRoomType = currentRoom.getRoomType();


            BigDecimal currentPrice = currentRoom.getPricePerNight();

            Boolean currentAvailability = currentRoom.isAvailable();

            String confirm = showUntilValid(() -> MyScanner.getString("Do you want to update room type ? (yes/No)," + "(Current type -" + currentRoomType + ") : "), Validator::isValidConfirmation, "Invalid confirmation.please try again");
            RoomType roomType = null;

            if (confirm.equalsIgnoreCase("yes")) {
                roomType = getRoomType();
            }

            confirm = showUntilValid(() -> MyScanner.getString("Do you want to update room price per night ? (yes/No)," + "(Current price -" + currentPrice + ") : "), Validator::isValidConfirmation, "Invalid confirmation.please try again");

            BigDecimal price = null;
            if (confirm.equalsIgnoreCase("yes")) {
                price = showUntilValid(() -> MyScanner.getBigDecimal("Enter price per night"), Validator::isValidPrice, "Price per night should always be greater than 800. try again");
            }

            confirm = showUntilValid(() -> MyScanner.getString("Do you want to update room availability ? (yes/No) : " + "(Current availability -" + currentAvailability + ") : "), Validator::isValidConfirmation, "Invalid confirmation.please try again");

            Boolean roomAvailability = null;
            if (confirm.equalsIgnoreCase("yes")) {
                String available = showUntilValid(() -> MyScanner.getString("If the room is available(Yes/No) :"), Validator::isValidConfirmation, "Invalid confirmation.please try again");

                if (available.equalsIgnoreCase("yes")) {
                    roomAvailability = true;
                } else {
                    roomAvailability = false;
                }

            }

            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setRoomType(roomType);
            room.setPricePerNight(price);
            room.setAvailable(roomAvailability);
            room.setHotelId(Session.getHotelId());

            int updateRoom = roomController.updateRoom(room);

            if (updateRoom == 0) {
                System.out.println("Room update failed");
            } else {
                System.out.println("Room updated successfully");
            }
        } catch (ServiceException e) {
            System.out.println("unable to update room");
        }
    }

    Comparator<Room> comparator = (room1, room2) -> room1.getRoomNumber() - room2.getRoomNumber();
}

