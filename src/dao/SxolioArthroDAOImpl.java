package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.SxolioArthro;

public class SxolioArthroDAOImpl implements SxolioArthroDAO {
	@Override
	public List<SxolioArthro> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("SxolioArthro.findAll");
		@SuppressWarnings("unchecked")
		List<SxolioArthro> sxolia = query.getResultList();  
        return sxolia;
	}
	
	@Override
	public SxolioArthro find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		SxolioArthro sa = em.find(SxolioArthro.class, id); 
        return sa;
	}
	
	@Override
	public int create(SxolioArthro sa) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(sa);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public void refresh(SxolioArthro sa) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(sa);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Override
	public int remove(SxolioArthro sa) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.remove(sa);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
}
