package com.challenges.MarketPlace.entryPoint.validation.exception;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CampoInvalidoOperacaoException extends RuntimeException {

    private final Map<String, List<String>> campos;

    public CampoInvalidoOperacaoException(Map<String, List<String>> campos) {
        this.campos=campos;
    }
}
