package com.challenges.MarketPlace.useCase.gateway;

import com.challenges.MarketPlace.useCase.domain.Produto;
import org.springframework.transaction.annotation.Transactional;

public interface ProdutoAtivoGateway {
    void atualizarProdutoAtivo(Produto produtoAtual);

    void desativarProdutoAtivo(Produto produtoAtual);

}
