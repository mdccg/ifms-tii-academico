package br.edu.ifms.academico.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifms.academico.interfaces.Sudo;
import br.edu.ifms.academico.modelo.Professor;

public class ProfessorDAO extends DAO<Professor> implements Sudo {
	public ProfessorDAO() {
		super();
	}
	
	public Professor sudoSu(String email, String senha) {
		String sql = "FROM Professor p "
				+ "WHERE p.email = :paramEmail "
				+ "AND p.senha = :paramSenha";
		TypedQuery<Professor> typedQuery = entityManager.createQuery(sql, Professor.class);
		typedQuery.setParameter("paramEmail", email);
		typedQuery.setParameter("paramSenha", senha);
		try {
			return typedQuery.getSingleResult();

		} catch (NoResultException noResultException) {
			return null;
		}
	}
}
