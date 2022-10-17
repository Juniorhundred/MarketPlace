package com.challenges.MarketPlace.entryPoint.validation.exception;


import com.challenges.MarketPlace.entryPoint.model.request.OfertaProdutoRemovidaModelRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoAtivoModelRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoOfertadoModelRequest;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
public class ValidacaoException {

    public static void validate(List<ProdutoOfertadoModelRequest> produtos) {
        Map<String, List<String>> campos = new HashMap<>();
        if (produtos.isEmpty()) {
            campos.put("produtos", List.of("Produto não pode estar vazio."));
            throw new CampoInvalidoOperacaoException(
                    campos);
        }
    }

    public static void validateAtivo (List<ProdutoAtivoModelRequest> produtos) {
        Map<String, List<String>> campos = new HashMap<>();
        if (produtos.isEmpty()) {
            campos.put("Lista Para ativar/Inativar produtos:", List.of("Lista de Produtos não pode estar vazio."));
            throw new CampoInvalidoOperacaoException(
                    campos);
        }
    }

    public static void validateOfertadoFalse (List<OfertaProdutoRemovidaModelRequest> produtos) {
        Map<String, List<String>> campos = new HashMap<>();
        if (produtos.isEmpty()) {
            campos.put("Lista Para ativarOferta/removerOferta:", List.of("Lista de Produtos não pode estar vazio."));
            throw new CampoInvalidoOperacaoException(
                    campos);
        }
    }
}
