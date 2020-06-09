package com.rest.api.movieservice.integration.service;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.movieservice.dao.MovieDao;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(NAME_ASCENDING)
public abstract class AbstractIntegrationTest {

	@Autowired
	protected MovieDao movieDao;

	@Autowired
	protected ModelMapper mapper;
	
	@Autowired
	protected TestRestTemplate restTemplate;

}
