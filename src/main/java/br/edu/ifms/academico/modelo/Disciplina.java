package br.edu.ifms.academico.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "disciplina")
	private List<Matricula> matriculas;

	@ManyToOne
	@JoinColumn(name = "professor")
	private Professor professor;

	private String nome;

	public Disciplina() {
	}

	public Disciplina(Long id, List<Matricula> matriculas, Professor professor, String nome) {
		super();
		this.id = id;
		this.matriculas = matriculas;
		this.professor = professor;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
