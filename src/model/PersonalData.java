package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the personal_data database table.
 * 
 */
@Entity
@Table(name="personal_data")
@NamedQuery(name="PersonalData.findAll", query="SELECT p FROM PersonalData p")
public class PersonalData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPersonal_Data;

	private String company;

	private String department;

	private String education;

	private String location;

	private String title;

	private String university;

	private int yearFrom;

	private int yearTo;
	
	private int finished_status;

	//bi-directional one-to-one association to PdStatus
	@OneToOne(mappedBy="personalData")
	private transient PdStatus pdStatus;

	//bi-directional one-to-one association to User
	@OneToOne
	@PrimaryKeyJoinColumn(name="idPersonal_Data", referencedColumnName="Personal_Data_id")
	private User user;

	public PersonalData() {
	}

	public int getIdPersonal_Data() {
		return this.idPersonal_Data;
	}

	public void setIdPersonal_Data(int idPersonal_Data) {
		this.idPersonal_Data = idPersonal_Data;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public int getYearFrom() {
		return this.yearFrom;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public int getYearTo() {
		return this.yearTo;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
	
	//here
	public int getFinished_status() {
		return this.finished_status;
	}

	public void setFinished_status(int fs) {
		this.finished_status = fs;
	}
	//

	public PdStatus getPdStatus() {
		return this.pdStatus;
	}

	public void setPdStatus(PdStatus pdStatus) {
		this.pdStatus = pdStatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}