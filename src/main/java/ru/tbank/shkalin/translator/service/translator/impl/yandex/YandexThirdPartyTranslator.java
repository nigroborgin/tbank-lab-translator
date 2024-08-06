package ru.tbank.shkalin.translator.service.translator.impl.yandex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tbank.shkalin.translator.service.translator.impl.ThirdPartyTranslator;
import ru.tbank.shkalin.translator.service.translator.impl.yandex.api_entities.TranslateRequest;
import ru.tbank.shkalin.translator.service.translator.impl.yandex.api_entities.TranslateResponse;

import java.util.ArrayList;
import java.util.List;

@Service("yandexTranslator")
public class YandexThirdPartyTranslator extends ThirdPartyTranslator {

    @Value("${yandex.token.iam}")
    private String iamToken;
//    @Value("${yandex.token.oauth}")
//    private String oAuthToken;
    @Value("${yandex.url.translate}")
    private String url;
    @Value("${yandex.folder-id}")
    private String folderId;

    @Override
    public String translate(String sourceLang, String targetLang, String sourceString) {
        return requestTranslate(sourceLang, targetLang, sourceString);
    }

    @Override
    public String requestTranslate(String sourceLang, String targetLang, String sourceString) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TranslateRequest> request = prepareRequest(sourceLang, targetLang, sourceString);
        TranslateResponse translateResponse = restTemplate.postForObject(url, request, TranslateResponse.class);
        String result = prepareResult(translateResponse);

        return result;
    }

    private HttpEntity<TranslateRequest> prepareRequest(String sourceLang, String targetLang, String sourceString) {
        List<String> textsList = new ArrayList<>();
        textsList.add(sourceString);

        TranslateRequest body = TranslateRequest.builder()
                .folderId(folderId)
                .texts(textsList)
                .targetLanguageCode(targetLang)
                .sourceLanguageCode(sourceLang)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + iamToken);

        return new HttpEntity<>(body, headers);
    }

    private static String prepareResult(TranslateResponse translateResponse) {
        StringBuilder result = new StringBuilder();
        translateResponse.getTranslations().forEach(elem -> result.append(elem.getText()));
        return result.toString();
    }
}
