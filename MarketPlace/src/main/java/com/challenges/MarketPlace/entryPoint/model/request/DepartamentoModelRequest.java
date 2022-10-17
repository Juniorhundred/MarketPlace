package com.challenges.MarketPlace.entryPoint.model.request;

import com.challenges.MarketPlace.entryPoint.utils.RemoveEspaçoString;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DepartamentoModelRequest {


    @NotBlank
    @Size(max = 50)
    @JsonDeserialize(using = RemoveEspaçoString.class)
    private  String nomeDepartamento;


    @NotBlank
    @Size(max = 256)
    @JsonDeserialize(using = RemoveEspaçoString.class)
    private String descricaoDepartamento;
}
