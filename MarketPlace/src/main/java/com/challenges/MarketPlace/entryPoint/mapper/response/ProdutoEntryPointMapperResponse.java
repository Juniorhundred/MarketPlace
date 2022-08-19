package com.challenges.MarketPlace.entryPoint.mapper.response;

import com.challenges.MarketPlace.entryPoint.model.response.ProdutoResponseListFiltro;
import com.challenges.MarketPlace.entryPoint.model.response.ProdutoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoEntryPointMapperResponse {

    public static ProdutoModelResponse converterDomainParaEntryPoint(Produto produto){

        return ProdutoModelResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .ativo(produto.getAtivo())
                .dataCadastro(produto.getDataCadastro())
                .dataAtualizacao(produto.getDataAtualizacao())
                .ofertado(produto.getOfertado())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .build();
    }

    public static ProdutoResponseListFiltro converterDomainParaEntrypointListFiltro (Produto produto){
        return ProdutoResponseListFiltro.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .build();
    }
    public static List<ProdutoResponseListFiltro> converterListaProdutos (List<Produto> produtos){
        List<ProdutoResponseListFiltro> produtoModelResponse = new ArrayList<>();
        produtos.forEach(produto -> {
            ProdutoResponseListFiltro produtoResponseListFiltro = converterDomainParaEntrypointListFiltro(produto);
            produtoModelResponse.add(produtoResponseListFiltro);
        });
        return produtoModelResponse;

    }
}
