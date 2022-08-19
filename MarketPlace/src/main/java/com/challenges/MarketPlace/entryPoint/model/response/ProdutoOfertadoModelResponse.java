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
public class ProdutoOfertadoModelResponse {

    private String id;
    private String nome;
    private String marca;
    private Double preco;
    private Boolean ofertado;
    private Integer porcentagemOferta;

}
