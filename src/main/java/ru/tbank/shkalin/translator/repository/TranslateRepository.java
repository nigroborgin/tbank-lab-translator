package ru.tbank.shkalin.translator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tbank.shkalin.translator.dao.LanguageDao;
import ru.tbank.shkalin.translator.dao.TranslateDao;
import ru.tbank.shkalin.translator.dao.UserDao;
import ru.tbank.shkalin.translator.entity.Language;
import ru.tbank.shkalin.translator.entity.Translate;
import ru.tbank.shkalin.translator.entity.User;

import java.sql.SQLException;

@Repository
public class TranslateRepository {

    @Autowired
    private LanguageDao languageDao;
    @Autowired
    private TranslateDao translateDao;
    @Autowired
    private UserDao userDao;

    public void add(Translate translate) throws SQLException {

        String userIp = translate.getUser().getIp();
        String srcLangCode = translate.getSourceLanguage().getLanguageCode();
        String tgtLangCode = translate.getTargetLanguage().getLanguageCode();

        // проверяем, есть ли уже в БД такие элементы
        User checkInDbUser = userDao.getBy(userIp);
        Language checkInDbSrcLang = languageDao.getBy(srcLangCode);
        Language checkInDbTgtLang = languageDao.getBy(tgtLangCode);

        // если нет - добавляем
        if (checkInDbUser == null) {
            userDao.insert(translate.getUser());
        }
        // независимо от того, были в БД такие элементы или нет, получаем "полную версию" объекта из БД
        // и кладем ее в translate
        translate.setUser(userDao.getBy(userIp));

        if (checkInDbSrcLang == null) {
            languageDao.insert(translate.getSourceLanguage());
        }
        translate.setSourceLanguage(languageDao.getBy(srcLangCode));

        if (checkInDbTgtLang == null) {
            languageDao.insert(translate.getTargetLanguage());
        }
        translate.setTargetLanguage(languageDao.getBy(tgtLangCode));

        // кладём в БД обновлённый translate (с объектами, содержащими ID)
        translateDao.insert(translate);
    }

}
