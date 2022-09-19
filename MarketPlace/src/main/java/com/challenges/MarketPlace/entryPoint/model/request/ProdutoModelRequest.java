package com.challenges.MarketPlace.entryPoint.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoModelRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private List<Integer> codigosDepartamento;
}
