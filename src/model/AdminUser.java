package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the admin_user database table.
 * 
 */
@Entity
@Table(name="admin_user")
@NamedQuery(name="AdminUser.findAll", query="SELECT a FROM AdminUser a")
public class AdminUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int User_id;

	//bi-directional one-to-one association to User
	@OneToOne
	@PrimaryKeyJoinColumn(name="User_id", referencedColumnName="user_id")
	private transient User user;

	public AdminUser() {
	}

	public int getUser_id() {
		return this.User_id;
	}

	public void setUser_id(int user_id) {
		this.User_id = user_id;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}