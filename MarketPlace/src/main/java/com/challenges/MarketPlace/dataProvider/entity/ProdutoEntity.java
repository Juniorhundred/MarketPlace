package com.challenges.MarketPlace.dataProvider.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Products")
public class ProdutoEntity {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false)
    private Boolean ofertado;

    @Column(nullable = false)
    private Integer porcentagemOferta;

    @Column(nullable = false)
    private String dataCadastro;

    private String dataAtualizacao;

}
