package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Operator;
import com.hotel.service.contracts.IUserService;
import com.hotel.util.Session;

import java.util.List;

public class OperatorService implements IUserService<Operator> {


    private final IUserDAO<Operator> operatorDAO;
    private int userId = 0;


    public OperatorService(IUserDAO<Operator> operatorDAO){
        this.operatorDAO = operatorDAO;
    }

    @Override
    public Operator logIn(String email, String password) throws ServiceException {
        try {
            Operator operator = operatorDAO.login(email, password);

            if (operator == null) {
                return null;
            }

            Session.setOperatorId(operator.getOperatorId());
            Session.setHotelId(operator.getHotelId());

            return operator;

        } catch (DataBaseException e) {
            throw new ServiceException("Unable to log in operator", e);
        }
    }


    @Override
    public Operator insert(Operator object) {
        return null;
    }

    @Override
    public boolean update(Operator object) {
        return false;
    }

    @Override
    public boolean delete(Operator object) {
        return false;
    }

    @Override
    public Operator getById(Integer id) {
        return null;
    }

    @Override
    public List<Operator> getAll(int id) {
        return List.of();
    }

    @Override
    public boolean isExists(Operator object) {
        return false;
    }
}
