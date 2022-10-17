package com.challenges.MarketPlace.entryPoint.model.request;

import com.challenges.MarketPlace.entryPoint.utils.RemoveEspaçoString;
import com.challenges.MarketPlace.entryPoint.validation.Constraints.TextoEmBrancoValidation;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoUptadeModelRequest {

    @JsonDeserialize(using = RemoveEspaçoString.class)
    @Size(max = 60)
    @TextoEmBrancoValidation
    private String nome;

    @JsonDeserialize(using = RemoveEspaçoString.class)
    @Size(max = 256)
    @TextoEmBrancoValidation
    private String descricao;

    @JsonDeserialize(using = RemoveEspaçoString.class)
    @Size(max = 40)
    @TextoEmBrancoValidation
    private String marca;

    @Positive
    private Double preco;

    private List<Integer> codigosDepartamento;

}
