package com.challenges.MarketPlace.dataProvider.mapper.request;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;

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
                .departamentos(DepartamentoRequestMapper.convertList(produto.getDepartamentos()))
                .build();
    }


}
