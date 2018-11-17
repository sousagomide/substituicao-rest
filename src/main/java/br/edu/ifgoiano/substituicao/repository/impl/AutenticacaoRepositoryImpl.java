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

import br.edu.ifgoiano.substituicao.model.Autenticacao;
import br.edu.ifgoiano.substituicao.repository.filter.AutenticacaoFilter;
import br.edu.ifgoiano.substituicao.repository.query.AutenticacaoRepositoryQuery;

public class AutenticacaoRepositoryImpl implements AutenticacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Autenticacao> listar(AutenticacaoFilter autenticacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Autenticacao> criteria = builder.createQuery(Autenticacao.class);
		Root<Autenticacao> root = criteria.from(Autenticacao.class);
		Predicate[] predicates = criarRestricoes(autenticacaoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Autenticacao> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(autenticacaoFilter));
	}

	private Long total(AutenticacaoFilter autenticacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Autenticacao> root = criteria.from(Autenticacao.class);
		Predicate[] predicates = criarRestricoes(autenticacaoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Autenticacao> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(AutenticacaoFilter autenticacaoFilter, CriteriaBuilder builder,
			Root<Autenticacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(autenticacaoFilter.getUsuario()))
			predicates.add(builder.like(builder.lower(root.get("usuario")), autenticacaoFilter.getUsuario() + "%"));
		if (!StringUtils.isEmpty(autenticacaoFilter.getSenha()))
			predicates.add(builder.like(builder.lower(root.get("senha")), autenticacaoFilter.getSenha() + "%"));
		if (!StringUtils.isEmpty(autenticacaoFilter.getIdPerfil()))
			predicates.add(builder.like(builder.lower(root.get("idPerfil")),
					autenticacaoFilter.getIdPerfil().getId().toString()));
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
