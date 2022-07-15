package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.List;


public interface ProdutoUseCase {

    Produto criarProduto (Produto produto);

    List<Produto> buscarListaDeProdutos ();

    Produto detalharProdutoPorId(String id);


    void deletarProduto(String id);


    Produto atualizarParcialmenteProduto(String id, Produto produto);
}
