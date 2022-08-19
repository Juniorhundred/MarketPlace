package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.gateway.OfertaGateway;
import com.challenges.MarketPlace.useCase.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
class OfertaUseCaseImplTest {

    @InjectMocks
    private OfertaUseCaseImpl ofertaUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private OfertaGateway ofertaGateway;

    @Test
    void atualizarOferta() {

    }

    @Test
    void listarOfertas() {
        var produtoCriado = mockProdutoResponse();

        given(ofertaGateway.listarOfertas()).willReturn(List.of(produtoCriado));

        List<Produto> produto = ofertaUseCase.listarOfertas();

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get(0).getId()),
                () -> assertEquals("Refrigerante", produto.get(0).getNome()),
                () -> assertEquals("Coca-Cola", produto.get(0).getMarca()),
                () -> assertEquals(10.0, produto.get(0).getPreco())
        );
    }

    @Test
    void deletarOferta() {
    }

    @Test
    void detalharProdutoPorId() {
        var produtoCriado = mockProdutoResponse();

        given(produtoGateway.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108"))
                .willReturn(Optional.of(produtoCriado));

        Produto produto = ofertaUseCase.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108");

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId())
        );
    }

    private Produto mockProdutoResponse() {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("Refrigerante")
                .descricao("Sabor: UVA")
                .marca("Coca-Cola")
                .preco(10.0)
                .dataCadastro("05/07/2022 16:55:10")
                .ofertado(false)
                .ativo(true)
                .porcentagemOferta(0)
                .build();
    }
}