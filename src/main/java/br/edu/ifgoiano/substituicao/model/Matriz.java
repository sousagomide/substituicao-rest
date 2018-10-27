package br.edu.ifgoiano.substituicao.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "matriz")
public class Matriz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer ano;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idturma")
	private Turma idTurma;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idcurso")
	private Curso idCurso;

	@OneToMany(mappedBy = "matriz", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MatrizDisciplina> disciplinas = new ArrayList<>();

	public Matriz(Long id, Integer ano, Turma idTurma, Curso idCurso) {
		this.id = id;
		this.ano = ano;
		this.idTurma = idTurma;
		this.idCurso = idCurso;
	}

	public void addDisciplina(Disciplina disciplina) {
		MatrizDisciplina matrizDisciplina = new MatrizDisciplina(this, disciplina);
		disciplinas.add(matrizDisciplina);
		disciplina.getMatrizes().add(matrizDisciplina);
	}

	public void removeTag(Disciplina disciplina) {
		for(Iterator<MatrizDisciplina> iterator = disciplinas.iterator(); iterator.hasNext();) {
			MatrizDisciplina matrizDisciplina = iterator.next();
			if(matrizDisciplina.getMatriz().equals(this) && matrizDisciplina.getDisciplina().equals(disciplina)) {
				iterator.remove();
				matrizDisciplina.getDisciplina().getMatrizes().remove(matrizDisciplina);
				matrizDisciplina.setMatriz(null);
				matrizDisciplina.setDisciplina(null);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Turma getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Turma idTurma) {
		this.idTurma = idTurma;
	}

	public Curso getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Curso idCurso) {
		this.idCurso = idCurso;
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
		Matriz other = (Matriz) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
