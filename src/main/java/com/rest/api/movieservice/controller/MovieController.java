package com.rest.api.movieservice.controller;

import static org.slf4j.LoggerFactory.getLogger;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.movieservice.dto.BaseMovieDto;
import com.rest.api.movieservice.dto.MovieDto;
import com.rest.api.movieservice.service.MovieService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final Logger LOG = getLogger(MovieController.class);
	
	@Autowired
	private MovieService movieService;	
	@Autowired
	private ModelMapper mapper;

	@ApiOperation(value = "Add new movie")
	@PostMapping
	public ResponseEntity<HttpStatus> addMovie(@RequestBody @Valid BaseMovieDto movieDto) {
		LOG.debug("MovieController - addMovie: {}", movieDto.toString());
		movieService.addMovie(movieDto);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete movie")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String movieId) throws Exception {
		LOG.debug("MovieController - deleteUser: {}", movieId);
		movieService.deleteMovie(movieId);
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Find movie")
	@GetMapping("/{id}")
	public ResponseEntity<MovieDto> findMovie(@PathVariable("id") String movieId) throws Exception {
		LOG.debug("MovieController - findMovie: {}", movieId);
		MovieDto dto = mapper.map(movieService.findMovie(movieId), MovieDto.class);
		return ResponseEntity.ok(dto);
	}
}
