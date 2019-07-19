/* _________________________________
< cof cof cof cof cof cof cof cof >
 ---------------------------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     || */

package br.edu.ifms.academico.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	public static final String PERSISTENCE_UNIT = "academico";
	private static EntityManagerFactory entityManagerFactory;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		} catch (Throwable ex) {
			System.err.println("Não foi possível carregar o EntityManager.");
			ex.printStackTrace();
			throw new ExceptionInInitializerError();
		}
	}

	public static EntityManager createEntityManager() {
		if (!entityManagerFactory.isOpen())
			throw new RuntimeException("EntityManagerFactory está fechada.");
		return entityManagerFactory.createEntityManager();
	}

	public static void closeEntityManagerFactory() {
		if (entityManagerFactory.isOpen())
			entityManagerFactory.close();
	}
}
