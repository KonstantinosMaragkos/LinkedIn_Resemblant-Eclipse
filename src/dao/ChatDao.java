package dao;

import java.util.List;

import model.Chat;

public interface ChatDao {
	
	public void create(Chat chat);
	
	public List<Chat> getChats(int user);
	
	public List<Chat> list();

	public void refresh(Chat c);
}
