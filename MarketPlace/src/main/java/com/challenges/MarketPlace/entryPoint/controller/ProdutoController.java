package com.challenges.MarketPlace.entryPoint.controller;

import com.challenges.MarketPlace.entryPoint.mapper.request.ProdutoEntryPointMapperRequest;
import com.challenges.MarketPlace.entryPoint.mapper.response.ProdutoEntryPointMapperResponse;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoModelRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoUptadeModelRequest;
import com.challenges.MarketPlace.entryPoint.model.response.ProdutoResponseListFiltro;
import com.challenges.MarketPlace.entryPoint.model.response.ProdutoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.service.ProdutoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/challengebrq/v1/produtos")
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;


    @PostMapping
    public ResponseEntity<ProdutoModelResponse> criarProduto(@RequestBody ProdutoModelRequest produtoModelRequest) {

        Produto produtoRequest = ProdutoEntryPointMapperRequest.converterEntryPointParaDomain(produtoModelRequest);
        Produto produtoResponse = produtoUseCase.criarProduto(produtoRequest);

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse
                .converterDomainParaEntryPoint(produtoResponse);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseListFiltro>> listarProdutos() {

        List<Produto> produtoResponse = produtoUseCase.buscarListaDeProdutos();
        List<ProdutoResponseListFiltro> produtoModelResponse = ProdutoEntryPointMapperResponse
                .converterListaProdutos(produtoResponse);
        return ResponseEntity.ok(produtoModelResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoModelResponse> detalharProdutoPorId(@PathVariable String id) {
        Produto produto = produtoUseCase.detalharProdutoPorId(id);

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse
                .converterDomainParaEntryPoint(produto);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarProdutoPorId(@PathVariable String id) {
        produtoUseCase.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProdutoModelResponse> atualizarProduto(@PathVariable("id") String id,
                                                                 @RequestBody ProdutoUptadeModelRequest produtoUptadeModelRequest) {
        Produto produtoRequest = ProdutoEntryPointMapperRequest.converterEntryPointParaDomainUpdate(produtoUptadeModelRequest);
        Produto produtoResponse = produtoUseCase.atualizarParcialmenteProduto(id, produtoRequest);

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterDomainParaEntryPoint(produtoResponse);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);

    }
}
