package ru.tbank.shkalin.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.shkalin.translator.service.TranslateService;

@RestController
public class TranslateController {

    @Autowired
    private TranslateService translateService;


    // TODO: убрать затычку, написать нормальный ввод
    public void getTranslate() {

        String srcLang = "en";
        String targetLang = "ru";
        String sourceString = "Hello world, this is my first program";

        String resultString = translateService.translateString(srcLang, targetLang, sourceString);
        System.out.println(resultString);
//        return resultString;
    }

}
