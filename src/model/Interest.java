package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the interest database table.
 * 
 */
@Entity
@NamedQuery(name="Interest.findAll", query="SELECT i FROM Interest i")
public class Interest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idInterest;
	
	private int idArthro;
	
	private int User_id;

	//bi-directional many-to-one association to Arthro
	@ManyToOne
	@JoinColumn(name="idArthro", referencedColumnName="idArthro")
	private transient Arthro arthro;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_id", referencedColumnName="user_id")
	private transient User user;

	public Interest() {
	}

	public int getIdInterest() {
		return this.idInterest;
	}

	public void setIdInterest(int idInterest) {
		this.idInterest = idInterest;
	}
	
	public int getUser_id() {
		return this.User_id;
	}

	public void setUser_id(int user_id) {
		this.User_id = user_id;
	}
	
	public int get_idArthro() {
		return this.idArthro;
	}

	public void set_idArthro(int id) {
		this.idArthro = id;
	}

	public Arthro getArthro() {
		return this.arthro;
	}

	public void setArthro(Arthro arthro) {
		this.arthro = arthro;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}