package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long>{}
