package br.edu.ifgoiano.substituicao.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.edu.ifgoiano.substituicao.model.Turma;
import br.edu.ifgoiano.substituicao.repository.filter.TurmaFilter;
import br.edu.ifgoiano.substituicao.repository.query.TurmaRepositoryQuery;

public class TurmaRepositoryImpl implements TurmaRepositoryQuery {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Turma> listar(TurmaFilter turmaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Turma> criteria = builder.createQuery(Turma.class);
		Root<Turma> root = criteria.from(Turma.class);
		Predicate[] predicates = criarRestricoes(turmaFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Turma> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(turmaFilter));
	}

	private Long total(TurmaFilter turmaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Turma> root = criteria.from(Turma.class);
		Predicate[] predicates = criarRestricoes(turmaFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Turma> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(TurmaFilter turmaFilter, CriteriaBuilder builder, Root<Turma> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(turmaFilter.getNome()))
			predicates.add(builder.like(builder.lower(root.get("nome")), turmaFilter.getNome() + "%"));
		if (!StringUtils.isEmpty(turmaFilter.getCurso()))
			predicates.add(builder.like(builder.lower(root.get("curso")), turmaFilter.getCurso() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
