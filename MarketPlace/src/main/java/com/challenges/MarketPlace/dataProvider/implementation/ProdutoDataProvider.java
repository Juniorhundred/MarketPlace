package com.challenges.MarketPlace.dataProvider.implementation;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.dataProvider.mapper.request.ProdutoRequestMapper;
import com.challenges.MarketPlace.dataProvider.mapper.response.ProdutoResponseMapper;
import com.challenges.MarketPlace.dataProvider.repository.ProdutoRepository;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.exceptions.ValidarNomeDuplicadoException;
import com.challenges.MarketPlace.useCase.gateway.ProdutoGateway;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoDataProvider implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoDataProvider(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @Override
    public Produto criarProduto(Produto produtoCriado) {
        try {
            ProdutoEntity criandoProduto = ProdutoRequestMapper.converterDomainParaEntity(produtoCriado);
            ProdutoEntity produto = produtoRepository.save(criandoProduto);

            return ProdutoResponseMapper.converterEntityParaDomain(produto);
        } catch ( Exception exception) {
            throw new ValidarNomeDuplicadoException(String.
                    format("O Produto '%s' consta como cadastrado no sistema", exception));
        }
    }

    @Override
    public Optional<Produto> findByNomeProduto(String nome) {
        return produtoRepository.findByNome(nome).map(ProdutoResponseMapper::converterEntityParaDomain);
    }

    @Override
    public List<Produto> listaProdutos() {
        return produtoRepository
                .findAll()
                .stream()
                .map(ProdutoResponseMapper::converterEntityParaDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> detalharProdutoPorId(String id) {

        return produtoRepository.findById(id)
                .map(ProdutoResponseMapper::converterEntityParaDomain);

    }

    @Override
    public void deletarProdutoPorId(String id) {
        produtoRepository.deleteById(id);

    }

    @Override
    public Produto atualizarProduto(Produto produtoAtual) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converterDomainParaEntity(produtoAtual);
        ProdutoEntity produtoCriado = produtoRepository.save(produtoEntity);
        return ProdutoResponseMapper.converterEntityParaDomain(produtoCriado);
    }


}
