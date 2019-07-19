package br.edu.ifms.academico.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifms.academico.modelo.Aluno;

public class AlunoDAO extends DAO<Aluno> {
	public AlunoDAO() {
		super();
	}

	public Aluno login(String email, String senha) {
		String sql = "SELECT a FROM Aluno a WHERE a.email = :paramEmail AND a.senha = :paramSenha";
		
		TypedQuery<Aluno> typedQuery = entityManager.createQuery(sql, Aluno.class);
		typedQuery.setParameter("paramEmail", email);
		typedQuery.setParameter("paramSenha", senha);
		
		try {
			return typedQuery.getSingleResult();
			
		} catch(NoResultException noResultException) { return null; }
	}
}
