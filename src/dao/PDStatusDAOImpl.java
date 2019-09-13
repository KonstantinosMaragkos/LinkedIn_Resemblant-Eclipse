package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.PdStatus;
import model.PersonalData;

public class PDStatusDAOImpl implements PDStatusDAO  {
	
	@Override
	public PdStatus find(int idPersonal_Data) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		PdStatus pds = em.find(PdStatus.class, idPersonal_Data); 
        return pds;
	}
	
	@Override
	public List<PdStatus> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("User.findAll");
		@SuppressWarnings("unchecked")
		List<PdStatus> pds_list = query.getResultList();  
        return pds_list;
	}
	
	@Override
	public void create(PdStatus pds) 
	{
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(pds);
		em.getTransaction().commit();
		//em.close();
	}
	
	@Override
	public void update(String field, String status, int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query;
		
		if(field.equals("University")) {
			query =  em.createQuery("UPDATE PdStatus SET university_Status= :s WHERE idPersonal_Data = :i");
		}
		else if(field.equals("Department")) {
			query = em.createQuery("UPDATE PdStatus SET department_Status= :s WHERE idPersonal_Data = :i");
		}
		else if(field.equals("Education")) {
			query = em.createQuery("UPDATE PdStatus SET education_Status= :s WHERE idPersonal_Data = :i");
		}
		else if(field.equals("Title")) {
			query = em.createQuery("UPDATE PdStatus SET title_Status= :s WHERE idPersonal_Data = :i");
		}
		else if(field.equals("Company")) {
			query = em.createQuery("UPDATE PdStatus SET company_Status= :s WHERE idPersonal_Data = :i");
		}
		else {
			query = em.createQuery("UPDATE PdStatus SET location_Status= :s WHERE idPersonal_Data = :i");
		}
		
		int sts = Integer.parseInt(status);
		query.setParameter("s", sts);
		query.setParameter("i", id);
		
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void refresh(PdStatus pds) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.refresh(pds);
		em.flush();
		em.getTransaction().commit();
	}
}
