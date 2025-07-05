package com.karl.projects.springboot_mongo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karl.projects.springboot_mongo.dto.AuthorDTO;
import com.karl.projects.springboot_mongo.entity.Author;
import com.karl.projects.springboot_mongo.exception.AuthorAlreadyExists;
import com.karl.projects.springboot_mongo.exception.AuthorNotFoundException;
import com.karl.projects.springboot_mongo.repository.AuthorRepository;

@Service
public class AuthorService {
	
	private  final AuthorRepository authorRepository;
	private final ModelMapper modelMapper;

	public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
		super();
		this.authorRepository = authorRepository;
		this.modelMapper = modelMapper;
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
	
	public Author save(AuthorDTO authorDto) {
		authorRepository.findByIdField(authorDto.getId()).ifPresent(t ->  {
			throw new AuthorAlreadyExists("Author already exists!");
		});
		Author author = modelMapper.map(authorDto, Author.class);
		return authorRepository.save(author);
	}
	
	public Pair<Author, HttpStatus> put(AuthorDTO authorDto) {
		return authorRepository.findByIdField(authorDto.getId()).map(t -> {
			modelMapper.map(authorDto, t);
			return Pair.of(authorRepository.save(t), HttpStatus.NO_CONTENT);
		}).orElseGet(() -> {
			return Pair.of(authorRepository.save(modelMapper.map(authorDto, Author.class)), HttpStatus.CREATED);
		});
	}
	
	@Transactional
	public void delete(String authorId) {
		authorRepository.deleteByIdField(authorId);
	}

}
