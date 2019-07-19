package br.edu.ifms.academico.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno extends Usuario {
	@OneToMany(mappedBy = "aluno")
	private List<Matricula> matriculas;

	public Aluno() {
		super();
	}

	public Aluno(List<Matricula> matriculas) {
		super();
		this.matriculas = matriculas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
}
