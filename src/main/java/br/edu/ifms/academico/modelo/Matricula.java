package br.edu.ifms.academico.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matriculas")
public class Matricula {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "disciplina")
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "aluno")
	private Aluno aluno;

	@Column(nullable = true)
	private Double nota1;
	
	@Column(nullable = true)
	private Double nota2;

	public Matricula() {
	}

	public Matricula(Long id, Disciplina disciplina, Aluno aluno, Double nota1, Double nota2) {
		super();
		this.id = id;
		this.disciplina = disciplina;
		this.aluno = aluno;
		this.nota1 = nota1;
		this.nota2 = nota2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

}
