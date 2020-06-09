package com.rest.api.movieservice.unitTest.service;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.movieservice.dao.MovieDao;
import com.rest.api.movieservice.dto.BaseMovieDto;
import com.rest.api.movieservice.model.Movie;
import com.rest.api.movieservice.service.impl.MovieServiceImpl;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieServiceTest {

	@Mock
	private MovieDao movieDao;
	@Mock
	private ModelMapper mapper;
	@InjectMocks
	private MovieServiceImpl movieService;

	@Test
	public void test001_shouldAddMovie() {
		BaseMovieDto movieDto = new BaseMovieDto();
		movieDto.setTitle("title");

		Movie movie = Movie.builder().title("title").build();

		when(mapper.map(any(), any())).thenReturn(movie);
		movieService.addMovie(movieDto);

		verify(movieDao).save(any(Movie.class));

	}
	
	@Test
	public void test002_shouldFindMovie() throws Exception {
		Movie movie = Movie.builder().title("title").build();
		when(movieDao.findById("1")).thenReturn(of(movie));
		
		movieService.findMovie("1");

		verify(movieDao).findById("1");
	}
	
	@Test
	public void test003_shouldDeleteMovie() throws Exception {
		movieService.deleteMovie("1");
		verify(movieDao).deleteById("1");
	}

}
