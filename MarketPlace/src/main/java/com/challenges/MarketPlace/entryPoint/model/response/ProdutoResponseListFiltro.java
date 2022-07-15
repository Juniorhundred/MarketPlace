package com.challenges.MarketPlace.entryPoint.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProdutoResponseListFiltro {

    private String id;
    private String nome;
    private String marca;
    private Double preco;
}
