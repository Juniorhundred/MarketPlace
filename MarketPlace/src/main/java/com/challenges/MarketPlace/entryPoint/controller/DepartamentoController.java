package com.challenges.MarketPlace.entryPoint.controller;

import com.challenges.MarketPlace.entryPoint.mapper.request.DepartamentoEntrypointMapperRequest;
import com.challenges.MarketPlace.entryPoint.mapper.response.DepartamentoEntrypointMapperResponse;
import com.challenges.MarketPlace.entryPoint.model.request.DepartamentoModelRequest;
import com.challenges.MarketPlace.entryPoint.model.response.DepartamentoModelResponse;
import com.challenges.MarketPlace.useCase.domain.Departamento;
import com.challenges.MarketPlace.useCase.service.DepartamentoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/challengebrq/v1/departamentos")
public class DepartamentoController {

    private final DepartamentoUseCase departamentoUseCase;

    @PostMapping
    public ResponseEntity<DepartamentoModelResponse> cadastrarDepartamento(@RequestBody DepartamentoModelRequest departamentoModelRequest) {

        Departamento departamentoRequestDomain = DepartamentoEntrypointMapperRequest
                .converterEntrypointParaDomain(departamentoModelRequest);
        Departamento departamentoResponseDomain = departamentoUseCase.
                criarDepartamento(departamentoRequestDomain);

        DepartamentoModelResponse departamentoModelResponse = DepartamentoEntrypointMapperResponse
                .converterDomainParaEntrypoint(departamentoResponseDomain);

        return new ResponseEntity<>(departamentoModelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoModelResponse>> buscarDepartamentos(@RequestParam(value="nomeDepartamento", required=false) String nomeDepartamento) {

        List<Departamento> departamentosResponseDomain = departamentoUseCase.buscarTodosDepartamentos(nomeDepartamento);

        List<DepartamentoModelResponse> departamentoModelResponse = DepartamentoEntrypointMapperResponse.converterDomainsParaEntrypoint(departamentosResponseDomain);

        if (departamentoModelResponse.isEmpty()) {
            ResponseEntity.noContent().build();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(departamentoModelResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idDepartamento}")
    public ResponseEntity<?> removerDepartamentoPorId(@PathVariable Long idDepartamento) {
        departamentoUseCase.excluirDepartamento(idDepartamento);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
