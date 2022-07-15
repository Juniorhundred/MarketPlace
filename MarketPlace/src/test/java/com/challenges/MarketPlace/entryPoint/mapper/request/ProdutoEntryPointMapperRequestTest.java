package com.challenges.MarketPlace.entryPoint.mapper.request;

import com.challenges.MarketPlace.entryPoint.model.request.ProdutoModelRequest;
import com.challenges.MarketPlace.useCase.domain.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoEntryPointMapperRequestTest {

    @Test
    void converterEntryPointParaDomainTestSucesso() {
        ProdutoModelRequest mockProduto = mockProduto();
        Produto produto = ProdutoEntryPointMapperRequest.converterEntryPointParaDomain(mockProduto);
        assertNotNull(produto);
        assertAll(
                () -> assertEquals("Iphone12", produto.getNome()),
                () -> assertEquals("16GB",produto.getDescricao()),
                () -> assertEquals("Apple",produto.getMarca()),
                () -> assertEquals(100.00,produto.getPreco())
        );
    }

    private ProdutoModelRequest mockProduto() {
        return ProdutoModelRequest.builder()
                .nome("Iphone12")
                .descricao("16GB")
                .marca("Apple")
                .preco(100.00)
                .build();
    }
}