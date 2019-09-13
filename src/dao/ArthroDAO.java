package dao;
import java.util.List;

import model.Arthro;

public interface ArthroDAO {

	public List<Arthro> list();
	
	public Arthro find(int id);
	
	public int create(Arthro ar);
	
	public void refresh(Arthro ar);
	
	public int remove(Arthro ar);
}
