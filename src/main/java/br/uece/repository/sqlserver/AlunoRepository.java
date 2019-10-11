package br.uece.repository.sqlserver;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uece.entity.sqlserver.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {


}
