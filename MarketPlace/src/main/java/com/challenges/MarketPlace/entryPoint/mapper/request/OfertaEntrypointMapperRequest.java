package com.challenges.MarketPlace.entryPoint.mapper.request;

import com.challenges.MarketPlace.entryPoint.model.request.OfertaProdutoRemovidaModelRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoOfertadoModelRequest;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class OfertaEntrypointMapperRequest {

    public static Produto converterProdutoOfertado(ProdutoOfertadoModelRequest produtoOfertadoModelRequest) {
        return Produto.builder()
                .id(produtoOfertadoModelRequest.getId())
                .porcentagemOferta(produtoOfertadoModelRequest.getPorcentagemOferta())
                .build();
    }

    public static List<Produto> converterProdutosOfertados(List<ProdutoOfertadoModelRequest> produtosOfertadosModelRequest) {
        List<Produto> produtos = new ArrayList<>();

        produtosOfertadosModelRequest.forEach(oferta -> {
            produtos.add(converterProdutoOfertado((oferta)));
        });
        return produtos;
    }

    public static Produto converterRemoverOferta(OfertaProdutoRemovidaModelRequest ofertaProdutoRemovidaModelRequest){
        return Produto.builder()
                .id(ofertaProdutoRemovidaModelRequest.getId())
                .build();
    }

    public static List<Produto> converterRemoverOfertas(List<OfertaProdutoRemovidaModelRequest> ofertaProdutoRemovidaModelRequests){
        List<Produto> produtos = new ArrayList<>();

        ofertaProdutoRemovidaModelRequests.forEach(oferta -> {
            produtos.add(converterRemoverOferta((oferta)));
        });
        return produtos;
    }
}