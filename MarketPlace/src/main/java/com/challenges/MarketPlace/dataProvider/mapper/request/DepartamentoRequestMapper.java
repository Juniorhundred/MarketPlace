package com.challenges.MarketPlace.dataProvider.mapper.request;

import com.challenges.MarketPlace.dataProvider.entity.DepartamentoEntity;
import com.challenges.MarketPlace.useCase.domain.Departamento;

public class DepartamentoRequestMapper {

    public static DepartamentoEntity converterDomainParaEntity (Departamento departamento){

        return DepartamentoEntity.builder()
                .idDepartamento(departamento.getIdDepartamento())
                .nomeDepartamento(departamento.getNomeDepartamento())
                .descricaoDepartamento(departamento.getDescricaoDepartamento())
                .build();
    }


}
