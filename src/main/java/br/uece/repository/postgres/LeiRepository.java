package br.uece.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uece.entity.postgres.Lei;

public interface LeiRepository extends JpaRepository<Lei, Long> {
}
