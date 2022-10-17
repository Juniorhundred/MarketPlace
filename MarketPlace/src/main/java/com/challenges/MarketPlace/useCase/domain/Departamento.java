package com.challenges.MarketPlace.useCase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Departamento {

    private Integer idDepartamento;
    private String nomeDepartamento;
    private String descricaoDepartamento;
}

