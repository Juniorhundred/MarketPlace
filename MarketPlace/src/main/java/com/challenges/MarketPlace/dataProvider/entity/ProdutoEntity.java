package com.challenges.MarketPlace.dataProvider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Produtos")
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "produto_departamento",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private List<DepartamentoEntity> departamentos = new ArrayList<>();
}
