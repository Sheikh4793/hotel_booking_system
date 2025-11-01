package com.hotel.view;

import java.util.Scanner;

import com.hotel.view.contracts.View;
import com.hotel.util.MyScanner;

public class HotelCustomerView implements View<T>, Menu {
    private final Scanner scanner = MyScanner.getScanner();

    @Override
    public void showMenu() {

    }
}
