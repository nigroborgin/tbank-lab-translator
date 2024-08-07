package ru.tbank.shkalin.translator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tbank.shkalin.translator.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    @Autowired
    private Connection connection;

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO account (ip) VALUES (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getIp());

        preparedStatement.execute();
    }

    public User getBy(String ip) throws SQLException {
        String sql =
                "SELECT id, ip " +
                "FROM account " +
                "WHERE ip = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ip);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return User.builder()
                    .id(resultSet.getInt("id"))
                    .ip(resultSet.getString("ip"))
                    .build();
        } else {
            return null;
        }
    }

}
