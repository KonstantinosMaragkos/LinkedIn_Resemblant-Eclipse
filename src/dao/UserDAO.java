package dao;

import java.util.List;

import model.User;

public interface UserDAO 
{
	public User find(int user_id);

    public List<User> list();

    public void create(User user);
    
    public List<User> logIn(String e_mail, String pass);
    
    public List<User> findEmail(String e_mail);
    
    public void update(String u_id, String new_email, String pass);
    
    public List<User> search_UserName(String name, String current_n, String current_s);
}
