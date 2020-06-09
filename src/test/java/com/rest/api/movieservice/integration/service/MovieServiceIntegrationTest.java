package com.rest.api.movieservice.integration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

import java.util.Date;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.rest.api.movieservice.dto.BaseMovieDto;
import com.rest.api.movieservice.dto.MovieDto;
import com.rest.api.movieservice.model.Movie;

public class MovieServiceIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void test000_cleanDb() {
		movieDao.deleteAll();
	}

	@Test
	public void test001_shouldAddMovie() {
		final String path = "/movie";

		BaseMovieDto dto = new BaseMovieDto();
		dto.setSinopsis("sinopsis");
		dto.setTitle("title test");
		dto.setDate(new Date());
		dto.setDirectors(new String[] { "Director1", "Director2" });
		dto.setGenres(new String[] { "Thriller" });

		HttpEntity<BaseMovieDto> request = new HttpEntity<>(dto);
		final ResponseEntity<String> response = restTemplate.exchange(path, POST, request, String.class);
		assertEquals(OK, response.getStatusCode());

		Movie worker = movieDao.findMovieByTitle("title test");
		assertEquals(dto.getTitle(), worker.getTitle());
		assertEquals(dto.getDate(), worker.getDate());
		assertEquals(dto.getSinopsis(), worker.getSinopsis());
		assertEquals(dto.getDirectors()[0], worker.getDirectors()[0]);
		assertEquals(dto.getDirectors()[1], worker.getDirectors()[1]);
		assertEquals(dto.getGenres()[0], worker.getGenres()[0]);
	}

	@Test
	public void test002_shouldFindMovie() {
		Movie movie = Movie.builder()
			.sinopsis("sinopsis")
			.title("title test find")
			.date(new Date())
			.directors(new String[] { "Director1", "Director2" })
			.genres(new String[] { "Horror" })
			.build();
		movie = movieDao.save(movie);

		final String path = "/movie/{id}";
		final String id = movie.getId();
		final ResponseEntity<MovieDto> response = restTemplate.exchange(path, GET, EMPTY, MovieDto.class, id);
		assertEquals(OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(movie.getSinopsis(), response.getBody().getSinopsis());
		assertEquals(movie.getDate(), response.getBody().getDate());
		assertEquals(movie.getDirectors().length, response.getBody().getDirectors().length);
		assertEquals(movie.getDirectors()[0], response.getBody().getDirectors()[0]);
		assertEquals(movie.getDirectors()[1], response.getBody().getDirectors()[1]);
		assertEquals(movie.getGenres().length, response.getBody().getGenres().length);
		assertEquals(movie.getGenres()[0], response.getBody().getGenres()[0]);
	}

	@Test
	public void test003_shouldDeleteMovie() {
		Movie movie = Movie.builder()
			.sinopsis("sinopsis")
			.title("title test delete")
			.date(new Date())
			.directors(new String[] { "Director1" })
			.genres(new String[] { "Horror" })
			.build();
		movie = movieDao.save(movie);

		final String path = "/movie/{id}";
		final String id = movie.getId();
		final ResponseEntity<MovieDto> response = restTemplate.exchange(path, DELETE, EMPTY, MovieDto.class, id);
		assertEquals(OK, response.getStatusCode());

		movie = movieDao.findMovieByTitle("title test delete");
		assertNull(movie);
	}

}
