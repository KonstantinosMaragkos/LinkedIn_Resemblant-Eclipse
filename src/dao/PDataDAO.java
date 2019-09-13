package dao;

import java.util.List;

import model.PersonalData;

public interface PDataDAO 
{
	public PersonalData find(int idPersonal_Data);

    public List<PersonalData> list();

    public void create(PersonalData pData);
    
    public void updateNew(String id, String uni, String dep, String frm, String to, String edu, String title, String comp, String loc, String strStat);
    
    public void refresh(PersonalData pData);
}
