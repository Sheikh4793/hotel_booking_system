package com.hotel.view;


import com.hotel.controller.RoomController;
import com.hotel.customexception.ServiceException;
import com.hotel.model.Room;
import com.hotel.model.constants.RoomType;
import com.hotel.util.MyScanner;
import com.hotel.util.Session;
import com.hotel.view.base.BaseView;

import java.util.List;

public class RoomView extends BaseView {

    private int hotelId;
    private final RoomController roomController ;

    public RoomView(RoomController roomController) {
        this.roomController = roomController;
    }



    public void showMenu() {

        while (true) {
            printHeader("ROOM MANAGEMENT");
            System.out.println("1.Add Room\n2.view All Rooms\n3.Back");
            int choice = MyScanner.getInt("Enter your choice : ");

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    getAllRooms(Session.getHotelId());
                    break;
                case 3:
                    return;
                default:
                    invalidOption();
            }
        }
    }

    private void addRoom() {
        Room room1 = null;
        int count = MyScanner.getInt("Enter number of rooms you want to add: ");
        int startingNumber = MyScanner.getInt("Enter start room number you want to add: ");
        int hotelId = Session.getHotelId();
        RoomType roomType = getRoomType();
        int capacity = MyScanner.getInt("Enter room capacity you want to add: ");
        int price = MyScanner.getInt("Enter room price per night you want to add: ");

        for(int i = startingNumber; i <= count; i++) {
            Room room = new Room();
            room.setHotelId(hotelId);
            room.setCapacity(capacity);
            room.setPricePerNight(price);
            room.setRoomType(roomType);
            room.setRoomNumber(i);
            room.setAvailable(true);
           room1 = roomController.insert(room);

            if(room1 == null){
                System.out.println("Room has been added successfully");
                return;
            }

        }
            System.out.println("Room addition not successfull");
        }


    private void getAllRooms(int hotelId) {
        try {
            System.out.println(Session.getHotelId());
            List<Room> rooms = roomController.getAllRooms(hotelId);

            if(rooms.isEmpty()){
                System.out.println("No rooms are found");
                return;
            }

            for(Room room : rooms){
                System.out.println(room);
                printDividerLine();
            }

        }
        catch (ServiceException e) {
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
            choice = MyScanner.getInt("Enter your choice (1-" + types.length + "): ");
            if (choice < 1 || choice > types.length) {
                System.out.println("Invalid choice. Try again.");
            }
        } while (choice < 1 || choice > types.length);

        return types[choice - 1];
    }

}

