package ru.tbank.shkalin.translator.service.translator.impl.yandex.api_entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslateResponse {

    private List<TranslateElem> translations;

}
