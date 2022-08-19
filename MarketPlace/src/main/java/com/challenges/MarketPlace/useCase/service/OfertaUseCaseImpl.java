package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.exceptions.EntityException;
import com.challenges.MarketPlace.useCase.exceptions.ProdutoAtivoException;
import com.challenges.MarketPlace.useCase.exceptions.ValidarPrecoException;
import com.challenges.MarketPlace.useCase.gateway.OfertaGateway;
import com.challenges.MarketPlace.useCase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OfertaUseCaseImpl implements OfertaUseCase {

    private final ProdutoGateway produtoGateway;
    private final OfertaGateway ofertaGateway;

    public OfertaUseCaseImpl(ProdutoGateway produtoGateway, OfertaGateway ofertaGateway) {
        this.produtoGateway = produtoGateway;
        this.ofertaGateway = ofertaGateway;
    }

    @Override
    public void atualizarOferta(List<Produto> produtos) {
        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            if (Objects.nonNull(produto.getAtivo())) {
                if (!produto.getAtivo()) {
                    throw new ProdutoAtivoException
                            (String.format("O produto %s precisa estar ativo para ser ofertado", produto.getId()));
                }
            }

            if (Objects.nonNull(produto.getPorcentagemOferta())) {
                validarPorcentagemZeradoOuNegativo(produto);
                produtoAtual.setPorcentagemOferta(produto.getPorcentagemOferta());
            }

            if(Objects.nonNull(produtoAtual.getOfertado())) {
                if(!produtoAtual.getOfertado()){
                    produtoAtual.setPorcentagemOferta(0);
                }
            }
            produtoAtual.setDataAtualizacao(getDataHora());
            ofertaGateway.atualizarOferta(produtoAtual);
        }
    }

    @Override
    public List<Produto> listarOfertas() {
        List<Produto> produtos = ofertaGateway.listarOfertas();
        if(produtos.isEmpty()) {

        }
        return ofertaGateway.listarOfertas();
    }

    @Override
    public void deletarOferta(List<Produto> produtos) {
        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            if(Objects.nonNull(produtoAtual.getOfertado())) {
                if(produtoAtual.getOfertado()){
                    produtoAtual.setOfertado(false);
                    produtoAtual.setPorcentagemOferta(0);
                }
            }
            ofertaGateway.deletarOferta(produtoAtual);
        }
    }

    @Override
    public Produto detalharProdutoPorId(String idProduto) {
        return produtoGateway.detalharProdutoPorId(idProduto)
                .orElseThrow(() -> new EntityException
                        (String.format("Produto %s não consta como cadastrado", idProduto)));
    }

    private void validarPorcentagemZeradoOuNegativo(Produto produtoRequest) {
        if (produtoRequest.getPorcentagemOferta() <= 0) {
            throw new ValidarPrecoException(String
                    .format("A porcentagem da oferta não pode ser zero ou menor.", produtoRequest
                            .getPorcentagemOferta()));
        }
    }

    private String getDataHora() {
        DateFormat dateFormat =
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}