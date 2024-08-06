package ru.tbank.shkalin.translator.service.translator.impl.yandex.api_entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IamTokenRequest {

    private String yandexPassportOauthToken;

}
