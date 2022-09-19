package com.challenges.MarketPlace.entryPoint.mapper.request;

import com.challenges.MarketPlace.entryPoint.model.request.ProdutoModelRequest;
import com.challenges.MarketPlace.entryPoint.model.request.ProdutoUptadeModelRequest;
import com.challenges.MarketPlace.useCase.domain.Departamento;
import com.challenges.MarketPlace.useCase.domain.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoEntryPointMapperRequest {

    private ProdutoEntryPointMapperRequest(){}

    public static Produto converterEntryPointParaDomain (ProdutoModelRequest produtoModelRequest){

        return Produto.builder()
                .nome(produtoModelRequest.getNome())
                .descricao(produtoModelRequest.getDescricao())
                .marca(produtoModelRequest.getMarca())
                .preco(produtoModelRequest.getPreco())
                .departamentos(convert(produtoModelRequest.getCodigosDepartamento()))
                .build();
    }

    private static List<Departamento> convert (List<Integer> codigosDepartamento) {
        List<Departamento> departamentos = new ArrayList<>();
        if (Objects.nonNull(codigosDepartamento)) {
            for (Integer codigo : codigosDepartamento) {
                Departamento departamento = Departamento.builder()
                        .idDepartamento(codigo)
                        .build();
                departamentos.add(departamento);
            }
        }
            return departamentos;
    }

    public static Produto converterEntryPointParaDomainUpdate(ProdutoUptadeModelRequest produtoUptadeModelRequest){

        return Produto.builder()
                .nome(produtoUptadeModelRequest.getNome())
                .descricao(produtoUptadeModelRequest.getDescricao())
                .marca(produtoUptadeModelRequest.getMarca())
                .preco(produtoUptadeModelRequest.getPreco())
                .departamentos(convert(produtoUptadeModelRequest.getCodigosDepartamento()))
                .build();
    }

}
