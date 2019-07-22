package br.edu.ifms.academico.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifms.academico.modelo.Disciplina;
import br.edu.ifms.academico.modelo.Matricula;

public class MatriculaDAO extends DAO<Matricula> {
	public MatriculaDAO() {
		super();
	}
	
	public List<Matricula> buscaPorDisciplina(Disciplina disciplina) {
		String sql = "FROM Matricula m "
				+ "JOIN Disciplina d "
				+ "ON m.disciplina = d.id "
				+ "WHERE id = :paramId";
		
		TypedQuery<Matricula> typedQuery = entityManager.createQuery(sql, Matricula.class);
		typedQuery.setParameter("paramId", disciplina.getId());
		
		try {
			return typedQuery.getResultList();

		} catch (NoResultException noResultException) {
			return null;
		}
	}
}
