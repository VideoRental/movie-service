package com.rest.api.movieservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Movie {
	
	private String id;
	
	private String title;
	
	private String[] genres;
	
	private int date;
	
	private String sinopsis;
	
	private String[] directors;
	
}
