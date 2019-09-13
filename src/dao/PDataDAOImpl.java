package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.PersonalData;

public class PDataDAOImpl implements PDataDAO 
{
	@Override
	public PersonalData find(int idPersonal_Data) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		PersonalData pd = em.find(PersonalData.class, idPersonal_Data); 
        return pd;
	}

	@Override
	public List<PersonalData> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("User.findAll");
		@SuppressWarnings("unchecked")
		List<PersonalData> pds = query.getResultList();  
        return pds;
	}

	@Override
	public void create(PersonalData pd) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(pd);
		em.getTransaction().commit();
		//em.close();
	}
	
	@Override
	public void updateNew(String id, String uni, String dep, String frm, String to, String edu, String title, String comp, String loc, String strStat)
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("UPDATE PersonalData SET university= :u, department= :d, "
				+ "yearFrom= :yf, yearTo= :yt, education= :e, title= :t, company= :c, location= :l, finished_status= :st "
				+ "WHERE idPersonal_Data = :i");
		query.setParameter("u", uni);
		query.setParameter("d", dep);
		
		int y1 = Integer.parseInt(frm);
		int y2= Integer.parseInt(to);
		int i = Integer.parseInt(id);
		int st = Integer.parseInt(strStat);
		
		query.setParameter("yf", y1);
		query.setParameter("yt", y2);
		query.setParameter("e", edu);
		query.setParameter("t", title);
		query.setParameter("c", comp);
		query.setParameter("l", loc);
		query.setParameter("st", st);
		query.setParameter("i", i);
		
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void refresh(PersonalData pd) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(pd);
		em.flush();
		em.getTransaction().commit();
	}
}
