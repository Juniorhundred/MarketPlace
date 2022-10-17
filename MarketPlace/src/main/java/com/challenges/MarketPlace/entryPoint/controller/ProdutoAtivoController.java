package com.challenges.MarketPlace.entryPoint.controller;

import com.challenges.MarketPlace.entryPoint.mapper.request.ProdutoAtivoEntryPointMapperRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoAtivoModelRequest;
import com.challenges.MarketPlace.entryPoint.validation.exception.ValidacaoException;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.service.ProdutoAtivoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/challengebrq/v1/produtos/ativacoes")
public class ProdutoAtivoController {

    private final ProdutoAtivoUseCase produtoAtivoUseCase;

    public ProdutoAtivoController(ProdutoAtivoUseCase produtoAtivoUseCase) {
        this.produtoAtivoUseCase = produtoAtivoUseCase;
    }

    @PutMapping
    public ResponseEntity<?> atualizarProdutoAtivo
            (@Valid @RequestBody List<ProdutoAtivoModelRequest> produtoAtivoModelRequests) {

        ValidacaoException.validateAtivo(produtoAtivoModelRequests);
        List<Produto> produtoRequestDomain = ProdutoAtivoEntryPointMapperRequest.converterProdutosAtivos(produtoAtivoModelRequests);

        produtoAtivoUseCase.atualizarProdutoAtivo(produtoRequestDomain);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deletarProdutoAtivado
            (@Valid @RequestBody List<ProdutoAtivoModelRequest> produtoAtivoModelRequests) {

        ValidacaoException.validateAtivo(produtoAtivoModelRequests);
        List<Produto> produtoRequestDomain = ProdutoAtivoEntryPointMapperRequest.converterProdutosInativos
                (produtoAtivoModelRequests);

        produtoAtivoUseCase.desativarProdutoAtivo(produtoRequestDomain);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}