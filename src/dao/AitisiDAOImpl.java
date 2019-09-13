package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;
import model.Aitisi;

public class AitisiDAOImpl implements AitisiDAO {

	@Override
	public List<Aitisi> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Aitisi.findAll");
		@SuppressWarnings("unchecked")
		List<Aitisi> aitiseis = query.getResultList();  
        return aitiseis;
	}
	
	@Override
	public Aitisi find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Aitisi ait = em.find(Aitisi.class, id); 
        return ait;
	}
	
	@Override
	public int create(Aitisi ait) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(ait);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public void updateStatus(Aitisi ait, String status) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("UPDATE Aitisi SET status= :st "
				+ "WHERE idAitisi = :id");
		query.setParameter("st", status);
		query.setParameter("id", ait.getIdAitisi());
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void refresh(Aitisi ait) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(ait);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Override
	public int remove(Aitisi ait) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.remove(ait);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
}
