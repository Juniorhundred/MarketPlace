package com.challenges.MarketPlace.dataProvider.mapper.request;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRequestMapper {

    public static ProdutoEntity converterDomainParaEntity (Produto produto) {

        return ProdutoEntity.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .dataCadastro(produto.getDataCadastro())
                .dataAtualizacao(produto.getDataAtualizacao())
                .ativo(produto.getAtivo())
                .ofertado(produto.getOfertado())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .build();
    }


    public static List<ProdutoEntity> converterOfertas(List<Produto> produtos){
        List<ProdutoEntity> produtosModelResponse = new ArrayList<>();

        produtos.forEach(produto -> {
            produtosModelResponse.add(converterDomainParaEntity((produto)));
        });
        return produtosModelResponse;
    }


}
