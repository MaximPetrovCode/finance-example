package com.example.finance.repositories;

import com.example.finance.models.entities.DataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM data WHERE nums = :nums AND currency_code = :currencyCode AND timestamp = :timestamp")
    Optional<List<DataEntity>> findData(Integer nums, Integer currencyCode, Long timestamp);

    @Query(nativeQuery = true, value = "SELECT * FROM data ")
    Optional<List<DataEntity>> getAll();
}
