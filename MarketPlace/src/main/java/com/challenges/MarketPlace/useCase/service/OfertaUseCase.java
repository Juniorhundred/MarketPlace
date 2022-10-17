package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.List;

public interface OfertaUseCase {

    void atualizarOferta(List<Produto> produtos);

    Produto detalharProdutoPorId(String idProduto);

    List<Produto> listarOfertas();

    void deletarOferta(List<Produto> produtos);
}