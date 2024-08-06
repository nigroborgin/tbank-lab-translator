package ru.tbank.shkalin.translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.tbank.shkalin.translator.repository.LanguageRepository;
import ru.tbank.shkalin.translator.repository.TranslateRepository;
import ru.tbank.shkalin.translator.repository.UserRepository;
import ru.tbank.shkalin.translator.service.translator.Translator;

@Service
public class TranslateService {

    @Autowired
    private TranslateRepository translateRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private UserRepository userRepository;

    @Qualifier("yandexTranslator")
    @Autowired
    private Translator translator;

    public String translateString(String sourceLang, String targetLang, String sourceString) {

        String[] wordsOfSource = sourceString.split(" ");
        String translateString = translator.translate(sourceLang, targetLang, sourceString);
        // добавить запись в бд

        return translateString;
    }

}
