package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the arthro database table.
 * 
 */
@Entity
@NamedQuery(name="Arthro.findAll", query="SELECT a FROM Arthro a")
public class Arthro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idArthro;
	
	private int User_id;

	private String photo;

	@Lob
	private String text;

	private String video;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_id", referencedColumnName="user_id")
	private transient User user;

	/*//bi-directional many-to-one association to SxolioArthro
	@OneToMany(mappedBy="arthro")
	private List<SxolioArthro> sxolioArthros;*/

	public Arthro() {
	}

	public int getIdArthro() {
		return this.idArthro;
	}

	public void setIdArthro(int idArthro) {
		this.idArthro = idArthro;
	}
	
	public void setUser_id(int u_id) {
		this.User_id = u_id;
	}
	
	public int getUser_id() {
		return this.User_id;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*public List<SxolioArthro> getSxolioArthros() {
		return this.sxolioArthros;
	}

	public void setSxolioArthros(List<SxolioArthro> sxolioArthros) {
		this.sxolioArthros = sxolioArthros;
	}

	public SxolioArthro addSxolioArthro(SxolioArthro sxolioArthro) {
		getSxolioArthros().add(sxolioArthro);
		sxolioArthro.setArthro(this);

		return sxolioArthro;
	}

	public SxolioArthro removeSxolioArthro(SxolioArthro sxolioArthro) {
		getSxolioArthros().remove(sxolioArthro);
		sxolioArthro.setArthro(null);

		return sxolioArthro;
	}*/

}