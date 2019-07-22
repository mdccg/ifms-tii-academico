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

		} catch (NoResultException noResultException) {
			return null;
		}
	}

	public Aluno buscaPorEmail(String email) {
		String sql = "FROM Aluno a " + "WHERE a.email = :paramEmail";

		TypedQuery<Aluno> typedQuery = entityManager.createQuery(sql, Aluno.class);
		typedQuery.setParameter("paramEmail", email);

		try {
			return typedQuery.getSingleResult();

		} catch (NoResultException noResultException) {
			return null;
		}
	}

	public Aluno buscaPorNome(String primeiro_nome, String ultimo_nome) {
		String sql = "FROM Aluno a " + "WHERE primeiro_nome = :paramPrimeiro_nome "
				+ "AND ultimo_nome = :paramUltimo_nome";

		TypedQuery<Aluno> typedQuery = entityManager.createQuery(sql, Aluno.class);
		typedQuery.setParameter("paramPrimeiro_nome", primeiro_nome);
		typedQuery.setParameter("paramUltimo_nome", ultimo_nome);

		try {
			return typedQuery.getSingleResult();

		} catch (NoResultException noResultException) {
			return null;
		}
	}
}
