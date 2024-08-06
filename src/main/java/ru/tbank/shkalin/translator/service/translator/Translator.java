package ru.tbank.shkalin.translator.service.translator;


public interface Translator {

    String translate(String sourceLang, String targetLang, String sourceString);

}
