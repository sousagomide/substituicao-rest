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

import br.edu.ifgoiano.substituicao.model.Disciplina;
import br.edu.ifgoiano.substituicao.repository.filter.DisciplinaFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public class DisciplinaRepositoryImpl implements RepositoryQuery<Disciplina, DisciplinaFilter> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Disciplina> listar(DisciplinaFilter disciplinaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Disciplina> criteria = builder.createQuery(Disciplina.class);
		Root<Disciplina> root = criteria.from(Disciplina.class);
		Predicate[] predicates = criarRestricoes(disciplinaFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Disciplina> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(disciplinaFilter));
	}

	private Long total(DisciplinaFilter disciplinaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Disciplina> root = criteria.from(Disciplina.class);
		Predicate[] predicates = criarRestricoes(disciplinaFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Disciplina> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(DisciplinaFilter disciplinaFilter, CriteriaBuilder builder,
			Root<Disciplina> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(disciplinaFilter.getNome()))
			predicates.add(builder.like(builder.lower(root.get("nome")), disciplinaFilter.getNome() + "%"));
		if (!StringUtils.isEmpty(disciplinaFilter.getCargaHoraria()))
			predicates.add(
					builder.like(builder.lower(root.get("cargaHoraria")), disciplinaFilter.getCargaHoraria() + "%"));
		if (!StringUtils.isEmpty(disciplinaFilter.getAtivo()))
			predicates.add(
					builder.like(builder.lower(root.get("ativo")), disciplinaFilter.getAtivo().toString()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
