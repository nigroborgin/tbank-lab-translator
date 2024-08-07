package ru.tbank.shkalin.translator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tbank.shkalin.translator.entity.Translate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TranslateDao {

    @Autowired
    private Connection connection;

    public void insert(Translate translate) throws SQLException {
        String sql = "INSERT INTO translate (user_id, source_lang_id, target_lang_id, source_text, translated_text) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, translate.getUser().getId());
        preparedStatement.setInt(2, translate.getSourceLanguage().getId());
        preparedStatement.setInt(3, translate.getTargetLanguage().getId());
        preparedStatement.setString(4, translate.getSourceText());
        preparedStatement.setString(5, translate.getTranslatedText());

        preparedStatement.execute();
    }

}
