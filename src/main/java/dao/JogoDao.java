package dao;


import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Jogo;
import util.JPAUtil;

public class JogoDao {
	public static void salvar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(jogo);
		em.getTransaction().commit();
		em.close();
	}

	public static void atualizar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(jogo);
		em.getTransaction().commit();
		em.close();
	}

	public static void excluir(int id) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Jogo excluirJogo = em.find(Jogo.class, id);
		em.remove(excluirJogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Jogo> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogo j");
		List<Jogo> resultado = q.getResultList();
		em.close();
		return resultado;
	}
	
	public static Integer maiorNumero(Jogo jogo) {
		List<Integer> v = Arrays.asList(jogo.getV1(),jogo.getV2(),jogo.getV3(),jogo.getV4(),jogo.getV5());
		return v.stream().max(Integer::compareTo).orElse(null);
	}
	
	public static Integer maiorNumeroSorteado() {
		EntityManager em = JPAUtil.criarEntityManager();
		TypedQuery<Integer> q = em.createNamedQuery("maiorNumeroSorteado", Integer.class);
		Integer n = q.getSingleResult();
		em.close();
		return n;
	}
}
