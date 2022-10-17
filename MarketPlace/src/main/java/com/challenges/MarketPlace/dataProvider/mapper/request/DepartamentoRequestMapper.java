package com.challenges.MarketPlace.dataProvider.mapper.request;

import com.challenges.MarketPlace.dataProvider.entity.DepartamentoEntity;
import com.challenges.MarketPlace.useCase.domain.Departamento;

import java.util.List;
import java.util.stream.Collectors;

public class DepartamentoRequestMapper {

    public static DepartamentoEntity converterDomainParaEntity (Departamento departamento){

        return DepartamentoEntity.builder()
                .idDepartamento(departamento.getIdDepartamento())
                .nomeDepartamento(departamento.getNomeDepartamento())
                .descricaoDepartamento(departamento.getDescricaoDepartamento())
                .build();
    }
    public static DepartamentoEntity convert (Departamento departamento) {

        return DepartamentoEntity.builder()
                .idDepartamento(departamento.getIdDepartamento())
                .build();
    }

    public static List<DepartamentoEntity> convertList(List<Departamento> departamentos){
        return departamentos.stream()
                .map(DepartamentoRequestMapper::convert)
                .collect(Collectors.toList());
    }

}
