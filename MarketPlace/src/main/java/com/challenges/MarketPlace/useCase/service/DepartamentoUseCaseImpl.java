package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Departamento;
import com.challenges.MarketPlace.useCase.exceptions.*;
import com.challenges.MarketPlace.useCase.gateway.DepartamentoGateway;
import org.springframework.dao.DataIntegrityViolationException;
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
                    throw new ValidarNomeDepartamentoDuplicadoException(
                                (String.format("O nome informado ja consta como cadastrado." +
                                        " Devido a isso nao é permitido cadastrar dois nomes de departamento iguais.")));
                });
    }

    @Override
    public List<Departamento> buscarTodosDepartamentos(String nomeDepartamento) {
        List<Departamento> departamentos = departamentoGateway.buscarTodosDepartamentos(nomeDepartamento);

        if (departamentos.isEmpty()) {
            throw new ConteudoNaoEncontradoException("Não existe departamentos cadastrados");
        }
        return departamentos;
    }

    @Override
    public Departamento buscarDepartamentoPorId(Integer idDepartamento) {
        return departamentoGateway.buscarDepartamentoPorId(idDepartamento)
                .orElseThrow(() -> new DepartamentoInexistenteException(
                        String.
                                format("O Id do departamento informado náo consta como cadastrado em sistema." +
                                        " Favor verificar.")));
    }

    @Override
    public void excluirDepartamento(Integer idDepartamento) {
        var departamento = buscarDepartamentoPorId(idDepartamento);
       try {
           departamentoGateway.excluirDepartamentoPorId(departamento.getIdDepartamento());

       }catch(DataIntegrityViolationException dataIntegrityViolationException){
           throw new EntityException(
                   String.format("Departamento %s está em uso, não pode ser excluido.", idDepartamento));
       }
    }
}