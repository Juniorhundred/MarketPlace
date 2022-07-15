package com.challenges.MarketPlace.entryPoint.mapper.request;

import com.challenges.MarketPlace.entryPoint.model.request.ProdutoModelRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoUptadeModelRequest;
import com.challenges.MarketPlace.useCase.domain.Produto;

public class ProdutoEntryPointMapperRequest {

    private ProdutoEntryPointMapperRequest(){}

    public static Produto converterEntryPointParaDomain (ProdutoModelRequest produtoModelRequest){

        return Produto.builder()
                .nome(produtoModelRequest.getNome())
                .descricao(produtoModelRequest.getDescricao())
                .marca(produtoModelRequest.getMarca())
                .preco(produtoModelRequest.getPreco())
                .build();
    }

    public static Produto converterEntryPointParaDomainUpdate(ProdutoUptadeModelRequest produtoUptadeModelRequest){

        return Produto.builder()
                .nome(produtoUptadeModelRequest.getNome())
                .descricao(produtoUptadeModelRequest.getDescricao())
                .marca(produtoUptadeModelRequest.getMarca())
                .preco(produtoUptadeModelRequest.getPreco())
                .ativo(produtoUptadeModelRequest.getAtivo())
                .ofertado(produtoUptadeModelRequest.getOfertado())
                .porcentagemOferta(produtoUptadeModelRequest.getPorcentagemOferta())
                .build();
    }



}
