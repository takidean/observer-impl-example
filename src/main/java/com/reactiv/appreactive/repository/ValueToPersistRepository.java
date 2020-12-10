package com.reactiv.appreactive.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.reactiv.appreactive.model.ValueToPersist;

public interface ValueToPersistRepository extends MongoRepository<ValueToPersist, String> {
}
