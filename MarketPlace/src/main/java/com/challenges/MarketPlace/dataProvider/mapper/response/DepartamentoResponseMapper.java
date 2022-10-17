package com.challenges.MarketPlace.dataProvider.mapper.response;

import com.challenges.MarketPlace.dataProvider.entity.DepartamentoEntity;
import com.challenges.MarketPlace.useCase.domain.Departamento;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoResponseMapper {

    public static Departamento converterEntityParaDomain (DepartamentoEntity departamentoEntity) {

        return Departamento.builder()
                .idDepartamento(departamentoEntity.getIdDepartamento())
                .nomeDepartamento(departamentoEntity.getNomeDepartamento())
                .descricaoDepartamento(departamentoEntity.getDescricaoDepartamento())
                .build();
    }

    public static List<Departamento> converterEntitiesParaDomain(List<DepartamentoEntity> departamentos){
        List<Departamento> departamentosModelResponse = new ArrayList<>();

        departamentos.forEach(departamento -> {
            departamentosModelResponse.add(converterEntityParaDomain((departamento)));
        });
        return departamentosModelResponse;
    }

    public static Departamento convert (DepartamentoEntity departamentoEntity) {

        return Departamento.builder()
                .idDepartamento(departamentoEntity.getIdDepartamento())
                .nomeDepartamento(departamentoEntity.getNomeDepartamento())
                .build();
    }

    public static List<Departamento> convertList(List<DepartamentoEntity> departamentos){
        List<Departamento> departamentosModelResponse = new ArrayList<>();

        departamentos.forEach(departamento -> {
            departamentosModelResponse.add(convert((departamento)));
        });
        return departamentosModelResponse;
    }
}