package com.challenges.MarketPlace.dataProvider.implementation;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import com.challenges.MarketPlace.dataProvider.repository.ProdutoRepository;
import com.challenges.MarketPlace.useCase.domain.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class OfertaDataProviderImplTest {

    @InjectMocks
    private OfertaDataProviderImpl ofertaDataProvider;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    void atualizarOferta() {
    }

    @Test
    void listarOfertas() {
        var produtoResponse =  List.of(mockProdutoResponseEntity());

        given(produtoRepository.findAllByOfertadoTrue()).willReturn(produtoResponse);

        List<Produto> produto = ofertaDataProvider.listarOfertas();

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get(0).getId()),
                () -> assertEquals("Iphone", produto.get(0).getNome()),
                () -> assertEquals("Apple", produto.get(0).getMarca()),
                () -> assertEquals(1000.0, produto.get(0).getPreco()),
                () -> assertTrue(produto.get(0).getOfertado()),
                () -> assertEquals(30, produto.get(0).getPorcentagemOferta())
        );
    }


    @Test
    void deletarOferta() {
    }

    private ProdutoEntity mockProdutoResponseEntity() {
        return ProdutoEntity.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("Iphone")
                .descricao("16gb")
                .marca("Apple")
                .preco(1000.0)
                .dataCadastro("05/07/2022 16:55:10")
                .dataAtualizacao("05/07/2022 16:57:10")
                .ofertado(true)
                .ativo(true)
                .porcentagemOferta(30)
                .build();
    }
}