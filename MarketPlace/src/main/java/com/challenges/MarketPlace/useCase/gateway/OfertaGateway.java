package com.challenges.MarketPlace.useCase.gateway;

import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.List;

public interface OfertaGateway {
    void atualizarOferta(Produto produto);

    List<Produto> listarOfertas();

    void deletarOferta(Produto produto);
}