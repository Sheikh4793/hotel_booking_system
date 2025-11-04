package com.hotel.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO {


        protected Connection conn;

        public BaseDAO(Connection conn) {
            this.conn = conn;
        }

        protected void setParams(PreparedStatement stmt, Object... params) throws SQLException {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }

        protected ResultSet executeQuery(String sql, Object... params) throws SQLException {
            PreparedStatement stmt = conn.prepareStatement(sql);
            setParams(stmt, params);
            return stmt.executeQuery();
        }

        protected int executeUpdate(String sql, Object... params) throws SQLException {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                setParams(stmt, params);
                return stmt.executeUpdate();
            }
        }

        protected void close(AutoCloseable resource) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception ignored) {
                }
            }
        }
    }


