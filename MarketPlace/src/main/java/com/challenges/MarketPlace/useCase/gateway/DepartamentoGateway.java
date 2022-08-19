package com.challenges.MarketPlace.useCase.gateway;

import com.challenges.MarketPlace.useCase.domain.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoGateway {

    Departamento criarDepartamento(Departamento departamento);

    Optional<Departamento> findbyNomeDepartamento(String nomeDepartamento);

    List<Departamento> buscarTodosDepartamentos(String nomeDepartamento);

    Optional<Departamento> buscarDepartamentoPorId(Long idDepartamento);

    void excluirDepartamentoPorId(Long idDepartamento);


}
