package com.challenges.MarketPlace.dataProvider.mapper.response;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoResponseMapper {

    public static Produto converterEntityParaDomain (ProdutoEntity produtoEntity) {
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
                .departamentos(DepartamentoResponseMapper.convertList(produtoEntity.getDepartamentos()))
                .build();

    }

    public static List<Produto> converterEntitiesParaDomain(List<ProdutoEntity> produtosEntity) {
        List<Produto> produtos = new ArrayList<>();

        produtosEntity.forEach(produto -> {
            produtos.add(converterEntityParaDomain((produto)));
        });
        return produtos;
    }

}
