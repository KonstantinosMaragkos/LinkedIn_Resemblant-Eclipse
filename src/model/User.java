package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int user_id;

	private String e_Mail;

	private String first_Name;

	private String last_Name;

	private String password;

	private String phone;

	private String photo;
	
	private int personal_data_id;

	//bi-directional one-to-one association to PersonalData
	@OneToOne(mappedBy="user")
	private transient PersonalData personalData;
	
	//bi-directional one-to-one association to AdminUser
	@OneToOne(mappedBy="user")
	private transient AdminUser admin_user;

	/*//bi-directional many-to-one association to Friend
	@OneToMany(mappedBy="user1")
	private List<Friend> friends1;

	//bi-directional many-to-one association to Friend
	@OneToMany(mappedBy="user2")
	private List<Friend> friends2;

	//bi-directional many-to-one association to Arthro
	@OneToMany(mappedBy="user")
	private List<Arthro> arthros;

	//bi-directional many-to-one association to SxolioArthro
	@OneToMany(mappedBy="user")
	private List<SxolioArthro> sxolioArthros;*/

	public User() {
	}

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getE_Mail() {
		return this.e_Mail;
	}

	public void setE_Mail(String e_Mail) {
		this.e_Mail = e_Mail;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public int getPersonal_Data_id() {
		return this.personal_data_id;
	}
	
	public void setPersonal_Data_id(int id) {
		this.personal_data_id = id;
	}
	
	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	/*public List<Friend> getFriends1() {
		return this.friends1;
	}

	public void setFriends1(List<Friend> friends1) {
		this.friends1 = friends1;
	}

	public Friend addFriends1(Friend friends1) {
		getFriends1().add(friends1);
		friends1.setUser1(this);

		return friends1;
	}

	public Friend removeFriends1(Friend friends1) {
		getFriends1().remove(friends1);
		friends1.setUser1(null);

		return friends1;
	}

	public List<Friend> getFriends2() {
		return this.friends2;
	}

	public void setFriends2(List<Friend> friends2) {
		this.friends2 = friends2;
	}

	public Friend addFriends2(Friend friends2) {
		getFriends2().add(friends2);
		friends2.setUser2(this);

		return friends2;
	}

	public Friend removeFriends2(Friend friends2) {
		getFriends2().remove(friends2);
		friends2.setUser2(null);

		return friends2;
	}

	public List<Arthro> getArthros() {
		return this.arthros;
	}

	public void setArthros(List<Arthro> arthros) {
		this.arthros = arthros;
	}

	public Arthro addArthro(Arthro arthro) {
		getArthros().add(arthro);
		arthro.setUser(this);

		return arthro;
	}

	public Arthro removeArthro(Arthro arthro) {
		getArthros().remove(arthro);
		arthro.setUser(null);

		return arthro;
	}

	public List<SxolioArthro> getSxolioArthros() {
		return this.sxolioArthros;
	}

	public void setSxolioArthros(List<SxolioArthro> sxolioArthros) {
		this.sxolioArthros = sxolioArthros;
	}

	public SxolioArthro addSxolioArthro(SxolioArthro sxolioArthro) {
		getSxolioArthros().add(sxolioArthro);
		sxolioArthro.setUser(this);

		return sxolioArthro;
	}

	public SxolioArthro removeSxolioArthro(SxolioArthro sxolioArthro) {
		getSxolioArthros().remove(sxolioArthro);
		sxolioArthro.setUser(null);

		return sxolioArthro;
	}*/

}