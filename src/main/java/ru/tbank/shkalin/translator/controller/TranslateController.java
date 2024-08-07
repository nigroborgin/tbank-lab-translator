package ru.tbank.shkalin.translator.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import ru.tbank.shkalin.translator.dto.RequestDto;
import ru.tbank.shkalin.translator.dto.ResponseDto;

import ru.tbank.shkalin.translator.service.TranslateService;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1")
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getTranslate(
            @RequestBody RequestDto requestDto,
            HttpServletRequest request) {

        String responseText = "";
        HttpStatusCode status = HttpStatus.OK;
        try {
            responseText = translateService.translateString(requestDto, request);
        } catch (SQLException e) {
            responseText = "Ошибка на стороне сервера";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (RestClientResponseException e) {
            status = e.getStatusCode();
            if (status.is4xxClientError()) {
                responseText = "Ошибка клиента";
            }
            if (status.is5xxServerError()) {
                responseText = "Ошибка на стороне внешнего сервера перевода";
            }

        }

        ResponseDto response = new ResponseDto(responseText);
        return new ResponseEntity<>(response, status);
    }

//    private String exceptionHandler(Exception e) {
//        if ()
//    }

}
