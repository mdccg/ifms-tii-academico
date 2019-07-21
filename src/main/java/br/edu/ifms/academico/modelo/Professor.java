package br.edu.ifms.academico.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor extends Usuario {
	@OneToMany(mappedBy = "professor")
	private List<Disciplina> disciplinas;

	public Professor() {
	}

	public Professor(List<Disciplina> disciplinas) {
		super();
		this.disciplinas = disciplinas;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
