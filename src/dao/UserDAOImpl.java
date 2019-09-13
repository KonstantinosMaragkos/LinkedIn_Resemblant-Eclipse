package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.User;

public class UserDAOImpl implements UserDAO 
{

	@Override
	public User find(int user_id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		User user = em.find(User.class, user_id); 
        return user;
	}

	@Override
	public List<User> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("User.findAll");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();  
        return users;
	}

	@Override
	public void create(User user) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		//em.close();
	}
	
	@Override
	public List<User> logIn(String e_mail, String pass) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("SELECT u FROM User u WHERE u.e_Mail LIKE :Email "
				+ "AND u.password LIKE :Pass");
		query.setParameter("Email", e_mail);
		query.setParameter("Pass", pass);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		return users;
	}
	
	@Override
	public List<User> findEmail(String e_mail) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("SELECT u FROM User u WHERE u.e_Mail LIKE :Email ");
		query.setParameter("Email", e_mail);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public void update(String u_id, String new_email, String pass) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("UPDATE User SET e_Mail= :ne, password= :p "
				+ "WHERE user_id = :ui");
		
		query.setParameter("ne", new_email);
		query.setParameter("p", pass);
		//query.setParameter("ui", u_id);
		int id = Integer.parseInt(u_id);
		query.setParameter("ui", id);
		
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public List<User> search_UserName(String name, String current_n, String current_s) {
		//works for first and last name
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("SELECT u FROM User u WHERE (u.first_Name LIKE :n AND u.first_Name NOT LIKE :cn) OR (u.last_Name LIKE :n AND u.last_Name NOT LIKE :cs)");
		
		query.setParameter("n", name + "%");
		query.setParameter("cn", current_n);
		query.setParameter("cs", current_s);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		return users;
	}
}
