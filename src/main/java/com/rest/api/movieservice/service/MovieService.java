package com.rest.api.movieservice.service;

import com.rest.api.movieservice.dto.BaseMovieDto;
import com.rest.api.movieservice.model.Movie;

public interface MovieService {
	
	public void addMovie(BaseMovieDto movieDto);
	
	public void deleteMovie(String movieId);
	
	public Movie findMovie(String movieId) throws Exception;
}
