package com.hotel.view.contracts;

public interface View<T> {
    void showMenu();
    T getInput();
    void showDetails(T t);
}
