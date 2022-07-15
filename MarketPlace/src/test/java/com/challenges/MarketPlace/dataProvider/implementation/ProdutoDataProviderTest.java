package com.challenges.MarketPlace.dataProvider.implementation;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.dataProvider.repository.ProdutoRepository;
import com.challenges.MarketPlace.useCase.domain.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ProdutoDataProviderTest {

    @InjectMocks
    private ProdutoDataProvider produtoDataProvider;

    @Mock
    private ProdutoRepository produtoRepository;


    @Test
    void criarProdutoTestSucesso() {
        ProdutoEntity produtoResponse = mockProdutoEntity();
        Produto produtoRequest = mockProduto();

        given(produtoRepository.save(Mockito.any())).willReturn(produtoResponse);

        Produto produto = produtoDataProvider.criarProduto(produtoRequest);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId()),
                () -> assertEquals("Refrigerante", produto.getNome()),
                () -> assertEquals("Sabor: UVA", produto.getDescricao()),
                () -> assertEquals("Coca-Cola", produto.getMarca()),
                () -> assertEquals(10.0, produto.getPreco()),
                () -> assertEquals("05/07/2022 16:55:10", produto.getDataCadastro()),
                () -> assertTrue(produto.getAtivo()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(0, produto.getPorcentagemOferta())
        );
    }

    private ProdutoEntity mockProdutoEntity() {
        return ProdutoEntity.builder()
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

    @Test
    void findByNameTestSucesso() {
        Produto produtoRequest = mockProduto();
        ProdutoEntity produtoResponse = mockProdutoEntity();

        given(produtoRepository.findByNome(produtoRequest.getNome()))
                .willReturn(Optional.of(produtoResponse));

        Optional<Produto> produto = produtoDataProvider.findByNomeProduto(produtoRequest.getNome());

        assertTrue(produto.isPresent());
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get().getId()),
                () -> assertEquals("Refrigerante", produto.get().getNome()),
                () -> assertEquals("Sabor: UVA", produto.get().getDescricao()),
                () -> assertEquals("Coca-Cola", produto.get().getMarca()),
                () -> assertEquals(10.0, produto.get().getPreco()),
                () -> assertEquals("05/07/2022 16:55:10", produto.get().getDataCadastro()),
                () -> assertTrue(produto.get().getAtivo()),
                () -> assertFalse(produto.get().getOfertado()),
                () -> assertEquals(0, produto.get().getPorcentagemOferta())
        );
    }
}