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

import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.repository.filter.ServidorFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public class ServidorRepositoryImpl implements RepositoryQuery<Servidor, ServidorFilter> {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Servidor> listar(ServidorFilter servidorFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Servidor> criteria = builder.createQuery(Servidor.class);
		Root<Servidor> root = criteria.from(Servidor.class);
		Predicate[] predicates = criarRestricoes(servidorFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Servidor> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(servidorFilter));
	}

	private Long total(ServidorFilter servidorFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Servidor> root = criteria.from(Servidor.class);
		Predicate[] predicates = criarRestricoes(servidorFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarPaginacao(TypedQuery<Servidor> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(ServidorFilter servidorFilter, CriteriaBuilder builder, Root<Servidor> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(servidorFilter.getNome()))
			predicates.add(builder.like(builder.lower(root.get("nome")), servidorFilter.getNome() + "%"));
		if (!StringUtils.isEmpty(servidorFilter.getSiape()))
			predicates.add(builder.like(builder.lower(root.get("siape")), servidorFilter.getSiape() + "%"));
		if (!StringUtils.isEmpty(servidorFilter.getCategoria()))
			predicates
					.add(builder.like(builder.lower(root.get("categoria")), servidorFilter.getCategoria().toString()));
		if (!StringUtils.isEmpty(servidorFilter.getFuncao()))
			predicates.add(builder.like(builder.lower(root.get("funcao")), servidorFilter.getFuncao() + "%"));
		if (!StringUtils.isEmpty(servidorFilter.getEmail()))
			predicates.add(builder.like(builder.lower(root.get("email")), servidorFilter.getEmail() + "%"));
		if (!StringUtils.isEmpty(servidorFilter.getIdAutenticacao()))
			predicates.add(builder.like(builder.lower(root.get("idAutenticacao")),
					servidorFilter.getIdAutenticacao().getId().toString() + "%"));
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
