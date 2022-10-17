package com.challenges.MarketPlace.entryPoint.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemErrorModelResponse {

    private String codigo;
    private String mensagem;
    private String urlErro;
    private List<CampoMensagemErrorModelResponse> campos;


}
