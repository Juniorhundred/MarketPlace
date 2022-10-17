package com.challenges.MarketPlace.dataProvider.mapper.request;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAtivoRequestMapper {

    public static ProdutoEntity converter(Produto produto) {
        return ProdutoEntity.builder()
                .id(produto.getId())
                .build();
    }

    public static List<ProdutoEntity> convert(List<Produto> produtos){
        List<ProdutoEntity> produtosModelResponse = new ArrayList<>();

        produtos.forEach(produto -> {
            produtosModelResponse.add(converter((produto)));
        });
        return produtosModelResponse;
    }
}

