package com.challenges.MarketPlace.entryPoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DepartamentoModelResponse {

    private  Integer idDepartamento;
    private String nomeDepartamento;
    private String descricaoDepartamento;
}
