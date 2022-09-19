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
class ProdutoDataProviderImplTest {

    @InjectMocks
    private ProdutoDataProviderImpl produtoDataProviderImpl;

    @Mock
    private ProdutoRepository produtoRepository;


    @Test
    void criarProdutoTestSucesso() {
        
    }

    @Test
    void findByNameTestSucesso() {
    }
    @Test
    void listaProdutosTestSucesso(){

    }

    @Test
    void detalharProdutoPorIdTestSucesso(){


    }

    @Test
    void deletarProdutoPorIdTestSucesso (){


    }

    @Test
    void atualizarProdutoTestSucesso(){

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

}
