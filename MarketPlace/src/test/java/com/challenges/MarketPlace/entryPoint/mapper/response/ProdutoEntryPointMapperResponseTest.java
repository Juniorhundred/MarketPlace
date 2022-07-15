package com.challenges.MarketPlace.entryPoint.mapper.response;

import com.challenges.MarketPlace.entryPoint.model.response.ProdutoResponseListFiltro;
import com.challenges.MarketPlace.entryPoint.model.response.ProdutoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoEntryPointMapperResponseTest {

    @Test
    void converterDomainParaEntryPointTestSucesso() {

        Produto mockProduto = mockProduto();

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse
                .converterDomainParaEntryPoint(mockProduto);

        assertNotNull(produtoModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoModelResponse.getId()),
                () -> assertEquals("Refrigerante", produtoModelResponse.getNome()),
                () -> assertEquals("Sabor: UVA", produtoModelResponse.getDescricao()),
                () -> assertEquals("Coca-Cola", produtoModelResponse.getMarca()),
                () -> assertEquals(10.0, produtoModelResponse.getPreco()),
                () -> assertTrue(produtoModelResponse.getAtivo()),
                () -> assertFalse(produtoModelResponse.getOfertado()),
                () -> assertEquals(0, produtoModelResponse.getPorcentagemOferta()),
                () -> assertEquals("05/07/2022 16:55:10", produtoModelResponse.getDataCadastro()),
                () -> assertEquals(null, produtoModelResponse.getDataAtualizacao())
        );
    }

    private Produto mockProduto() {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("Refrigerante")
                .descricao("Sabor: UVA")
                .marca("Coca-Cola")
                .preco(10.0)
                .dataCadastro("05/07/2022 16:55:10")
                .dataAtualizacao(null)
                .ativo(true)
                .ofertado(false)
                .porcentagemOferta(0)
                .build();
    }

    @Test
    void converterDomainParaEntrypointListFiltro(){
        Produto mockPoduto = mockProduto();

        ProdutoResponseListFiltro responseListFiltro = ProdutoEntryPointMapperResponse
                .converterDomainParaEntrypointListFiltro(mockPoduto);

        assertNotNull(responseListFiltro);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", responseListFiltro.getId()),
                () -> assertEquals("Refrigerante", responseListFiltro.getNome()),
                () -> assertEquals("Coca-Cola", responseListFiltro.getMarca()),
                () -> assertEquals(10.0, responseListFiltro.getPreco())

        );


    }

    @Test
    void converterListaProdutosTestSucesso(){
        var mockListProduto = mockListProduto();

        List<ProdutoResponseListFiltro> produtoResponseListFiltro = ProdutoEntryPointMapperResponse.converterListaProdutos(List.of(mockListProduto));

        assertNotNull(produtoResponseListFiltro);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoResponseListFiltro.get(0).getId()),
                () -> assertEquals("Refrigerante", produtoResponseListFiltro.get(0).getNome()),
                () -> assertEquals("Coca-Cola", produtoResponseListFiltro.get(0).getMarca()),
                () -> assertEquals(10.0, produtoResponseListFiltro.get(0).getPreco())

        );


    }

    private Produto mockListProduto() {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("Refrigerante")
                .marca("Coca-Cola")
                .preco(10.0)
                .build();



    }
}