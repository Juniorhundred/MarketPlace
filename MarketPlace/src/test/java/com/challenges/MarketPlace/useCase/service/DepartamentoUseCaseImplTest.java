package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Departamento;
import com.challenges.MarketPlace.useCase.gateway.DepartamentoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(SpringExtension.class)
class DepartamentoUseCaseImplTest {

    @InjectMocks
    private DepartamentoUseCaseImpl departamentoUseCase;

    @Mock
    private DepartamentoGateway departamentoGateway;


    @Test
    void criarDepartamento() {
        Departamento departamentoParaSerCriado = mockDepartamentoRequest();
        Departamento departamentoCriado = mockDepartamentoResponse();

        given(departamentoGateway.findbyNomeDepartamento(departamentoParaSerCriado
                .getNomeDepartamento())).willReturn(Optional.empty());
        given(departamentoGateway.criarDepartamento(departamentoParaSerCriado)).willReturn(departamentoCriado);

        Departamento departamento = departamentoUseCase.criarDepartamento(departamentoParaSerCriado);

        assertNotNull(departamento);
        assertAll(
                () -> assertEquals(1, departamento.getIdDepartamento()),
                () -> assertEquals("Informática", departamento.getNomeDepartamento()),
                () -> assertEquals("Departamento possui -> Computadores DeskTop, Micro-Computadores, Itens em geral de informática.", departamento.getDescricaoDepartamento())
        );
    }


    @Test
    void validarDuplicidadeNomeDepartamento() {

    }

    @Test
    void buscarTodosDepartamentos() {
        var departamentoCriado = mockDepartamentoResponses();

        given(departamentoGateway.buscarTodosDepartamentos("Eletrônicos"))
                .willReturn(departamentoCriado);
        given(departamentoGateway.buscarTodosDepartamentos("Residencial"))
                .willReturn(departamentoCriado);


        List<Departamento> departamentos = departamentoUseCase
                .buscarTodosDepartamentos("Eletrônicos");


        assertAll(

                () -> assertTrue(departamentos.size() == 2),
                () -> assertEquals(1, departamentos.get(0).getIdDepartamento()),
                () -> assertEquals("Eletrônicos", departamentos.get(0).getNomeDepartamento()),
                () -> assertEquals("Aparelhos em geral", departamentos.get(0).getDescricaoDepartamento()),

                () -> assertEquals(1, departamentos.get(1).getIdDepartamento()),
                () -> assertEquals("Residencial", departamentos.get(1).getNomeDepartamento()),
                () -> assertEquals("Artigos para sua casa", departamentos.get(1).getDescricaoDepartamento())
        );
    }

    @Test
    void buscarDepartamentoPorId() {
        var departamentoCriado = mockDepartamentoResponse();

        given(departamentoGateway.buscarDepartamentoPorId(1))
                .willReturn(Optional.of(departamentoCriado));

        Departamento departamento = departamentoUseCase
                .buscarDepartamentoPorId(1);

        assertNotNull(departamento);
        assertAll(
                () -> assertEquals(1, departamento.getIdDepartamento()),
                () -> assertEquals("Informática", departamento.getNomeDepartamento()),
                () -> assertEquals("Departamento possui -> Computadores DeskTop, Micro-Computadores, Itens em geral de informática.", departamento.getDescricaoDepartamento())
        );
    }

    @Test
    void excluirDepartamento() {

            var departamentoCriado = mockDepartamentoResponse();
            when(departamentoGateway.buscarDepartamentoPorId(departamentoCriado.getIdDepartamento()))
                    .thenReturn(Optional.of(departamentoCriado));

            departamentoUseCase.excluirDepartamento(departamentoCriado.getIdDepartamento());

            verify(departamentoGateway, times(1))
                    .excluirDepartamentoPorId(departamentoCriado.getIdDepartamento());
        }

    private Departamento mockDepartamentoRequest() {
        return Departamento.builder()
                .nomeDepartamento("Informática")
                .descricaoDepartamento("Departamento possui -> Computadores DeskTop, Micro-Computadores, Itens em geral de informática.")
                .build();
    }

    private Departamento mockDepartamentoResponse() {
        return Departamento.builder()
                .idDepartamento(1)
                .nomeDepartamento("Informática")
                .descricaoDepartamento("Departamento possui -> Computadores DeskTop, Micro-Computadores, Itens em geral de informática.")
                .build();

    }

    private List<Departamento> mockDepartamentoResponses() {
        return List.of(
                Departamento
                    .builder()
                        .idDepartamento(1)
                        .nomeDepartamento("Eletrônicos")
                        .descricaoDepartamento("Aparelhos em geral")
                .build(),
                Departamento
                    .builder()
                        .idDepartamento(1)
                        .nomeDepartamento("Residencial")
                        .descricaoDepartamento("Artigos para sua casa")
                        .build()
        );
    }

}