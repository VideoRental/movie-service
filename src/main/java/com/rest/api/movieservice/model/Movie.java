package com.rest.api.movieservice.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Movie {
	
	private String id;
	
	private String title;
	
	private String[] genres;
	
	private Date date;
	
	private String sinopsis;
	
	private String[] directors;
	
}
