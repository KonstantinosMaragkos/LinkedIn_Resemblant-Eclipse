package dao;
import java.util.List;

import model.Interest;

public interface InterestDAO {

	public List<Interest> list();
	
	public Interest find(int id);
	
	public int create(Interest in);
	
	public void refresh(Interest in);
	
	public int remove(Interest in);
}