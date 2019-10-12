package br.example.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import br.example.entity.postgres.Law;

public interface LawRepository extends JpaRepository<Law, Long> {
}
