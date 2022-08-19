package com.challenges.MarketPlace.dataProvider.implementation;

import com.challenges.MarketPlace.dataProvider.ExceptionsDataProvider.ValidarCadastroException;
import com.challenges.MarketPlace.dataProvider.entity.DepartamentoEntity;
import com.challenges.MarketPlace.dataProvider.mapper.request.DepartamentoRequestMapper;
import com.challenges.MarketPlace.dataProvider.mapper.response.DepartamentoResponseMapper;
import com.challenges.MarketPlace.dataProvider.repository.DepartamentoRepository;
import com.challenges.MarketPlace.useCase.domain.Departamento;
import com.challenges.MarketPlace.useCase.gateway.DepartamentoGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartamentoDataProviderImpl implements DepartamentoGateway {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoDataProviderImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Departamento criarDepartamento(Departamento departamento) {
        try {
            DepartamentoEntity departamentoAtual = DepartamentoRequestMapper.converterDomainParaEntity(departamento);
            DepartamentoEntity departamentoCriado = departamentoRepository.save(departamentoAtual);

            return DepartamentoResponseMapper.converterEntityParaDomain(departamentoCriado);
        }catch (Exception exception){
            throw new ValidarCadastroException("Erro ao realizar o cadastro do departamento", exception);
        }
    }

    @Override
    public Optional<Departamento> findbyNomeDepartamento(String nomeDepartamento) {
        return departamentoRepository.findByNomeDepartamento(nomeDepartamento)
                .map(DepartamentoResponseMapper::converterEntityParaDomain);
    }
    @Override
    public List<Departamento> buscarTodosDepartamentos(String nomeDepartamento) {
        List<DepartamentoEntity> departamentos = departamentoRepository.buscarNomeDepartamentoFiltro(nomeDepartamento);

        return DepartamentoResponseMapper.converterEntitiesParaDomain(departamentos);
    }
    @Override
    public Optional<Departamento> buscarDepartamentoPorId(Long idDepartamento) {
        return departamentoRepository.findById(idDepartamento)
                .map(DepartamentoResponseMapper::converterEntityParaDomain);
    }

    @Override
    public void excluirDepartamentoPorId(Long idDepartamento) {

        departamentoRepository.deleteById(idDepartamento);
    }

}


