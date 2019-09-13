package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sxolio_arthro database table.
 * 
 */
@Entity
@Table(name="sxolio_arthro")
@NamedQuery(name="SxolioArthro.findAll", query="SELECT s FROM SxolioArthro s")
public class SxolioArthro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSxolio_Arthro;
	
	private int idArthro;
	
	private int User_id;

	@Lob
	private String comment;

	//bi-directional many-to-one association to Arthro
	@ManyToOne
	@JoinColumn(name="idArthro", referencedColumnName="idArthro")
	private transient Arthro arthro;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_id", referencedColumnName="user_id")
	private transient User user;

	public SxolioArthro() {
	}

	public int getIdSxolio_Arthro() {
		return this.idSxolio_Arthro;
	}

	public void setIdSxolio_Arthro(int idSxolio_Arthro) {
		this.idSxolio_Arthro = idSxolio_Arthro;
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

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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