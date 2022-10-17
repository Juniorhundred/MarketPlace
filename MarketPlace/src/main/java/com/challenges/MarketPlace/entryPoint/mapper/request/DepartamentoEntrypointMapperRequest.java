package com.challenges.MarketPlace.entryPoint.mapper.request;

import com.challenges.MarketPlace.entryPoint.model.request.DepartamentoModelRequest;
import com.challenges.MarketPlace.useCase.domain.Departamento;

public class DepartamentoEntrypointMapperRequest {

    public static Departamento converterEntrypointParaDomain (DepartamentoModelRequest departamentoModelRequest) {

        return Departamento.builder()
                .nomeDepartamento(departamentoModelRequest.getNomeDepartamento())
                .descricaoDepartamento(departamentoModelRequest.getDescricaoDepartamento())
                .build();
    }
}
