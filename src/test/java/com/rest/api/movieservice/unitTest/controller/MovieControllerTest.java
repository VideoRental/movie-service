package com.rest.api.movieservice.unitTest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.rest.api.movieservice.dto.MovieDto;
import com.rest.api.movieservice.model.Movie;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieControllerTest extends AbstractControllerTest {

	@Test
	public void test001_shouldReturnFoundMovie() throws Exception {
		// given
		Movie movie = Movie.builder().title("Movie1").build();
		MovieDto dto = new MovieDto();
		dto.setId("1");
		dto.setTitle("Movie1");

		// when
		when(movieService.findMovie("1")).thenReturn(movie);
		when(mapper.map(any(), any())).thenReturn(dto);

		// then
		mockMvc.perform(get("/movie/1").accept(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.title", is("Movie1")))
			.andExpect(jsonPath("$.id", is("1")));
	}

	@Test
	public void test002_shouldAddMovie_badRequest() throws Exception {
		// given
		String commentBody = "{\"sinopsis\": \"Sinopsis\", \"title\": \"Movie1\"}";

		// then
		mockMvc.perform(post("/movie").content(commentBody).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void test003_shouldAddMovie() throws Exception {
		// given
		String commentBody = "{\"sinopsis\": \"Sinopsis\", \"title\": \"Movie1\", \"genres\": [\"comedy\", \"thriller\"], \"date\": \"2014-01-01T23:28:56.782Z\", \"directors\": [\"Allan\", \"Fred\"]}";

		// then
		mockMvc.perform(post("/movie").content(commentBody).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void test004_shouldDeleteMovie() throws Exception {
		mockMvc.perform(
				delete("/movie/1").contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
			.andExpect(status().isOk());
	}

}
