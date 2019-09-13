package dao;

import java.util.List;

import javax.persistence.EntityManager;

import jpautils.EntityManagerHelper;
import model.AdminUser;

public class AdminDAOImpl implements AdminDAO{

	@Override
	public AdminUser find(int user_id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		AdminUser ad = em.find(AdminUser.class, user_id); 
        return ad;
	}
}
