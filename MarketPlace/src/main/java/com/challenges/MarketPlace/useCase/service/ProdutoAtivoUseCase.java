package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Produto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProdutoAtivoUseCase {

    void atualizarProdutoAtivo (List<Produto> produtos);

    void desativarProdutoAtivo(List<Produto> produtos);

    Produto detalharProdutoPorId(String idProduto);


}