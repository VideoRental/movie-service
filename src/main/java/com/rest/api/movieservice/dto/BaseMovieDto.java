package com.rest.api.movieservice.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BaseMovieDto {

	@NotNull
	private String title;
	@NotNull
	private String[] genres;
	@NotNull
	private int date;
	@NotNull
	private String sinopsis;
	@NotNull
	private String[] directors;
}
