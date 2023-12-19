package com.example.repository.impl;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.Reader;
import com.example.repository.BorrowRepository;
import com.example.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {
    @Override
    public void insert(Integer bookid, Integer readerid, String borrowtime, String returntime, Integer adminid, Integer State) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state) values(?,?,?,?,0)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bookid);
            preparedStatement.setInt(2,readerid);
            preparedStatement.setString(3,borrowtime);
            preparedStatement.setString(4,returntime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public void handle(Integer borrowId, Integer state, Integer adminId) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update borrow set state = ?,adminid = ? where id = ?";
        PreparedStatement preparedStatement =null;
        try {
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,adminId);
            preparedStatement.setInt(3,borrowId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public List<Borrow> findAllByReaderId(Integer id,Integer index) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select borrow.id,book.name,book.author,book.publish,borrow.borrowtime,borrow.returntime,reader.name,reader.tel,reader.cardid,borrow.state " +
                     "from book,borrow,reader " +
                     "where readerid = ? and book.id=borrow.bookid and reader.id = borrow.readerid order by book.id offset ? rows fetch next 10 rows only ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,index);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return  list;
    }

    @Override
    public List<Borrow> findAllByState(Integer state, Integer index) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select borrow.id,book.name,book.author,book.publish,borrow.borrowtime,borrow.returntime,reader.name,reader.tel,reader.cardid,borrow.state " +
                "from book,borrow,reader " +
                "where state = ? and book.id=borrow.bookid and reader.id = borrow.readerid order by book.id offset ? rows fetch next 10 rows only ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,index);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return  list;
    }

    @Override
    public int count(Integer readerid) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow where readerid = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,readerid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public int countByState(Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow where state = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }
}
