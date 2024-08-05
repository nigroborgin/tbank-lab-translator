package ru.tbank.shkalin.translator.service;

import org.springframework.stereotype.Service;

@Service
public class TranslateService {

    public String translateString(String sourceLang, String targetLang, String sourceString) {

        String[] wordsOfSource = sourceString.split(" ");

        return "";
    }

}
