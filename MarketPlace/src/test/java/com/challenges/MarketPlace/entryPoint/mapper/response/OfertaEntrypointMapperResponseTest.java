package com.challenges.MarketPlace.entryPoint.mapper.response;

import com.challenges.MarketPlace.entryPoint.model.response.ProdutoOfertadoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfertaEntrypointMapperResponseTest {

    @Test
    void converterDomainParaEntryPoint() {
        Produto mockProduto = mockProdutoResponse();

        ProdutoOfertadoModelResponse produtoOfertadoModelResponse = OfertaEntrypointMapperResponse.converterDomainParaEntryPoint(mockProduto);

        assertNotNull(produtoOfertadoModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoOfertadoModelResponse.getId()),
                () -> assertEquals("Refrigerante", produtoOfertadoModelResponse.getNome()),
                () -> assertEquals("Coca-Cola", produtoOfertadoModelResponse.getMarca()),
                () -> assertEquals(10.0, produtoOfertadoModelResponse.getPreco()),
                () -> assertFalse(produtoOfertadoModelResponse.getOfertado()),
                () -> assertEquals(30, produtoOfertadoModelResponse.getPorcentagemOferta())
        );
    }

    @Test
    void converterDomainParaEntrypointList() {
        var mockProduto = mockProdutoResponse();

        List<ProdutoOfertadoModelResponse> produtoOfertadoModelResponses = OfertaEntrypointMapperResponse.converterDomainParaEntrypointList(List.of(mockProduto));

        assertNotNull(produtoOfertadoModelResponses);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoOfertadoModelResponses.get(0).getId()),
                () -> assertEquals("Refrigerante", produtoOfertadoModelResponses.get(0).getNome()),
                () -> assertEquals("Coca-Cola", produtoOfertadoModelResponses.get(0).getMarca()),
                () -> assertEquals(10.0, produtoOfertadoModelResponses.get(0).getPreco()),
                () -> assertFalse(produtoOfertadoModelResponses.get(0).getOfertado()),
                () -> assertEquals(30, produtoOfertadoModelResponses.get(0).getPorcentagemOferta())

                //() -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoOfertadoModelResponses.get(1).getId()),
               // () -> assertEquals("Refrigerante", produtoOfertadoModelResponses.get(1).getNome()),
               // () -> assertEquals("Coca-Cola", produtoOfertadoModelResponses.get(1).getMarca()),
               // () -> assertEquals(10.0, produtoOfertadoModelResponses.get(1).getPreco()),
                //() -> assertFalse(produtoOfertadoModelResponses.get(1).getOfertado()),
                //() -> assertEquals(30, produtoOfertadoModelResponses.get(1).getPorcentagemOferta())
        );
    }


    private Produto mockProdutoResponse() {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("Refrigerante")
                .marca("Coca-Cola")
                .preco(10.0)
                .dataCadastro("05/07/2022 16:55:10")
                .ofertado(false)
                .porcentagemOferta(30)
                .build();

    }
}