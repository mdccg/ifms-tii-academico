package br.edu.ifms.academico.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.ifms.academico.modelo.Aluno;
import br.edu.ifms.academico.modelo.Disciplina;

public class DisciplinaDAO extends DAO<Disciplina> {
	public DisciplinaDAO() {
		super();
	}

	public List<Disciplina> buscaDisciplinas(Aluno aluno) {
		String sql1 = "select d from "
				    + "Disciplina d "
				    + "JOIN Matricula m ON m.disciplina = d.id "
				    + "JOIN Aluno a on m.aluno = a.id "
				    + "WHERE a.id = :paramId";

		TypedQuery<Disciplina> typedQuery = entityManager.createQuery(sql1, Disciplina.class);
		typedQuery.setParameter("paramId", aluno.getId());
		return typedQuery.getResultList();
	}
}
