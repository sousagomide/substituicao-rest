package br.edu.ifgoiano.substituicao.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifgoiano.substituicao.model.enumeration.ModalidadeCurso;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private ModalidadeCurso modalidade;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idcampus")
	private Campus idCampus;

	// Construtor padrão exigido pelo Hibernate
	public Curso() {}
	
	public Curso(Long id, String nome, ModalidadeCurso modalidade, Campus idCampus) {
		this.id = id;
		this.nome = nome;
		this.modalidade = modalidade;
		this.idCampus = idCampus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ModalidadeCurso getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeCurso modalidade) {
		this.modalidade = modalidade;
	}

	public Campus getIdCampus() {
		return idCampus;
	}

	public void setIdCampus(Campus idCampus) {
		this.idCampus = idCampus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
