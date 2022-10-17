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

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class OfertaProdutoRemovidaModelRequest {

    @JsonDeserialize(using = RemoveEspaçoString.class)
    @NotBlank
    private String id;
}
