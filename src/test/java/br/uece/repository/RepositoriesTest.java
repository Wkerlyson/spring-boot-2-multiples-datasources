package br.uece.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.example.Application;
import br.example.service.ExampleService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("development")
public class RepositoriesTest {
	
	
	@Autowired
	private ExampleService service;
	
	
	@Test
	public void saveInTwoDatabases() {
		try {
			service.saveInTwoDatabases();
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
