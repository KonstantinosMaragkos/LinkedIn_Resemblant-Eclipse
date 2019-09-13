package dao;

import java.util.List;
import model.AdminUser;

public interface AdminDAO {

	public AdminUser find(int user_id);
	
	//public List<AdminUser> logIn(String e_mail, String pass);
}
