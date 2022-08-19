package com.challenges.MarketPlace.entryPoint.mapper.response;

import com.challenges.MarketPlace.entryPoint.model.response.DepartamentoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Departamento;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoEntrypointMapperResponse {

    public static DepartamentoModelResponse converterDomainParaEntrypoint (Departamento departamento) {

        return DepartamentoModelResponse.builder()
                .idDepartamento(departamento.getIdDepartamento())
                .nomeDepartamento(departamento.getNomeDepartamento())
                .descricaoDepartamento(departamento.getDescricaoDepartamento())
                .build();
    }

    public static List<DepartamentoModelResponse> converterDomainsParaEntrypoint(List<Departamento> departamentos){
        List<DepartamentoModelResponse> departamentoModelResponse = new ArrayList<>();

        departamentos.forEach(departamento -> {
            departamentoModelResponse.add(converterDomainParaEntrypoint((departamento)));
        });
        return departamentoModelResponse;
    }
}
