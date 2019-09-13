package dao;
import java.util.List;
import model.Aggelia;

public interface AggeliaDAO {

	public List<Aggelia> list();
	
	public Aggelia find(int id);
	
	public int create(Aggelia ag);
	
	public void refresh(Aggelia ag);
	
	public int remove(Aggelia ag);
}
