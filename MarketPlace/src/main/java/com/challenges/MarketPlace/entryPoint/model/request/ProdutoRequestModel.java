package com.challenges.MarketPlace.entryPoint.model.request;

import com.challenges.MarketPlace.entryPoint.utils.RemoveEspaçoString;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoRequestModel {

    @NotBlank
    @Size(max = 60)
    @JsonDeserialize(using = RemoveEspaçoString.class)
    private String nome;

    @NotBlank
    @Size(max = 256)
    @JsonDeserialize(using = RemoveEspaçoString.class)
    private String descricao;

    @NotBlank
    @Size(max = 256)
    @JsonDeserialize(using = RemoveEspaçoString.class)
    private String marca;

    @NotNull
    private Double preco;

    @NotEmpty
    private List<Integer> codigosDepartamento;

}
