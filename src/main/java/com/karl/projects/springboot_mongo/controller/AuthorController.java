package com.karl.projects.springboot_mongo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.karl.projects.springboot_mongo.dto.AuthorDTO;
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
	
	@PostMapping("/author")
	public ResponseEntity<Author> save(@RequestBody AuthorDTO authorDto){
		Author author = authorService.save(authorDto);
		String hostUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
		String location = hostUrl.concat("/author/").concat(author.getId());
		try {
			return ResponseEntity.created(new URI(location)).body(author);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/author")
	public ResponseEntity<Author> put(@RequestBody AuthorDTO authorDTO){
		Pair<Author, HttpStatus> res = authorService.put(authorDTO);
		if(res.getSecond().equals(HttpStatus.CREATED)) {
			String hostUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
			String location = hostUrl.concat("/author/").concat(res.getFirst().getId());
			try {
				return ResponseEntity.created(new URI(location)).body(res.getFirst());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.status(res.getSecond()).build();
	}
	
	@DeleteMapping("/author/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		authorService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
