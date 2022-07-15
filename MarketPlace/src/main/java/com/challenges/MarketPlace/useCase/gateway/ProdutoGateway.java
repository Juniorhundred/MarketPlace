package com.challenges.MarketPlace.useCase.gateway;

import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {


    Produto criarProduto(Produto produtoCriado);


     Optional<Produto> findByNomeProduto (String nome);


    List<Produto> listaProdutos();

    Optional<Produto> detalharProdutoPorId(String id);

    void deletarProdutoPorId(String id);

    Produto atualizarProduto(Produto produtoAtual);

}
