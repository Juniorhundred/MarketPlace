package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.exceptions.IdNaoEncontradoException;
import com.challenges.MarketPlace.useCase.gateway.ProdutoAtivoGateway;
import com.challenges.MarketPlace.useCase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoAtivoUseCaseImpl implements ProdutoAtivoUseCase {

    private final ProdutoGateway produtoGateway;
    private final ProdutoAtivoGateway produtoAtivoGateway ;

    public ProdutoAtivoUseCaseImpl(ProdutoGateway produtoGateway, ProdutoAtivoGateway produtoAtivoGateway) {
        this.produtoGateway = produtoGateway;
        this.produtoAtivoGateway = produtoAtivoGateway;
    }

    @Transactional
    @Override
    public void atualizarProdutoAtivo(List<Produto> produtos) {
        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            produtoAtual.setAtivo(true);

            produtoAtual.setDataAtualizacao(getDataHora());
            produtoAtivoGateway.atualizarProdutoAtivo(produtoAtual);
        }
    }

    @Transactional
    @Override
    public void desativarProdutoAtivo(List<Produto> produtos) {
        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            produtoAtual.setAtivo(false);
            produtoAtual.setOfertado(false);
            produtoAtual.setPorcentagemOferta(0);

            produtoAtual.setDataAtualizacao(getDataHora());
            produtoAtivoGateway.desativarProdutoAtivo(produtoAtual);
        }
    }

    public Produto detalharProdutoPorId(String id) {
        return produtoGateway.detalharProdutoPorId(id)
                .orElseThrow(() -> new IdNaoEncontradoException(String
                        .format("Id não encontrado", id)));
    }

    private String getDataHora() {
        DateFormat dateFormat =
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}