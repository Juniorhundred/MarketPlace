package com.challenges.MarketPlace.useCase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Produto {

    private String id;
    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagemOferta;
    private String dataCadastro;
    private String dataAtualizacao;
}
