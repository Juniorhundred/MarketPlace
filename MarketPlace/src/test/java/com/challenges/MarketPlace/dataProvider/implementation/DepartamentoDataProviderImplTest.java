package com.challenges.MarketPlace.dataProvider.implementation;

import com.challenges.MarketPlace.dataProvider.entity.DepartamentoEntity;
import com.challenges.MarketPlace.dataProvider.repository.DepartamentoRepository;
import com.challenges.MarketPlace.useCase.domain.Departamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class DepartamentoDataProviderImplTest {

    @InjectMocks
    private DepartamentoDataProviderImpl departamentoDataProvider;

    @Mock
    private DepartamentoRepository departamentoRepository;


    @Test
    void criarDepartamento() {
        DepartamentoEntity departamentoEntity = mockDepartamentoEntity();
        Departamento departamento = mockDepartamentoRequest();

        given(departamentoRepository.save(Mockito.any())).willReturn(departamentoEntity);

        Departamento departamentoDomain = departamentoDataProvider
                .criarDepartamento(departamento);

        assertNotNull(departamentoDomain);
        assertAll(
                () -> assertEquals(1L, departamentoDomain.getIdDepartamento()),
                () -> assertEquals("Eletrodomésticos", departamentoDomain.getNomeDepartamento()),
                () -> assertEquals("Geladeira, Tv, HomeTeather", departamentoDomain.getDescricaoDepartamento())
        );
    }


    @Test
    void findbyNomeDepartamento() {
        DepartamentoEntity departamentoRequest = mockDepartamentoEntity();

        given(departamentoRepository.findByNomeDepartamento(departamentoRequest.getNomeDepartamento()))
                .willReturn(Optional.of(departamentoRequest));

        Optional<Departamento> departamento = departamentoDataProvider.findbyNomeDepartamento(departamentoRequest.getNomeDepartamento());

        assertTrue(departamento.isPresent());
        assertAll(
                () -> assertEquals(1L, departamento.get().getIdDepartamento()),
                () -> assertEquals("Eletrodomésticos", departamento.get().getNomeDepartamento()),
                () -> assertEquals("Geladeira, Tv, HomeTeather", departamento.get().getDescricaoDepartamento())
        );
    }

    @Test
    void buscarTodosDepartamentos() {
    }

    @Test
    void buscarDepartamentoPorId() {
        Departamento departamentoRequest = mockDepartamentoRequest();
        DepartamentoEntity departamentoResponse = mockDepartamentoEntity();

        given(departamentoRepository.findById(departamentoRequest.getIdDepartamento()))
                .willReturn(Optional.of(departamentoResponse));

        Optional<Departamento> departamento = departamentoDataProvider
                .buscarDepartamentoPorId(departamentoRequest.getIdDepartamento());

        assertTrue(departamento.isPresent());
        assertAll(
                () -> assertEquals(1L, departamento.get().getIdDepartamento())
        );
    }

    @Test
    void excluirDepartamentoPorId() {

            var departamentoRequest = mockDepartamentoRequest();
            departamentoDataProvider.excluirDepartamentoPorId(departamentoRequest.getIdDepartamento());

            verify(departamentoRepository, times(1))
                    .deleteById(departamentoRequest.getIdDepartamento());
        }

    private DepartamentoEntity mockDepartamentoEntity() {
        return DepartamentoEntity.builder()
                .idDepartamento(1L)
                .nomeDepartamento("Eletrodomésticos")
                .descricaoDepartamento("Geladeira, Tv, HomeTeather")
                .build();
    }

    private Departamento mockDepartamentoRequest() {
        return Departamento.builder()
                .idDepartamento(1L)
                .nomeDepartamento("Eletrodomésticos")
                .descricaoDepartamento("Geladeira, Tv, HomeTeather")
                .build();
    }
}
