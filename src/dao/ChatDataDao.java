package dao;

import java.util.List;

import model.ChatData;


public interface ChatDataDao {
	
	public void create(ChatData chat_data);
	
	public List<ChatData> LoadMessages(int chat_id);
	
	public List<ChatData> list();
	
	public void refresh(ChatData c);
}
