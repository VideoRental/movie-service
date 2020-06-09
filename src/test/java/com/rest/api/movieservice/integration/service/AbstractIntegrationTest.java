package com.rest.api.movieservice.integration.service;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.movieservice.dao.MovieDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

	@Autowired
	protected MovieDao movieDao;

	@Autowired
	protected ModelMapper mapper;

}
