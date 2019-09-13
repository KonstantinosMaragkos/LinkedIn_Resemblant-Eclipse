package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Interest;

public class InterestDAOImpl implements InterestDAO {
	@Override
	public List<Interest> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Interest.findAll");
		@SuppressWarnings("unchecked")
		List<Interest> ins = query.getResultList();  
        return ins;
	}
	
	@Override
	public Interest find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Interest in = em.find(Interest.class, id); 
        return in;
	}
	
	@Override
	public int create(Interest in) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(in);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public void refresh(Interest in) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(in);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Override
	public int remove(Interest in) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.remove(in);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
}
