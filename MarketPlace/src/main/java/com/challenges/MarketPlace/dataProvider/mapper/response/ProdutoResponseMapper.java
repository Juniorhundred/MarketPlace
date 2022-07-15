package com.challenges.MarketPlace.dataProvider.mapper.response;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;

public class ProdutoResponseMapper {

    public static Produto converterEntityParaDomain (ProdutoEntity produtoEntity){
        return Produto.builder()
                .id(produtoEntity.getId())
                .nome(produtoEntity.getNome())
                .descricao(produtoEntity.getDescricao())
                .marca(produtoEntity.getMarca())
                .preco(produtoEntity.getPreco())
                .dataCadastro(produtoEntity.getDataCadastro())
                .dataAtualizacao(produtoEntity.getDataAtualizacao())
                .ativo(produtoEntity.getAtivo())
                .ofertado(produtoEntity.getOfertado())
                .porcentagemOferta(produtoEntity.getPorcentagemOferta())
                .build();

    }

}
