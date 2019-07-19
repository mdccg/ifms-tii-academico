package br.edu.ifms.academico.dao;

import javax.persistence.EntityManager;

import br.edu.ifms.academico.utils.JPAUtil;

public class DAO<T> {
	EntityManager entityManager;

	public DAO() {
		entityManager = JPAUtil.createEntityManager();
	}

	public void adiciona(T t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	public T buscaPorID(Class<T> classOfT, Long id) {
		return entityManager.find(classOfT, id);
	}

	public void atualiza(T t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	public void remove(T t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}
}
