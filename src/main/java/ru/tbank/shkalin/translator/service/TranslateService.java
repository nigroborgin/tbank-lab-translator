package ru.tbank.shkalin.translator.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.tbank.shkalin.translator.dto.RequestDto;
import ru.tbank.shkalin.translator.entity.Language;
import ru.tbank.shkalin.translator.entity.Translate;
import ru.tbank.shkalin.translator.entity.User;
import ru.tbank.shkalin.translator.repository.TranslateRepository;
import ru.tbank.shkalin.translator.service.translator.Translator;
import ru.tbank.shkalin.translator.util.HttpUtil;

import java.sql.SQLException;

@Service
public class TranslateService {

    @Autowired
    private TranslateRepository translateRepository;

    @Qualifier("yandexTranslator")
    @Autowired
    private Translator translator;

    public String translateString(RequestDto requestDto, HttpServletRequest httpServletRequest) throws SQLException {

        String srcLang = requestDto.getSourceLang();
        String tgtLang = requestDto.getTargetLang();
        String srcString = requestDto.getSourceString();
        String trString = translator.translate(srcLang, tgtLang, srcString);
        String userIp = HttpUtil.getRequestIp(httpServletRequest);

        Translate translateEntity = createTranslateEntity(userIp, srcLang, tgtLang, srcString, trString);
        translateRepository.add(translateEntity);

        return trString;
    }

    private Translate createTranslateEntity(String userIp, String srcLang, String tgtLang, String srcString, String trString) {
        return Translate.builder()
                .user(User.builder().ip(userIp).build())
                .sourceLanguage(Language.builder().languageCode(srcLang).build())
                .targetLanguage(Language.builder().languageCode(tgtLang).build())
                .sourceText(srcString)
                .translatedText(trString)
                .build();
    }

}
