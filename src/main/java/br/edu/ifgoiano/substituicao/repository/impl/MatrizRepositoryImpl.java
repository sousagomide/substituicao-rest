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

import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public class MatrizRepositoryImpl implements RepositoryQuery<Matriz, MatrizFilter> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Matriz> listar(MatrizFilter matrizFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Matriz> criteria = builder.createQuery(Matriz.class);
		Root<Matriz> root = criteria.from(Matriz.class);
		Predicate[] predicates = criarRestricoes(matrizFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Matriz> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(matrizFilter));
	}

	private Long total(MatrizFilter matrizFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Matriz> root = criteria.from(Matriz.class);
		Predicate[] predicates = criarRestricoes(matrizFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Matriz> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(MatrizFilter matrizFilter, CriteriaBuilder builder, Root<Matriz> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(matrizFilter.getAno()))
			predicates.add(builder.like(builder.lower(root.get("ano")), matrizFilter.getAno() + "%"));
		if (!StringUtils.isEmpty(matrizFilter.getAtivo()))
			predicates.add(builder.like(builder.lower(root.get("ativo")), matrizFilter.getAtivo().toString()));
		if (!StringUtils.isEmpty(matrizFilter.getCurso()))
			predicates.add(
					builder.like(builder.lower(root.get("curso")), matrizFilter.getCurso().getId().toString() + "%"));
		if (!StringUtils.isEmpty(matrizFilter.getTurma()))
			predicates.add(
					builder.like(builder.lower(root.get("turma")), matrizFilter.getTurma().getId().toString() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
