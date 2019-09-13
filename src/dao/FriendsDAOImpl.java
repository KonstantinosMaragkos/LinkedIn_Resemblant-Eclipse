package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Friend;

public class FriendsDAOImpl implements FriendsDAO{

	@Override
	public List<Friend> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Friend.findAll");
		@SuppressWarnings("unchecked")
		List<Friend> frs = query.getResultList();  
        return frs;
	}
	
	@Override
	public Friend find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Friend fr = em.find(Friend.class, id); 
        return fr;
	}
	
	@Override
	public List<Friend> findFriends(int u_id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		Query query = em.createQuery("SELECT f FROM Friend f WHERE f.user_id LIKE :u AND f.status LIKE :s");
		query.setParameter("user_id", u_id);
		query.setParameter("s", 0);
		@SuppressWarnings("unchecked")
		List<Friend> frs = query.getResultList();  
        return frs;
	}
	
	@Override
	public int create(Friend fr) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(fr);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
		//em.close();
	}
	
	@Override
	public void refresh(Friend fd) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(fd);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Override
	public int remove(Friend fd) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.remove(fd);
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public int update(int u_id, int f_id, int status) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if(! em.getTransaction().isActive()) {
			Query query = em.createQuery("UPDATE Friend SET status= :s "
				+ "WHERE user_id = :ui AND friend_id = :fi");
		
			query.setParameter("s", status);
			query.setParameter("ui", u_id);
			query.setParameter("fi", f_id);
		
			em.getTransaction().begin();
			query.executeUpdate();
			em.getTransaction().commit();
			return 0;
		}
		else {
			return 1;
		}
	}
}
