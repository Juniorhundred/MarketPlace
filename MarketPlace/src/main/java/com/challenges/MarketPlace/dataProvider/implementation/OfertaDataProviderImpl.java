package com.challenges.MarketPlace.dataProvider.implementation;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.dataProvider.mapper.request.ProdutoRequestMapper;
import com.challenges.MarketPlace.dataProvider.mapper.response.ProdutoResponseMapper;
import com.challenges.MarketPlace.dataProvider.repository.ProdutoRepository;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.gateway.OfertaGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OfertaDataProviderImpl implements OfertaGateway {

    private final ProdutoRepository produtoRepository;

    public OfertaDataProviderImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @Override
    public void atualizarOferta(Produto produto) {
        ProdutoEntity produtoOfertado = ProdutoRequestMapper.converterDomainParaEntity(produto);
        produtoRepository.save(produtoOfertado);
    }

    @Override
    public List<Produto> listarOfertas() {
        List<ProdutoEntity> produtos = produtoRepository.findAllByOfertadoTrue();
        return ProdutoResponseMapper.converterEntitiesParaDomain(produtos);
    }

    @Transactional
    @Override
    public void deletarOferta(Produto produto) {
            ProdutoEntity produtoAtual = ProdutoRequestMapper.converterDomainParaEntity(produto);
            produtoRepository.save(produtoAtual);
    }
}