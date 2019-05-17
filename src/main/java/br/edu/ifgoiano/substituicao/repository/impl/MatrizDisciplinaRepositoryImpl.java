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

import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizDisciplinaFilter;
import br.edu.ifgoiano.substituicao.repository.query.MatrizDisciplinaRepositoryQuery;

public class MatrizDisciplinaRepositoryImpl implements MatrizDisciplinaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<MatrizDisciplina> listar(MatrizDisciplinaFilter matrizDisciplinaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<MatrizDisciplina> criteria = builder.createQuery(MatrizDisciplina.class);
		Root<MatrizDisciplina> root = criteria.from(MatrizDisciplina.class);
		Predicate[] predicates = criarRestricoes(matrizDisciplinaFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<MatrizDisciplina> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(matrizDisciplinaFilter));
	}

	private Long total(MatrizDisciplinaFilter matrizDisciplinaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<MatrizDisciplina> root = criteria.from(MatrizDisciplina.class);
		Predicate[] predicates = criarRestricoes(matrizDisciplinaFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<MatrizDisciplina> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(MatrizDisciplinaFilter matrizDisciplinaFilter, CriteriaBuilder builder, Root<MatrizDisciplina> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(matrizDisciplinaFilter.getDisciplina()))
			predicates.add(builder.like(builder.lower(root.get("disciplina")), matrizDisciplinaFilter.getDisciplina() + "%"));
		if (!StringUtils.isEmpty(matrizDisciplinaFilter.getMatriz()))
			predicates.add(builder.like(builder.lower(root.get("matriz")), matrizDisciplinaFilter.getMatriz().getId().toString() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
