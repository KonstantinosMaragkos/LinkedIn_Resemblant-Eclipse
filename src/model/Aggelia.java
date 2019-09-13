package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aggelia database table.
 * 
 */
@Entity
@NamedQuery(name="Aggelia.findAll", query="SELECT a FROM Aggelia a")
public class Aggelia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAggelia;
	
	private int user_id;

	@Lob
	private String description;

	@Lob
	private String desired;

	private String location;

	@Lob
	private String requirements;

	private String title;

	private String workload;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private transient User user;

	/*//bi-directional many-to-one association to Aitisi
	@OneToMany(mappedBy="aggelia")
	private List<Aitisi> aitisis;*/

	public Aggelia() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDesired() {
		return this.desired;
	}

	public void setDesired(String desired) {
		this.desired = desired;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRequirements() {
		return this.requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkload() {
		return this.workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*public List<Aitisi> getAitisis() {
		return this.aitisis;
	}

	public void setAitisis(List<Aitisi> aitisis) {
		this.aitisis = aitisis;
	}

	public Aitisi addAitisi(Aitisi aitisi) {
		getAitisis().add(aitisi);
		aitisi.setAggelia(this);

		return aitisi;
	}

	public Aitisi removeAitisi(Aitisi aitisi) {
		getAitisis().remove(aitisi);
		aitisi.setAggelia(null);

		return aitisi;
	}*/

}