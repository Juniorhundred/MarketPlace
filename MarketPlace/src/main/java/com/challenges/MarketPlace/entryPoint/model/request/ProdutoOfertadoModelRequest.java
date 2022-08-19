package com.challenges.MarketPlace.entryPoint.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProdutoOfertadoModelRequest {

    private String id;
    private Integer porcentagemOferta;
}
