package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Customer;
import com.hotel.service.contracts.IUserService;
import com.hotel.util.Session;

import java.util.List;

public class CustomerService implements IUserService<Customer> {

    private final IUserDAO<Customer> customerDAO;

    public CustomerService(IUserDAO<Customer> customerDAO){
        this.customerDAO = customerDAO;
    }

    public Customer insert(Customer customer) throws ServiceException {
        try {

            Customer customer1 = customerDAO.insert(customer);

            if (customer1 == null) {
                return null;
            }

            Session.setCustomerId(customer1.getCustomerId());
            return customer1;

        } catch (DataBaseException e) {
            throw new ServiceException("Unable to insert customer", e);
        }
    }


    public Customer logIn(String email, String password) throws ServiceException {
        try {
            Customer customer1 = customerDAO.login(email, password);

            if (customer1 == null) {
                return null;
            }

            Session.setCustomerId(customer1.getCustomerId());

            return customer1;

        } catch (DataBaseException e) {
            throw new ServiceException("Unable to log in customer", e);
        }
    }


    @Override
    public boolean update(Customer object) {
        return false;
    }

    @Override
    public boolean delete(Customer object) {
        return false;
    }

    @Override
    public Customer getById(Integer id) {
        return null;
    }

    @Override
    public List<Customer> getAll(int id) {
        return List.of();
    }

    @Override
    public boolean isExists(Customer object) {
        return false;
    }


}
