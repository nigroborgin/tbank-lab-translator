package ru.tbank.entity;

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
    private Language translateLanguage;
    private String sourceString;
    private String translateString;

}
