package br.uece.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.uece.Application;
import br.uece.service.ExemploService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("development")
public class RepositoriesTest {
	
	
	@Autowired
	private ExemploService service;
	
	
	@Test
	public void salvarEmDoisBancos() {
		try {
			service.salvarEmDoisBancos();
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
