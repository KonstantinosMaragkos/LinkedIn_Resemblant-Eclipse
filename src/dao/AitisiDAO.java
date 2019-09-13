package dao;

import java.util.List;
import model.Aitisi;

public interface AitisiDAO {
	
	public List<Aitisi> list();
	
	public Aitisi find(int id);
	
	public int create(Aitisi ait);
	
	public void refresh(Aitisi ait);
	
	public void updateStatus(Aitisi ait, String status);
	
	public int remove(Aitisi ait);
	
}
