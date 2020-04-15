package com.rest.api.movieservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.movieservice.dao.MovieDao;
import com.rest.api.movieservice.dto.BaseMovieDto;
import com.rest.api.movieservice.model.Movie;
import com.rest.api.movieservice.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public void deleteMovie(String movieId) {
		movieDao.deleteById(movieId);
	}

	@Override
	public Movie findMovie(String movieId) throws Exception {
		return movieDao.findById(movieId).orElseThrow(Exception::new);
	}

	@Override
	public void addMovie(BaseMovieDto movieDto) {
		Movie movie = mapper.map(movieDto, Movie.class);
		movieDao.save(movie);
	}
}
