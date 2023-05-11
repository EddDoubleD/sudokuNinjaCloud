package com.edddoubled.microservice.data.repository;

import com.edddoubled.microservice.data.model.SolutionMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SolutionMetadataRepository extends MongoRepository<SolutionMetadata, String> {
}
