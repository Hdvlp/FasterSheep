package com.fastersheep.fastersheep.service;


import com.fastersheep.fastersheep.model.ExampleEntity;
import com.fastersheep.fastersheep.repo.ExampleRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import com.fastersheep.fastersheep.model.Currencies;

@Service
public class ExampleService {

    private final ExampleRepository repository;

    public ExampleService(ExampleRepository repository) {
        this.repository = repository;
    }

    public ExampleEntity saveEntity(Currencies currencies, BigDecimal price) {
        LocalDateTime currentTimestamp = LocalDateTime.now(ZoneOffset.UTC); // Current UTC timestamp
        
        ExampleEntity entity = new ExampleEntity(currencies, price, currentTimestamp);
        return repository.save(entity);
    }

    public List<ExampleEntity> getEntitySortedByTimestampPartF(Currencies currencies) {
        List<ExampleEntity> page = repository.findEntitiesByCurrenciesColumnOrderByTimestampDesc(
                Currencies.getNumberFromString(currencies.name())
                );    
        return page;
    }

    public Page<ExampleEntity> getEntitySortedByTimestampPartG(Currencies currencies) {
         Page<ExampleEntity> page = repository
         .findEntitiesByCurrenciesColumnOrderByTimestampDescPartG(
             Currencies.getNumberFromString(currencies.name()),
             PageRequest.of(0, 20)
             );
  
        return page;
    }
}
