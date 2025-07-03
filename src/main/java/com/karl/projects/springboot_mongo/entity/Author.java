package com.karl.projects.springboot_mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Author")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

	@Id
	private String mongoId;
	private String id;
	private String firstName;
	private String lastName;
}
