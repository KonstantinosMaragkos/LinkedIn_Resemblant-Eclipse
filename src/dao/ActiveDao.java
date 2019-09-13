package dao;

import java.util.List;

import model.Active;

public interface ActiveDao {
	
	public List<Active> list(int user_id);
	
	public void create(Active active);
	
	public void update(int user, int newChat);
	
	public List<Active> listAll();
	
	public void refresh(Active a);

}
