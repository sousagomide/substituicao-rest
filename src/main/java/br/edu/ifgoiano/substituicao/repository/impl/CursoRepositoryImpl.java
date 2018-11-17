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

import br.edu.ifgoiano.substituicao.model.Curso;
import br.edu.ifgoiano.substituicao.repository.filter.CursoFilter;
import br.edu.ifgoiano.substituicao.repository.query.CursoRepositoryQuery;

public class CursoRepositoryImpl implements CursoRepositoryQuery {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Curso> listar(CursoFilter cursoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
		Root<Curso> root = criteria.from(Curso.class);
		Predicate[] predicates = criarRestricoes(cursoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Curso> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(cursoFilter));
	}

	private Long total(CursoFilter cursoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Curso> root = criteria.from(Curso.class);
		Predicate[] predicates = criarRestricoes(cursoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Curso> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(CursoFilter cursoFilter, CriteriaBuilder builder, Root<Curso> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(cursoFilter.getNome()))
			predicates.add(builder.like(builder.lower(root.get("instituicao")), cursoFilter.getNome() + "%"));
		if (!StringUtils.isEmpty(cursoFilter.getModalidade()))
			predicates.add(builder.like(builder.lower(root.get("modalidade")), cursoFilter.getModalidade().toString()));
		if (!StringUtils.isEmpty(cursoFilter.getIdCampus()))
			predicates.add(builder.like(builder.lower(root.get("idCampus")),
					cursoFilter.getIdCampus().getId().toString() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
