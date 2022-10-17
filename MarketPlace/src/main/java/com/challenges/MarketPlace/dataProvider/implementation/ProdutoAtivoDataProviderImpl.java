package com.challenges.MarketPlace.dataProvider.implementation;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.dataProvider.mapper.request.ProdutoRequestMapper;
import com.challenges.MarketPlace.dataProvider.repository.ProdutoRepository;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.gateway.ProdutoAtivoGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProdutoAtivoDataProviderImpl implements ProdutoAtivoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoAtivoDataProviderImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @Override
    public void atualizarProdutoAtivo(Produto produtoAtual) {
        ProdutoEntity produtoAtivo = ProdutoRequestMapper.converterDomainParaEntity(produtoAtual);
        produtoRepository.save(produtoAtivo);
    }

    @Transactional
    @Override
    public void desativarProdutoAtivo(Produto produtoAtual) {
        ProdutoEntity produtoInativo = ProdutoRequestMapper.converterDomainParaEntity(produtoAtual);
        produtoRepository.save(produtoInativo);
    }
}
