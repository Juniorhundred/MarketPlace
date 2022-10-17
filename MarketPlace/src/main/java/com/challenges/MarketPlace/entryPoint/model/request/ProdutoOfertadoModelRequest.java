package com.challenges.MarketPlace.entryPoint.model.request;

import com.challenges.MarketPlace.entryPoint.utils.RemoveEspaçoString;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoOfertadoModelRequest {

    @JsonDeserialize(using = RemoveEspaçoString.class)
    @NotBlank
    private String id;

    @NotNull
    private Integer porcentagemOferta;
}
