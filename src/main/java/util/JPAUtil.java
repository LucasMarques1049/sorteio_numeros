package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jogo_ap2");
	
	public static EntityManager criarEntityManager() {
		return emf.createEntityManager();
	}
}
