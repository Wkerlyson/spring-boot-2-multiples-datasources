package br.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.example.entity.postgres.Law;
import br.example.entity.sqlserver.Student;
import br.example.service.ExampleService;

@RestController
public class ExampleResource {

	@Autowired
	private ExampleService service;

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> findStudentById(@PathVariable Long id) {

		Student obj = service.findStudentById(id);
		return ResponseEntity.ok().body(obj);

	}

	@GetMapping("/leis/{id}")
	public ResponseEntity<Law> findLawById(@PathVariable Long id) {

		Law obj = service.findLawById(id);
		return ResponseEntity.ok().body(obj);

	}
}
