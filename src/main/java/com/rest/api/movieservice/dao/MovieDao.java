package com.rest.api.movieservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rest.api.movieservice.model.Movie;

public interface MovieDao extends PagingAndSortingRepository<Movie, String> {

	public Movie findMovieByTitle(String title);
}
