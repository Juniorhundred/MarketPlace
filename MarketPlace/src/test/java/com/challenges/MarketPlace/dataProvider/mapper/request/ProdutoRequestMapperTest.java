package com.challenges.MarketPlace.dataProvider.mapper.request;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.useCase.domain.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoRequestMapperTest {

    @Test
    void converterDomainParaEntity() {
        Produto produto = mockProduto();

        ProdutoEntity produtoEntity = ProdutoRequestMapper.converterDomainParaEntity(produto);
        assertNotNull(produtoEntity);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoEntity.getId()),
                () -> assertEquals("Refrigerante", produtoEntity.getNome()),
                () -> assertEquals("Sabor: UVA", produtoEntity.getDescricao()),
                () -> assertEquals("Coca-Cola", produtoEntity.getMarca()),
                () -> assertEquals(10.0, produtoEntity.getPreco()),
                () -> assertEquals("05/07/2022 16:55:10", produtoEntity.getDataCadastro()),
                () -> assertTrue(produtoEntity.getAtivo()),
                () -> assertFalse(produtoEntity.getOfertado()),
                () -> assertEquals(0, produtoEntity.getPorcentagemOferta())

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
                .ativo(true)
                .ofertado(false)
                .porcentagemOferta(0)
                .build();
    }
}