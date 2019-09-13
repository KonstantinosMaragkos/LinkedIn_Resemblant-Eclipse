package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Aggelia;

public class AggeliaDAOImpl implements AggeliaDAO {

	@Override
	public List<Aggelia> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Aggelia.findAll");
		@SuppressWarnings("unchecked")
		List<Aggelia> ags = query.getResultList();  
        return ags;
	}
	
	@Override
	public Aggelia find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Aggelia ag = em.find(Aggelia.class, id); 
        return ag;
	}
	
	@Override
	public int create(Aggelia ag) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(ag);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public void refresh(Aggelia ag) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(ag);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Override
	public int remove(Aggelia ag) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.remove(ag);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
}
