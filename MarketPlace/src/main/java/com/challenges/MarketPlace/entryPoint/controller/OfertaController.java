package com.challenges.MarketPlace.entryPoint.controller;

import com.challenges.MarketPlace.entryPoint.mapper.request.OfertaEntrypointMapperRequest;
import com.challenges.MarketPlace.entryPoint.mapper.response.OfertaEntrypointMapperResponse;
import com.challenges.MarketPlace.entryPoint.model.request.OfertaProdutoRemovidaModelRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoOfertadoModelRequest;
import com.challenges.MarketPlace.entryPoint.model.response.ProdutoOfertadoModelResponse;
import com.challenges.MarketPlace.entryPoint.validation.exception.ValidacaoException;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.service.OfertaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/challengebrq/v1/ofertas")
public class OfertaController {

    private final OfertaUseCase ofertaUseCase;

    public OfertaController(OfertaUseCase ofertaUseCase) {
        this.ofertaUseCase = ofertaUseCase;
    }

    @PutMapping
    public ResponseEntity<?> atualizarOfertaProduto
            (@Valid @RequestBody  List<ProdutoOfertadoModelRequest> produtoOfertadoModelRequests) {

        ValidacaoException.validate(produtoOfertadoModelRequests);
        List<Produto> produtoRequestDomain = OfertaEntrypointMapperRequest.converterProdutosOfertados(produtoOfertadoModelRequests);

        ofertaUseCase.atualizarOferta(produtoRequestDomain);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoOfertadoModelResponse>> buscarProdutosOfertados() {

        List<Produto> produtoResponse = ofertaUseCase.listarOfertas();

        List<ProdutoOfertadoModelResponse> produtoOfertadoModelResponses = OfertaEntrypointMapperResponse
                .converterDomainParaEntrypointList(produtoResponse);

        if (produtoOfertadoModelResponses.isEmpty()) {
            ResponseEntity.noContent().build();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(produtoOfertadoModelResponses, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarOferta
            (@Valid @RequestBody List<OfertaProdutoRemovidaModelRequest> ofertaProdutoRemovidaModelRequests) {
        ValidacaoException.validateOfertadoFalse(ofertaProdutoRemovidaModelRequests);
        List<Produto> produtoRequestDomain = OfertaEntrypointMapperRequest
                .converterRemoverOfertas(ofertaProdutoRemovidaModelRequests);

        ofertaUseCase.deletarOferta(produtoRequestDomain);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
