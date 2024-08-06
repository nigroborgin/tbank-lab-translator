package ru.tbank.shkalin.translator.service.translator.impl;

import ru.tbank.shkalin.translator.service.translator.Translator;

public abstract class ThirdPartyTranslator implements Translator {

    public abstract String requestTranslate(String sourceLang, String targetLang, String sourceString);

}
