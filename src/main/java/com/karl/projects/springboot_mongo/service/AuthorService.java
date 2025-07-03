package com.karl.projects.springboot_mongo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karl.projects.springboot_mongo.entity.Author;
import com.karl.projects.springboot_mongo.exception.AuthorNotFoundException;
import com.karl.projects.springboot_mongo.repository.AuthorRepository;

@Service
public class AuthorService {
	
	private  final AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}
	
	public List<Author> findAll(){
		return authorRepository.findAll();
	}
	
	public Author findByFirstName(String firstName) {
		return authorRepository.findByFirstName(firstName).orElseThrow(() -> new AuthorNotFoundException("Author not found!"));
	}
	
	public Author findByIdField(String id) {
		return authorRepository.findByIdField(id).orElseThrow(() -> new AuthorNotFoundException("Author not found!"));
	}
	

}
