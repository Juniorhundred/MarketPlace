package com.challenges.MarketPlace.entryPoint.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProdutoUptadeModelRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagemOferta;

    public ProdutoUptadeModelRequest(String nome, String descricao, String marca, Double preco, Boolean ativo, Boolean ofertado, Integer porcentagemOferta) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.ativo = ativo;
        this.ofertado = ofertado;
        this.porcentagemOferta = porcentagemOferta;
    }
}
