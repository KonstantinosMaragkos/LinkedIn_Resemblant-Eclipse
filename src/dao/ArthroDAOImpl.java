package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Arthro;

public class ArthroDAOImpl implements ArthroDAO {

	@Override
	public List<Arthro> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Arthro.findAll");
		@SuppressWarnings("unchecked")
		List<Arthro> ars = query.getResultList();  
        return ars;
	}
	
	@Override
	public Arthro find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Arthro ar = em.find(Arthro.class, id); 
        return ar;
	}
	
	@Override
	public int create(Arthro ar) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(ar);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public void refresh(Arthro ar) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(ar);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Override
	public int remove(Arthro ar) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.remove(ar);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
}
