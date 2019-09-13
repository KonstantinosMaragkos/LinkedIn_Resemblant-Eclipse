package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Active;
import model.Arthro;
import model.Chat;
import model.User;
import jpautils.EntityManagerHelper;


public class ChatDaoImpl implements ChatDao{

	@Override
	public void create(Chat chat) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(chat);
		em.getTransaction().commit();
	}
	
	@Override
	public List<Chat> getChats(int user) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT c FROM Chat c WHERE c.user1= :us " + "OR c.user2= :us");
		q.setParameter("us", user);
		@SuppressWarnings("unchecked")
		List<Chat> chats = q.getResultList();
		return chats;
	}
	
	@Override
	public List<Chat> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Chat.findAll");
		@SuppressWarnings("unchecked")
		List<Chat> chat = query.getResultList();  
        return chat;
	}
	
	@Override
	public void refresh(Chat c) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(c);
		em.flush();
		em.getTransaction().commit();
	}
}
