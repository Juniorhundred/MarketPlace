package com.challenges.MarketPlace.useCase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Departamento {

    private Long idDepartamento;
    private String nomeDepartamento;
    private String descricaoDepartamento;
}

