package com.challenges.MarketPlace.dataProvider.repository;

import com.challenges.MarketPlace.dataProvider.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {

    Optional<ProdutoEntity> findByNome (String nome);

}
