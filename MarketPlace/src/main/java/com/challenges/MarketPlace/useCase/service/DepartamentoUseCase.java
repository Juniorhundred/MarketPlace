package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Departamento;

import java.util.List;

public interface DepartamentoUseCase {

    Departamento criarDepartamento(Departamento departamento);

    List<Departamento> buscarTodosDepartamentos(String nomeDepartamento);

    void excluirDepartamento(Integer idDepartamento);

    Departamento buscarDepartamentoPorId(Integer idDepartamento);
}