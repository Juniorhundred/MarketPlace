package com.challenges.MarketPlace.dataProvider.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Departamentos")
public class DepartamentoEntity {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idDepartamento;

    @Column(nullable = false)
    private String nomeDepartamento;

    @Column(nullable = false)
    private String descricaoDepartamento;

}
