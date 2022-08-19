package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Departamento;
import com.challenges.MarketPlace.useCase.exceptions.DepartamentoInexistenteException;
import com.challenges.MarketPlace.useCase.exceptions.ValidarNomeDuplicadoException;
import com.challenges.MarketPlace.useCase.gateway.DepartamentoGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoUseCaseImpl implements DepartamentoUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DepartamentoUseCaseImpl(DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = departamentoGateway;
    }

    @Override
    public Departamento criarDepartamento(Departamento departamento) {
        validarDuplicidadeNomeDepartamento(departamento);

        return departamentoGateway.criarDepartamento(departamento);
    }

    public void validarDuplicidadeNomeDepartamento(Departamento departamento){
        departamentoGateway.findbyNomeDepartamento(departamento.getNomeDepartamento())
                .ifPresent(exception -> {
                    throw new ValidarNomeDuplicadoException(
                                (String.format("O nome informado ja consta como cadastrado." +
                                        " Devido a isso nao é permitido cadastrar dois nomes de departamento iguais.", departamento.getNomeDepartamento())));
                });
    }

    @Override
    public List<Departamento> buscarTodosDepartamentos(String nomeDepartamento) {

        return departamentoGateway.buscarTodosDepartamentos(nomeDepartamento);
    }

    @Override
    public Departamento buscarDepartamentoPorId(Long idDepartamento) {
        return departamentoGateway.buscarDepartamentoPorId(idDepartamento)
                .orElseThrow(() -> new DepartamentoInexistenteException(
                        String.
                                format("O Id do departamento informado náo consta como cadastrado em sistema." +
                                        " Favor verificar.", idDepartamento)));
    }

    @Override
    public void excluirDepartamento(Long idDepartamento) {
        var departamento = buscarDepartamentoPorId(idDepartamento);

        departamentoGateway.excluirDepartamentoPorId(departamento.getIdDepartamento());
    }
}
