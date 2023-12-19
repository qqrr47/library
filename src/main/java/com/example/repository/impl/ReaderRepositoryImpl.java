package com.example.repository.impl;

import com.example.entity.Reader;
import com.example.repository.ReaderRepository;
import com.example.utils.JDBCTools;

import java.sql.*;

public class ReaderRepositoryImpl implements ReaderRepository {

    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username = ? and password = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Reader reader = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                reader = new Reader(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return reader;
    }

    @Override
    public boolean findByUsername(String username) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Reader reader = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return false;
    }

    @Override
    public void addReader(String username, String password, String name, String tel, String cardId, String gender) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into reader(username,password,name,tel,cardid,gender) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        Reader reader = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,tel);
            preparedStatement.setString(5,cardId);
            preparedStatement.setString(6,gender);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }
}
