package com.challenges.MarketPlace.entryPoint.mapper.response;

import com.challenges.MarketPlace.entryPoint.model.response.DepartamentoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Departamento;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoEntrypointMapperResponseTest {

    @Test
    void converterDomainParaEntrypoint() {
            Departamento mockDepartamento = mockDepartamentoResponse();

            DepartamentoModelResponse departamentoModelResponse = DepartamentoEntrypointMapperResponse
                    .converterDomainParaEntrypoint(mockDepartamento);

            assertNotNull(departamentoModelResponse);
            assertAll(
                    () -> assertEquals(1L,departamentoModelResponse.getIdDepartamento()),
                    () -> assertEquals("Informática",departamentoModelResponse.getNomeDepartamento()),
                    () -> assertEquals("Itens em geral de informática.", departamentoModelResponse.getDescricaoDepartamento())
            );
        }

    @Test
    void converterDomainsParaEntrypoint() {
            var mockDepartamento = mockDepartamentoResponse();

            List<DepartamentoModelResponse> departamentosModelResponse = DepartamentoEntrypointMapperResponse
                    .converterDomainsParaEntrypoint(List.of(mockDepartamento));

            assertNotNull(departamentosModelResponse);
            assertAll(
                    () -> assertEquals(1L,departamentosModelResponse.get(0).getIdDepartamento()),
                    () -> assertEquals("Informática",departamentosModelResponse.get(0).getNomeDepartamento()),
                    () -> assertEquals("Itens em geral de informática.", departamentosModelResponse.get(0).getDescricaoDepartamento())
            );
        }

    private Departamento mockDepartamentoResponse() {
        return Departamento.builder()
                .idDepartamento(1L)
                .nomeDepartamento("Informática")
                .descricaoDepartamento("Itens em geral de informática.")
                .build();

    }
}