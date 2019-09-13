package dao;
import java.util.List;

import model.Friend;

public interface FriendsDAO {

	public List<Friend> list();
	
	public Friend find(int id);
	
	public List<Friend> findFriends(int u_id);
	
	public int create(Friend fr);
	
	public void refresh(Friend fd);
	
	public int remove(Friend fd);
	
	public int update(int u_id, int f_id, int status);
}
