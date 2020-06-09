package com.rest.api.movieservice.integration.service;

import java.util.Date;

import org.junit.Test;

import com.rest.api.movieservice.model.Movie;

public class MovieServiceIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void test001_shouldAddMovie() {
		movieDao.save(createMovieTest());
	}

	private Movie createMovieTest() {
		Movie movie = Movie.builder().date(new Date()).sinopsis("sinopsis").title("title").build();
		return movie;
	}
}
