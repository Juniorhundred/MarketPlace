package com.challenges.MarketPlace.entryPoint.mapper.request;

import com.challenges.MarketPlace.entryPoint.model.request.ProdutoAtivoModelRequest;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAtivoEntryPointMapperRequest {

    public static Produto converterProdutoAtivo(ProdutoAtivoModelRequest produtoAtivoModelRequest) {
        return Produto.builder()
                .id(produtoAtivoModelRequest.getId())
                .build();
    }

    public static List<Produto> converterProdutosAtivos(List<ProdutoAtivoModelRequest> produtoAtivoModelRequest) {
        List<Produto> produtos = new ArrayList<>();

        produtoAtivoModelRequest.forEach(ativo-> {
            produtos.add(converterProdutoAtivo((ativo)));
        });

        return produtos;
    }

    public static Produto converterProdutoInativo (ProdutoAtivoModelRequest produtoAtivoModelRequest){
        return Produto.builder()
                .id(produtoAtivoModelRequest.getId())
                .build();
    }

    public static List<Produto> converterProdutosInativos(List<ProdutoAtivoModelRequest> produtoAtivoModelRequest){
        List<Produto> produtos = new ArrayList<>();

        produtoAtivoModelRequest.forEach(inativo -> {
            produtos.add(converterProdutoInativo((inativo)));
        });
        return produtos;
    }
}