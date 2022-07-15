package com.challenges.MarketPlace.entryPoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProdutoModelResponse {

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
