package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import model.Active;
import model.Chat;
import model.Friend;
import jpautils.EntityManagerHelper;

public class ActiveDaoImpl implements ActiveDao {

	@Override
	public List<Active> list(int user_id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Active a WHERE a.user_id= :User ");
		q.setParameter("User", user_id);
		@SuppressWarnings("unchecked")
		List<Active> active = q.getResultList();
		return active;		
	}
	
	@Override
	public List<Active> listAll() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Active.findAll");
		@SuppressWarnings("unchecked")
		List<Active> active = query.getResultList();  
        return active;
	}
	
	@Override
	public void create(Active active) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(active);
		em.getTransaction().commit();
	}
	
	@Override
	public void update(int user, int newChat) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("UPDATE Active SET chat_id= :ChatId " + "WHERE user_id= :UserId ");
		q.setParameter("ChatId", newChat);
		q.setParameter("UserId", user);
		
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void refresh(Active a) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(a);
		em.flush();
		em.getTransaction().commit();
	}
}
