package br.uece.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.uece.entity.postgres.Lei;
import br.uece.entity.sqlserver.Aluno;
import br.uece.service.ExemploService;

@RestController
public class ExemploResource {

	@Autowired
	private ExemploService service;

	@GetMapping("/alunos/{id}")
	public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {

		Aluno obj = service.buscarAlunoPorId(id);
		return ResponseEntity.ok().body(obj);

	}

	@GetMapping("/leis/{id}")
	public ResponseEntity<Lei> buscarLeiPorId(@PathVariable Long id) {

		Lei obj = service.buscarLeiPorId(id);
		return ResponseEntity.ok().body(obj);

	}
}
