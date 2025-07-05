package com.karl.projects.springboot_mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.karl.projects.springboot_mongo.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String>{

	public Optional<Author> findByFirstName(String firstName);
	@Query("{'id': ?0 }")
	public Optional<Author> findByIdField(String id);
	@Query(value = "{'id': ?0 }", delete = true)
	public Optional<Author> deleteByIdField(String id);
}
