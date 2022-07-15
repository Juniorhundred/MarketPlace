package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.exceptions.ValidarNomeDuplicadoException;
import com.challenges.MarketPlace.useCase.exceptions.ValidarPrecoException;
import com.challenges.MarketPlace.useCase.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
class ProdutoUseCaseImplTest {

    @InjectMocks
    private ProdutoUseCaseImpl produtoUseCaseImpl;
    
    @Mock
    private ProdutoGateway produtoGateway;
    
    @Test
    void criarProdutoTest() {
        Produto criarProduto = mockProdutoRequest(10.0);
        Produto produtoCriado = mockProductResponse();

        given(produtoGateway.findByNomeProduto(criarProduto.getNome())).willReturn(Optional.empty());

        given(produtoGateway.criarProduto(criarProduto)).willReturn(produtoCriado);

        Produto produto = produtoUseCaseImpl.criarProduto(criarProduto);

    }

    @Test
    void ProdutoNomeDuplicadoTest(){
        Produto produto = mockProdutoRequest(10.0);
        given(produtoGateway.findByNomeProduto(produto.getNome())).willReturn(Optional.of(mockProductResponse()));

        assertThrows((ValidarNomeDuplicadoException.class),
                () -> produtoUseCaseImpl.criarProduto(produto));


    }



    @Test
    void produtoPrecoNegativo (){

        Produto produto = mockProdutoRequest(-100.00);
        assertThrows((ValidarPrecoException.class),
                () -> produtoUseCaseImpl.criarProduto(produto));
    }

    @Test
    void produtoPrecoZerado (){

        Produto produto = mockProdutoRequest(0.00);
        assertThrows((ValidarPrecoException.class),
                () -> produtoUseCaseImpl.criarProduto(produto));
    }

    @Test
    void buscarListaDeProdutos(){


    }

    @Test
    void detalharProdutoPorId(){

    }



    private Produto mockProductResponse() {
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

    private Produto mockProdutoRequest(double price) {
        return Produto.builder()
                .nome("Refrigerante")
                .descricao("Sabor: UVA")
                .marca("Coca-Cola")
                .preco(price)
                .build();
    }
}