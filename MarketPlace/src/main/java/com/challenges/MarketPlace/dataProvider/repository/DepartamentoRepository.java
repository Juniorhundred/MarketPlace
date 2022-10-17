package com.challenges.MarketPlace.dataProvider.repository;

import com.challenges.MarketPlace.dataProvider.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {

    Optional<DepartamentoEntity> findByNomeDepartamento(String nomeDepartamento);

    @Query("FROM DepartamentoEntity WHERE (:nomeDepartamento is null or nomeDepartamento like %:nomeDepartamento%)")
    List<DepartamentoEntity> buscarNomeDepartamentoFiltro(@Param("nomeDepartamento") String nomeDepartamento);
}
