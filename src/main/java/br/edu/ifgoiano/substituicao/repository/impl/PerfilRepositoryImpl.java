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

import br.edu.ifgoiano.substituicao.model.Perfil;
import br.edu.ifgoiano.substituicao.repository.filter.PerfilFilter;
import br.edu.ifgoiano.substituicao.repository.query.PerfilRepositoryQuery;

public class PerfilRepositoryImpl implements PerfilRepositoryQuery {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Perfil> listar(PerfilFilter perfilFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Perfil> criteria = builder.createQuery(Perfil.class);
		Root<Perfil> root = criteria.from(Perfil.class);
		Predicate[] predicates = criarRestricoes(perfilFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Perfil> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(perfilFilter));
	}

	private Long total(PerfilFilter perfilFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Perfil> root = criteria.from(Perfil.class);
		Predicate[] predicates = criarRestricoes(perfilFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Perfil> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(PerfilFilter perfilFilter, CriteriaBuilder builder,
			Root<Perfil> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(perfilFilter.getDescricao()))
			predicates.add(builder.like(builder.lower(root.get("descricao")), perfilFilter.getDescricao() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
