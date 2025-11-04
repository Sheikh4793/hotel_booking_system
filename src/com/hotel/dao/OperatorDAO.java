package com.hotel.dao;


import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Operator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperatorDAO extends BaseDAO implements IUserDAO<Operator> {

    private Connection connection;

    public OperatorDAO(Connection connection) {
        super(connection);
    }


    @Override
    public int insert(Operator obj) throws DataBaseException {
        return 0;
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
    public List getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Operator object) {
        return false;
    }


    @Override
    public int login(String email, String password) throws DataBaseException {
        String sql = "SELECT id FROM hotel_operators WHERE email = ? AND password = ? ";
        int id = 0;
        try {
            try (ResultSet rs = executeQuery(sql, email, password)) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("unable to logIn operator", e);
        }
        return id;
    }
}

