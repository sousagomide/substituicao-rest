package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "matriz_disciplina")
public class MatrizDisciplina extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "idmatriz", nullable = false)
	private Matriz matriz;

	@ManyToOne
	@JoinColumn(name = "iddisciplina", nullable = false)
	private Disciplina disciplina;

	@OneToMany(mappedBy = "matrizDisciplina", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<QuadroDocente> quadrosDocentes;

	public MatrizDisciplina() { }
	
	public MatrizDisciplina(Long id, Matriz matriz, Disciplina disciplina) {
		this.id = id;
		this.matriz = matriz;
		this.disciplina = disciplina;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<QuadroDocente> getQuadrosDocentes() {
		return quadrosDocentes;
	}

	public void setQuadrosDocentes(List<QuadroDocente> quadrosDocentes) {
		this.quadrosDocentes = quadrosDocentes;
	}

}
