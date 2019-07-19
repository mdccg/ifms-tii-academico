package br.edu.ifms.academico.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifms.academico.modelo.Aluno;
import br.edu.ifms.academico.modelo.Endereco;

public class EnderecoDAO extends DAO<Endereco> {
	public EnderecoDAO() {
		super();
	}
	
	public Endereco buscaPorUsuario(Aluno aluno) {
		String sql = "FROM Endereco AS e "
				+ "JOIN Aluno AS a "
				+ "ON e.id = a.endereco_id "
				+ "WHERE a.id = :paramId"; // FIXME 
		
		TypedQuery<Endereco> typedQuery = entityManager.createQuery(sql, Endereco.class);
		typedQuery.setParameter("paramId", aluno.getId());
		
		try {
			return typedQuery.getSingleResult();
			
		} catch(NoResultException noResultException) { return null; }
	}
}
