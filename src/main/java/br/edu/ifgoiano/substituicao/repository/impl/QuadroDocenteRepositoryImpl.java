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

import br.edu.ifgoiano.substituicao.model.QuadroDocente;
import br.edu.ifgoiano.substituicao.repository.filter.QuadroDocenteFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public class QuadroDocenteRepositoryImpl implements RepositoryQuery<QuadroDocente, QuadroDocenteFilter> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<QuadroDocente> listar(QuadroDocenteFilter quadroDocenteFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<QuadroDocente> criteria = builder.createQuery(QuadroDocente.class);
		Root<QuadroDocente> root = criteria.from(QuadroDocente.class);
		Predicate[] predicates = criarRestricoes(quadroDocenteFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<QuadroDocente> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(quadroDocenteFilter));
	}

	private Long total(QuadroDocenteFilter quadroDocenteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<QuadroDocente> root = criteria.from(QuadroDocente.class);
		Predicate[] predicates = criarRestricoes(quadroDocenteFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<QuadroDocente> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(QuadroDocenteFilter quadroDocenteFilter, CriteriaBuilder builder, Root<QuadroDocente> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(quadroDocenteFilter.getMatrizDisciplina()))
			predicates.add(
					builder.like(builder.lower(root.get("matrizDisciplina")), quadroDocenteFilter.getMatrizDisciplina().getId().toString() + "%"));
		if (!StringUtils.isEmpty(quadroDocenteFilter.getServidor()))
			predicates.add(
					builder.like(builder.lower(root.get("servidor")), quadroDocenteFilter.getServidor().getId().toString() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
