package com.challenges.MarketPlace.dataProvider.mapper.response;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class OfertaResponseMapper {

    public static Produto converter(ProdutoEntity produtoEntity) {
        return Produto.builder()
                .id(produtoEntity.getId())
                .nome(produtoEntity.getNome())
                .marca(produtoEntity.getMarca())
                .preco(produtoEntity.getPreco())
                .ofertado(produtoEntity.getOfertado())
                .porcentagemOferta(produtoEntity.getPorcentagemOferta())
                .build();
    }

    public static List<Produto> convert(List<ProdutoEntity> produtos) {
        List<Produto> produtosModelResponse = new ArrayList<>();

        produtos.forEach(produto -> {
            produtosModelResponse.add(converter((produto)));
        });
        return produtosModelResponse;
    }
}