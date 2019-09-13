package dao;

import java.util.List;

import model.PdStatus;

public interface PDStatusDAO {
	
	public PdStatus find(int idPersonal_Data);

    public List<PdStatus> list();

    public void create(PdStatus pds);
    
    public void update(String field, String status, int id);
    
    public void refresh(PdStatus pds);
}
