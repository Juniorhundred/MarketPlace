package com.challenges.MarketPlace.entryPoint.mapper.response;

import com.challenges.MarketPlace.entryPoint.model.response.ProdutoOfertadoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class OfertaEntrypointMapperResponse {

    public static ProdutoOfertadoModelResponse converterDomainParaEntryPoint(Produto produto) {
        return ProdutoOfertadoModelResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .ofertado(produto.getOfertado())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .build();
    }

    public static List<ProdutoOfertadoModelResponse> converterDomainParaEntrypointList(List<Produto> produtos) {
        List<ProdutoOfertadoModelResponse> produtoOfertadoModelResponses = new ArrayList<>();

        produtos.forEach(produto -> {
            produtoOfertadoModelResponses.add(converterDomainParaEntryPoint((produto)));
        });
        return produtoOfertadoModelResponses;
    }
}
