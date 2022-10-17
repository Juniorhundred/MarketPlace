package com.challenges.MarketPlace.entryPoint.handler;

import lombok.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor

public enum ExceptionEnum {

    INTERNAL_SERVER_ERROR("/erro-interno-de-servico", 500),
    NOT_FOUND("/conteudo-nao-encontrado", 404),
    CONFLICT("/entidade-em-uso", 409),
    UNPROCESSABLE_ENTITY("/erro-de-validacoes", 422);

    private final String url;
    private final Integer httpStatus;

    public String getUrl() {
        return url;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public static String Url(Integer httpStatusValue) {
        if (Objects.isNull(httpStatusValue)) {
            return ExceptionEnum.INTERNAL_SERVER_ERROR.getUrl();
        }

        return Arrays.stream(values())
                .filter(url -> url.getHttpStatus().equals(httpStatusValue))
                .map(ExceptionEnum::getUrl)
                .collect(Collectors.joining());
    }

}
