package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pd_status database table.
 * 
 */
@Entity
@Table(name="pd_status")
@NamedQuery(name="PdStatus.findAll", query="SELECT p FROM PdStatus p")
public class PdStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPersonal_Data;

	private int company_Status;

	private int department_Status;

	private int education_Status;

	private int location_Status;

	private int title_Status;

	private int university_Status;

	//bi-directional one-to-one association to PersonalData
	@OneToOne
	@PrimaryKeyJoinColumn(name="idPersonal_Data", referencedColumnName="idPersonal_Data")
	private transient PersonalData personalData;

	public PdStatus() {
	}

	public int getIdPersonal_Data() {
		return this.idPersonal_Data;
	}

	public void setIdPersonal_Data(int idPersonal_Data) {
		this.idPersonal_Data = idPersonal_Data;
	}

	public int getCompany_Status() {
		return this.company_Status;
	}

	public void setCompany_Status(int company_Status) {
		this.company_Status = company_Status;
	}

	public int getDepartment_Status() {
		return this.department_Status;
	}

	public void setDepartment_Status(int department_Status) {
		this.department_Status = department_Status;
	}

	public int getEducation_Status() {
		return this.education_Status;
	}

	public void setEducation_Status(int education_Status) {
		this.education_Status = education_Status;
	}

	public int getLocation_Status() {
		return this.location_Status;
	}

	public void setLocation_Status(int location_Status) {
		this.location_Status = location_Status;
	}

	public int getTitle_Status() {
		return this.title_Status;
	}

	public void setTitle_Status(int title_Status) {
		this.title_Status = title_Status;
	}

	public int getUniversity_Status() {
		return this.university_Status;
	}

	public void setUniversity_Status(int university_Status) {
		this.university_Status = university_Status;
	}

	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}
	
	//mine
		public void setAll(int value) {
			this.company_Status = value;
			this.department_Status = value;
			this.education_Status = value;
			this.location_Status = value;
			this.title_Status = value;
			this.university_Status = value;
		}

		public int getGEducation() {
			if(this.university_Status==1 && this.department_Status==1 && this.education_Status==1) {
				return 1;
			}
			else {
				return 0;
			}
		}
		
		public int getGProfession() {
			if(this.title_Status==1 && this.company_Status==1 && this.location_Status==1) {
				return 1;
			}
			else {
				return 0;
			}
		}

}