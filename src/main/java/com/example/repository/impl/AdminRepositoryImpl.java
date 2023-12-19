package com.example.repository.impl;

import com.example.entity.Admin;
import com.example.repository.AdminRepository;
import com.example.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookadmin where username = ? and password = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Admin admin = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                admin = new Admin(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return admin;
    }
}
