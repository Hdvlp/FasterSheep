package com.fastersheep.fastersheep.repo;

import com.fastersheep.fastersheep.model.ExampleEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

    Page<ExampleEntity> findAllByOrderByIdDesc(Pageable pageable);

    @Query("SELECT e FROM ExampleEntity e WHERE e.currencies = :currencies ORDER BY e.timestamp DESC")
    List<ExampleEntity> findEntitiesByCurrenciesColumnOrderByTimestampDesc(
        @Param("currencies") long currencies);

    @Query("SELECT e FROM ExampleEntity e WHERE e.currencies = :currencies ORDER BY e.timestamp DESC")
    Page<ExampleEntity> findEntitiesByCurrenciesColumnOrderByTimestampDescPartG(
        @Param("currencies") long currencies,
        Pageable pageable);

}


