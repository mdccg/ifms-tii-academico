package br.edu.ifms.academico.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifms.academico.modelo.Aluno;
import br.edu.ifms.academico.modelo.Endereco;

public class EnderecoDAO extends DAO<Endereco> {
	public EnderecoDAO() {
		super();
	}
	
	public Endereco verificaExistente(Endereco endereco) {
		String sql = "FROM Endereco e "
				+ "WHERE e.bairro = :paramBairro "
				+ "AND e.cep = :paramCep "
				+ "AND e.cidade = :paramCidade "
				+ "AND e.numero = :paramNumero "
				+ "AND e.rua = :paramRua";
		
		TypedQuery<Endereco> typedQuery = entityManager.createQuery(sql, Endereco.class);
		typedQuery.setParameter("paramBairro", endereco.getBairro());
		typedQuery.setParameter("paramCep", endereco.getCep());
		typedQuery.setParameter("paramCidade", endereco.getCidade());
		typedQuery.setParameter("paramNumero", endereco.getNumero());
		typedQuery.setParameter("paramRua", endereco.getRua());
		
		try {
			return typedQuery.getSingleResult();
			
		} catch(NoResultException noResultException) {
			return null;
		}
	}
	
	public Endereco buscaPorAluno(Aluno aluno) {
		return null;
	}
}
