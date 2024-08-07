package ru.tbank.shkalin.translator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tbank.shkalin.translator.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LanguageDao {

    @Autowired
    private Connection connection;

    public void insert(Language language) throws SQLException {
        String sql = "INSERT INTO language (lang_code) VALUES (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, language.getLanguageCode());

        preparedStatement.execute();
    }

    public Language getBy(String langCode) throws SQLException {
        String sql =
                "SELECT id, lang_code " +
                "FROM language " +
                "WHERE lang_code = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, langCode);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return Language.builder()
                    .id(resultSet.getInt("id"))
                    .languageCode(resultSet.getString("lang_code"))
                    .build();
        } else {
            return null;
        }
    }

}
