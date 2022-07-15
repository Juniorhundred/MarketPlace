package com.challenges.MarketPlace.dataProvider.mapper.response;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoResponseMapperTest {

    @Test
    void converterEntityParaDomain() {
        ProdutoEntity mockProductEntity = mockProductEntity();
        Produto produto = ProdutoResponseMapper.converterEntityParaDomain(mockProductEntity);
        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId()),
                () -> assertEquals("Refrigerante", produto.getNome()),
                () -> assertEquals("Sabor: UVA", produto.getDescricao()),
                () -> assertEquals("Coca-Cola", produto.getMarca()),
                () -> assertEquals(10.0, produto.getPreco()),
                () -> assertEquals("05/07/2022 16:55:10", produto.getDataCadastro()),
                () -> assertEquals("06/07/2022 16:55:10", produto.getDataAtualizacao()),
                () -> assertTrue(produto.getAtivo()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(0, produto.getPorcentagemOferta())
        );
    }

    private ProdutoEntity mockProductEntity() {
        return ProdutoEntity.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("Refrigerante")
                .descricao("Sabor: UVA")
                .marca("Coca-Cola")
                .preco(10.0)
                .dataCadastro("05/07/2022 16:55:10")
                .dataAtualizacao("06/07/2022 16:55:10")
                .ativo(true)
                .ofertado(false)
                .porcentagemOferta(0)
                .build();
    }


}