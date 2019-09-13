package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Chat;
import model.ChatData;
import jpautils.EntityManagerHelper;

public class ChatDataDaoImpl implements ChatDataDao{

	@Override
	public void create(ChatData chat_data) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(chat_data);
		em.getTransaction().commit();
	}
	
	@Override
	public List<ChatData> LoadMessages(int chat_id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT cd FROM ChatData cd WHERE cd.chat_id LIKE :c ");
		q.setParameter("c", chat_id);
		@SuppressWarnings("unchecked")
		List<ChatData> messages = q.getResultList();
		return messages;
	}
	
	public List<ChatData> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("ChatData.findAll");
		@SuppressWarnings("unchecked")
		List<ChatData> cd = query.getResultList();  
        return cd;
	}
	
	@Override
	public void refresh(ChatData c) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(c);
		em.flush();
		em.getTransaction().commit();
	}
}
