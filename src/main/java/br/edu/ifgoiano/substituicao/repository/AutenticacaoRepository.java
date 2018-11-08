package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Autenticacao;

public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Long> {}
