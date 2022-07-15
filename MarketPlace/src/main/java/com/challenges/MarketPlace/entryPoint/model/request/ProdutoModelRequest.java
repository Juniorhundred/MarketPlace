package com.challenges.MarketPlace.entryPoint.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProdutoModelRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
}
