package ru.tbank.shkalin.translator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Translate {

    private Integer id;
    private User user;
    private Language sourceLanguage;
    private Language targetLanguage;
    private String sourceText;
    private String translatedText;

}
