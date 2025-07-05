package com.karl.projects.springboot_mongo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorAlreadyExists extends RuntimeException{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

}
