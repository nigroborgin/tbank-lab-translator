package ru.tbank.shkalin.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.shkalin.translator.dto.RequestDto;
import ru.tbank.shkalin.translator.dto.ResponseDto;
import ru.tbank.shkalin.translator.service.TranslateService;

@RestController
@RequestMapping("/api/v1")
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getTranslate(@RequestBody RequestDto requestDto) {

        String sourceLang = requestDto.getSourceLang();
        String targetLang = requestDto.getTargetLang();
        String sourceString = requestDto.getSourceString();

        String translatedString = translateService.translateString(sourceLang, targetLang, sourceString);
        ResponseDto responseTranslate = new ResponseDto(translatedString);
        ResponseEntity<ResponseDto> responseEntity = new ResponseEntity<>(responseTranslate, HttpStatus.OK);

        return responseEntity;
    }

}
