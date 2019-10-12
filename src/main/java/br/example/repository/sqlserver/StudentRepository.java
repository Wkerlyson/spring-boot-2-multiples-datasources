package br.example.repository.sqlserver;

import org.springframework.data.jpa.repository.JpaRepository;

import br.example.entity.sqlserver.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {


}
