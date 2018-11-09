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

import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.repository.filter.CampusFilter;
import br.edu.ifgoiano.substituicao.repository.query.CampusRepositoryQuery;

public class CampusRepositoryImpl implements CampusRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Campus> listar(CampusFilter campusFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Campus> criteria = builder.createQuery(Campus.class);
		Root<Campus> root = criteria.from(Campus.class);
		Predicate[] predicates = criarRestricoes(campusFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Campus> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(campusFilter));
	}

	private Long total(CampusFilter campusFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Campus> root = criteria.from(Campus.class);
		Predicate[] predicates = criarRestricoes(campusFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Campus> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(CampusFilter campusFilter, CriteriaBuilder builder, Root<Campus> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(campusFilter.getInstituicao()))
			predicates.add(builder.like(builder.lower(root.get("instituicao")), campusFilter.getInstituicao() + "%"));
		if(!StringUtils.isEmpty(campusFilter.getNome()))
			predicates.add(builder.like(builder.lower(root.get("nome")), campusFilter.getNome() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}