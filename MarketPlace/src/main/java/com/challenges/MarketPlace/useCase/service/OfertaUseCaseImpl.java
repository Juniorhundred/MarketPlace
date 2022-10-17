package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.entryPoint.validation.exception.CampoInvalidoOperacaoException;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.exceptions.*;
import com.challenges.MarketPlace.useCase.gateway.OfertaGateway;
import com.challenges.MarketPlace.useCase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
            try {
                Produto produtoAtual = detalharProdutoPorId(produto.getId());

                if (!produtoAtual.getAtivo()) {
                    throw new ProdutoAtivoException
                            (String.format("produtos inativos não podem ser ofertados"));
                }
                produtoAtual.setOfertado(true);

                if(Objects.isNull(produto.getPorcentagemOferta())) {
                    throw new PorcentagemProdutoException
                            (String.format("A Porcentagem de Oferta não pode ser nulo"));
                }

                if (Objects.nonNull(produto.getPorcentagemOferta())) {
                    validarPorcentagemZeradoOuNegativo(produto);
                    produtoAtual.setPorcentagemOferta(produto.getPorcentagemOferta());
                }

                produtoAtual.setOfertado(true);

                produtoAtual.setDataAtualizacao(getDataHora());
                ofertaGateway.atualizarOferta(produtoAtual);

            }catch (IdNaoEncontradoException exception) {
                Map<String, List<String>> campos = new HashMap<>();
                campos.put("id", List.of(exception.getMessage()));
                throw new CampoInvalidoOperacaoException(campos);
            }
        }
    }

    @Override
    public List<Produto> listarOfertas() {
        List<Produto> produtos = ofertaGateway.listarOfertas();
        if(produtos.isEmpty()) {

            throw new ConteudoNaoEncontradoException("Não existem ofertas no momento.");

        }
        return ofertaGateway.listarOfertas();
    }

    @Transactional
    @Override
    public void deletarOferta(List<Produto> produtos) {
        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            if( produtoAtual.getOfertado()) {
                produtoAtual.setOfertado(false);
                produtoAtual.setPorcentagemOferta(0);
                }

            ofertaGateway.deletarOferta(produtoAtual);
        }
    }

    @Override
    public Produto detalharProdutoPorId(String idProduto) {
        return produtoGateway.detalharProdutoPorId(idProduto)
                .orElseThrow(() -> new EntityNotExistException
                        (String.format("Produto %s não consta como cadastrado", idProduto)));
    }

    private void validarPorcentagemZeradoOuNegativo(Produto produtoRequest) {
        if (produtoRequest.getPorcentagemOferta() <= 0 ) {
            throw new ValidarPorcentagemOfertaException(String
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