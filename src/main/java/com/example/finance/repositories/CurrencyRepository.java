package com.example.finance.repositories;

import com.example.finance.models.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Integer> {
    @Override
    Optional<CurrencyEntity> findById(Integer integer);

    @Query(nativeQuery = true, value = "SELECT * FROM currency ")
    Optional<List<CurrencyEntity>> getAll();
}
