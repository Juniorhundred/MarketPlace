package com.challenges.MarketPlace.entryPoint.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DepartamentoModelRequest {

    private String nomeDepartamento;
    private String descricaoDepartamento;
}
