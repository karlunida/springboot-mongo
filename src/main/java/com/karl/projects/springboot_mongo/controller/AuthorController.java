package com.karl.projects.springboot_mongo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.karl.projects.springboot_mongo.entity.Author;
import com.karl.projects.springboot_mongo.service.AuthorService;

@RestController
public class AuthorController {
	
	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		super();
		this.authorService = authorService;
	}
	
	@GetMapping
	public ResponseEntity<List<Author>> findAll(){
		return ResponseEntity.ok(authorService.findAll());
	}
	
	@GetMapping("/author/firstname/{firstName}")
	public ResponseEntity<Author> findByFirstName(@PathVariable String firstName){
		return ResponseEntity.ok(authorService.findByFirstName(firstName));
	}

	@GetMapping("/author/{id}")
	public ResponseEntity<Author> findByIdField(@PathVariable String id){
		return ResponseEntity.ok(authorService.findByIdField(id));
	}

}
