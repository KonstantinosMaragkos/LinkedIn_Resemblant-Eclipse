package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aitisi database table.
 * 
 */
@Entity
@NamedQuery(name="Aitisi.findAll", query="SELECT a FROM Aitisi a")
public class Aitisi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAitisi;
	
	private int idAggelia;
	
	private int user_id;

	private String status;

	//bi-directional many-to-one association to Aggelia
	@ManyToOne
	@JoinColumn(name="idAggelia", referencedColumnName="idAggelia")
	private transient Aggelia aggelia;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private transient User user;

	public Aitisi() {
	}

	public int getIdAitisi() {
		return this.idAitisi;
	}

	public void setIdAitisi(int idAitisi) {
		this.idAitisi = idAitisi;
	}
	
	public int getIdAggelia() {
		return this.idAggelia;
	}

	public void setIdAggelia(int idAggelia) {
		this.idAggelia = idAggelia;
	}
	
	public void setUser_id(int u_id) {
		this.user_id = u_id;
	}
	
	public int getUser_id() {
		return this.user_id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Aggelia getAggelia() {
		return this.aggelia;
	}

	public void setAggelia(Aggelia aggelia) {
		this.aggelia = aggelia;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}