package com.hotel.service.contracts;

import com.hotel.customexception.ServiceException;


public interface IUserService<T> {
    T logIn(String email,String password)throws ServiceException;

    T register(T customer);
}
