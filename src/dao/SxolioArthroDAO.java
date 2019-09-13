package dao;
import java.util.List;

import model.SxolioArthro;

public interface SxolioArthroDAO {

	public List<SxolioArthro> list();
	
	public SxolioArthro find(int id);
	
	public int create(SxolioArthro sa);
	
	public void refresh(SxolioArthro sa);
	
	public int remove(SxolioArthro sa);
}